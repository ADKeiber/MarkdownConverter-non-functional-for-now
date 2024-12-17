package com.adk.markdown.tag;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Doesn't allow for
 */
public class CodeTag extends BaseTag{
    int startOfContentIndex = 1;

    public CodeTag(){
        this.supportedSubtags.addAll(Arrays.asList(new BaseTag[]{ }));
        this.tagValues.addAll(List.of(new String[]{"`"}));
        this.beginningTag = "`";
    }

    @Override
    public int getTextWithAppliedFormat(String entireText) {
        int endIndex = entireText.indexOf("`",1);
        if(endIndex == -1 || entireText.substring(1, endIndex).contains("\n\n")){
            validFormat = false;
        } else {
            this.content = entireText.substring(startOfContentIndex, endIndex);
            this.endTag = "`";
        }
        endIndex = endIndex == -1? entireText.length(): endIndex + startOfContentIndex;
        return endIndex;
    }
}
