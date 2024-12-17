package com.adk.markdown.tag;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is technically a modified version of the LLink tag... it doesn't allow for any '[', ']', '(' or ')' except for the original UNLESS they have an escape character in front
 */
public class LinkTag extends BaseTag{
    public LinkTag(){
        this.supportedSubtags.addAll(Arrays.asList(new BaseTag[]{ }));
        this.tagValues.addAll(List.of(new String[]{"["}));
        this.beginningTag = "[";
    }

    @Override
    public int getTextWithAppliedFormat(String entireText) {
        /**
         * We need to check there isn't 2 of the same character next to each other ex: (( or [[ this breaks format
         * you also can't have a single ] inside of a [] or a ) inside of a ( TODO
         */

        int indexOfStartText = entireText.indexOf("[");
        int indexOfEndText = entireText.indexOf("]", indexOfStartText);
        int indexOfStartLink = -1;
        int indexOfEndLink = -1;

        if(indexOfEndText != -1){
            while(entireText.charAt(indexOfEndText - 1) == '\\'){
                indexOfEndText = entireText.indexOf("]", indexOfEndText + 1);
                if (indexOfEndText == -1)
                    break;
            }
            indexOfStartLink = entireText.indexOf("(", indexOfEndText);

            if(indexOfStartLink != -1 ) {
                while(entireText.charAt(indexOfStartLink - 1) == '\\'){
                    indexOfStartLink = entireText.indexOf("]", indexOfStartLink + 1);
                    if (indexOfStartLink == -1)
                        break;
                }
                if(indexOfEndText + 1 != indexOfStartLink)
                    validFormat = false;
                indexOfEndLink = entireText.indexOf(")", indexOfStartLink);

                if(indexOfEndLink != -1) {
                    while(entireText.charAt(indexOfEndLink - 1) == '\\'){
                        indexOfEndLink = entireText.indexOf(")", indexOfEndLink + 1);
                        if (indexOfEndLink == -1)
                            break;
                    }
                    if(indexOfEndLink != -1) {
                        this.content = entireText.substring(indexOfStartText + 1, indexOfEndText);
                        this.hiddenContent = entireText.substring(indexOfStartLink + 1, indexOfEndLink);
                        this.endTag = "]";
                    }
                }
            }
        }
        boolean duplicateExists = indexOfEndLink != -1 && (
                countOccurrences(entireText.substring(indexOfStartText, indexOfEndLink + 1), '(') > 1 ||
                countOccurrences(entireText.substring(indexOfStartText, indexOfEndLink + 1), ')') > 1 ||
                countOccurrences(entireText.substring(indexOfStartText, indexOfEndLink + 1), '[') > 0 ||
                countOccurrences(entireText.substring(indexOfStartText, indexOfEndLink + 1), ']') > 1);

        if(indexOfEndText == -1 || indexOfStartLink == -1 || indexOfEndLink == -1) {
            indexOfEndLink = entireText.length();
            this.validFormat = false;
        } else if (entireText.substring(indexOfStartText, indexOfEndLink).contains("\n") || duplicateExists ){
            validFormat = false;
        }
        return indexOfEndLink + 1;
    }

    public static int countOccurrences(String text, char letter) {
        int count = 0;
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) == letter && text.charAt(i - 1) != '\\') {
                count++;
            }
        }
        return count;
    }
}
