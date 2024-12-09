package com.adk.markdown.tag;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FencedCodeBlock extends BaseTag{

    int startOfContentIndex = 3;

    public FencedCodeBlock(){
        this.supportedSubtags.addAll(Arrays.asList(new BaseTag[]{ }));
        this.tagValues.addAll(List.of(new String[]{"```"}));
        this.beginningTag = "```";
    }

    @Override
    public int getTextWithAppliedFormat(String entireText) {

//        System.out.println("\nEntire string: \n" + entireText);
//        System.out.println("Entire string length: " + entireText.length());
//        System.out.println("String after start tag: \n" + entireText.substring(startOfContentIndex));

        boolean foundValidEndTag = false;
        int endTagIndex = entireText.indexOf("```", startOfContentIndex);
        endTagIndex = endTagIndex == -1? entireText.length(): endTagIndex + startOfContentIndex;

//        System.out.println("End Index: " + endTagIndex);
//        System.out.println("Character at End Index: " + entireText.charAt(endTagIndex));


        while(!foundValidEndTag && endTagIndex <= entireText.length() - 1) {

            String remainderText = entireText.substring(endTagIndex + 1);
//            System.out.println("REMAINDER TEXT: " + remainderText);

            // Need to look for anything other than a space between this and either a new line or the end of the string
            int newLineIndex = remainderText.indexOf("\n");
            //checks for any character that doesn't match a space, or new line
            Pattern pattern = Pattern.compile("[^\\s\\r\\n]");
            Matcher m;

            //Checks to see if a new line was found if not look for characters in entire string after end tag, else look between end tag and new line
            m = newLineIndex == -1 ? pattern.matcher(entireText.substring(endTagIndex)) : pattern.matcher(remainderText.substring(0, newLineIndex));

            //Sets end tag to null if no characters were found, else ```
            this.endTag = m.find() ? null : "```";

            if (this.endTag != null) {
                foundValidEndTag = true;
            } else {
                int tempIndex = endTagIndex;
                tempIndex = entireText.indexOf("```", endTagIndex);
                endTagIndex = tempIndex == -1? entireText.length(): endTagIndex + startOfContentIndex;
            }
        }

//        System.out.println("End Index: " + endTagIndex);

        return endTagIndex;
    }

}
