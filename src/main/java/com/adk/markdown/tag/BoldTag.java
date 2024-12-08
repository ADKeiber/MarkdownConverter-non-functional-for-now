package com.adk.markdown.tag;

import java.util.Arrays;
import java.util.List;

public class BoldTag extends BaseTag{

    public BoldTag(){
        this.supportedSubtags.addAll(Arrays.asList(new BaseTag[]{ }));
        this.tagValues.addAll(List.of(new String[]{"*"}));
    }

    @Override
    public int getTextWithAppliedFormat(String entireText) {
        this.beginningTag = "**";
        System.out.println(entireText);
        String remainingText = entireText.substring(2);
        int endTagIndex = remainingText.indexOf("**");
        if(endTagIndex == -1){

            endTagIndex = remainingText.length();
            validFormat = false;
        }
        this.content = remainingText.substring(0, endTagIndex);
        System.out.println("end Index: " + endTagIndex );

        System.out.println("Start tag: " + this.beginningTag);
        System.out.println("content: " + this.content);

        generateSubTags();

        if(endTagIndex != remainingText.length()){
            endTagIndex += 4;
            this.endTag = "**";
            System.out.println("End tag: " + this.endTag);
        }

        return endTagIndex;
    }
}
