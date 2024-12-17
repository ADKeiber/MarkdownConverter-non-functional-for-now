package com.adk.markdown.tag;

import java.util.Arrays;
import java.util.List;

//TODO...
public class BoldTag extends BaseTag{

    public BoldTag(){
        this.supportedSubtags.addAll(Arrays.asList(new BaseTag[]{ }));
        this.tagValues.addAll(List.of(new String[]{"**"}));
    }

    @Override
    public int getTextWithAppliedFormat(String entireText) {
        this.beginningTag = "**";
        String remainingText = entireText.substring(2);
        if(entireText.charAt(0) == ' '){
            validFormat = false;
        }
        int endTagIndex = remainingText.indexOf("**");
        if(endTagIndex == -1){
            endTagIndex = remainingText.length();
            validFormat = false;
        } else {
            if(endTagIndex == 0 ||remainingText.charAt(endTagIndex - 1) == ' ' )
                validFormat = false;
        }
        this.content = remainingText.substring(0, endTagIndex);
        if(endTagIndex != remainingText.length()){
            endTagIndex += 4;
            this.endTag = "**";
        }
        generateSubTags();
        return endTagIndex;
    }
}
