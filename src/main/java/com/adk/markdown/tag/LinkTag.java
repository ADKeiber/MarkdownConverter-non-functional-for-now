package com.adk.markdown.tag;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is technically a modified version of the LLink tag... it doesn't allow for any '[', ']', '(' or ')' except for the original
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
        System.out.println(entireText);
        int indexOfStartText = entireText.indexOf("[");
        int indexOfEndText = entireText.indexOf("]", indexOfStartText);
        int indexOfStartLink = -1;
        int indexOfEndLink = -1;

        if(indexOfEndText != -1){

            indexOfStartLink = entireText.indexOf("(", indexOfEndText);

//            int duplicateSquareIndex = entireText.indexOf("[", indexOfStartText);
//
//            if((duplicateSquareIndex != -1 && duplicateSquareIndex < indexOfStartLink && !entireText.substring(duplicateSquareIndex, indexOfStartLink).contains("]")) )
//                validFormat = false;

            if(indexOfStartLink != -1 ) {

                if(indexOfEndText+1 != indexOfStartLink)
                    validFormat = false;

                indexOfEndLink = entireText.indexOf(")", indexOfStartLink);

//                duplicateSquareIndex = entireText.indexOf("[", indexOfStartText);

                if(indexOfEndLink != -1) {

                    this.content = entireText.substring(indexOfStartText + 1, indexOfEndText);
                    this.hiddenContent = entireText.substring(indexOfStartLink + 1, indexOfEndLink);
                    this.endTag = "]";
                    System.out.println("content: " + this.content);
                    System.out.println("hidden content: " + this.hiddenContent);
                }
            }
        }

        boolean duplicateExists = entireText.lastIndexOf("(") != entireText.indexOf("(") || entireText.lastIndexOf(")") != entireText.indexOf(")") ||
                entireText.lastIndexOf("]") != entireText.indexOf("]") || entireText.lastIndexOf("[") != entireText.indexOf("[");
        System.out.println("Duplicate: " + duplicateExists);

        if(indexOfEndText == -1 || indexOfStartLink == -1 || indexOfEndLink == -1) {
            indexOfEndLink = entireText.length();
            this.validFormat = false;
        } else if (entireText.substring(indexOfStartText, indexOfEndLink).contains("\n") || duplicateExists ){
            validFormat = false;
        }
        System.out.println("Valid format: " + this.validFormat);
        return indexOfEndLink;
    }
}
