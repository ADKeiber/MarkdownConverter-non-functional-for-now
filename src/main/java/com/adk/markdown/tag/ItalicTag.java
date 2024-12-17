package com.adk.markdown.tag;

import java.util.Arrays;
import java.util.List;

public class ItalicTag extends BaseTag{
    public ItalicTag(){
        this.supportedSubtags.addAll(Arrays.asList(new BaseTag[]{ }));
        this.tagValues.addAll(List.of(new String[]{"*"}));
    }

    @Override
    public int getTextWithAppliedFormat(String entireText) {
        this.beginningTag = "*";
        String remainingText = entireText.substring(1);
        if(entireText.length() == 1 && (remainingText.charAt(0) == ' ' || remainingText.charAt(0) == '*')){
            validFormat = false;
        }
        int endTagIndex = remainingText.indexOf("*");

        if(endTagIndex == -1){
            endTagIndex = remainingText.length();
            validFormat = false;
        } else {
            if(endTagIndex == 0 ||remainingText.charAt(endTagIndex - 1) == ' ' )
                validFormat = false;
        }

        this.content = remainingText.substring(0, endTagIndex);

        generateSubTags();

        if(endTagIndex != remainingText.length()){
            endTagIndex += 2;
            this.endTag = "*";
        }
        return endTagIndex;
    }
}
