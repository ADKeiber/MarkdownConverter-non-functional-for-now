package com.adk.markdown.tag;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HorizontalRuleTag extends BaseTag{

    public HorizontalRuleTag(){
        this.supportedSubtags.addAll(Arrays.asList(new BaseTag[]{ }));
        this.tagValues.addAll(List.of(new String[]{"---"}));
        this.beginningTag = "---";
    }
    @Override
    public int getTextWithAppliedFormat(String entireText) {
        int endIndex = 2;
        int newLineIndex = entireText.indexOf("\n");
        Pattern pattern = Pattern.compile("[^\\s-]");
        Matcher m = pattern.matcher(entireText);


        if(newLineIndex == -1){
            endIndex = entireText.length();
        }
        if(newLineIndex == -1 && m.find()){
            validFormat = false;
        } else if(newLineIndex != -1 && pattern.matcher(entireText.substring(0,newLineIndex)).find()){
            validFormat = false;
        } else {
            endIndex += newLineIndex;
            this.endTag = "\n";
        }
        if(newLineIndex != -1){
            endIndex -= 1;
        }
        this.content = entireText.substring(0, endIndex);
        System.out.println(this);
        return endIndex;
    }
}
