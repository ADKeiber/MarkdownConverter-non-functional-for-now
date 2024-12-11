package com.adk.markdown.tag;

import java.util.Arrays;
import java.util.List;

public class BlockquoteTag extends BaseTag{
    int startOfContentIndex = 1;

    public BlockquoteTag(){
        this.supportedSubtags.addAll(Arrays.asList(new BaseTag[]{ }));
        this.tagValues.addAll(List.of(new String[]{">"}));
        this.beginningTag = ">";
    }

    @Override
    public int getTextWithAppliedFormat(String entireText) {

        int endIndex = entireText.indexOf("\n\n");

        if(endIndex == -1 ){
            endIndex = entireText.length();
        } else {
            this.content = entireText.substring(startOfContentIndex, endIndex);
            endIndex = endIndex + startOfContentIndex + 1;
            this.endTag = "\n\n";
        }

        System.out.println(this);
        return endIndex;
    }
}
