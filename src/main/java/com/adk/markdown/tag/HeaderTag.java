package com.adk.markdown.tag;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class HeaderTag extends BaseTag {

    public HeaderTag() {
        this.tagValues.addAll(List.of(new String[]{"###### ", "##### ","#### ","### ","## ", "# " }));

        this.supportedSubtags.addAll(Arrays.asList(new BaseTag[]{ new BoldTag(), new ItalicTag(),
                new HorizontalRuleTag(), new LinkTag(), new HeadingIdTag(), new StrikethroughTag(),
                new EmojiTag(), new HighlightTag(), new SubscriptTag(), new SuperscriptTag()}));
        this.endTag = "\\n"; //Header tag end with a new line
    }

    @Override
    public int getTextWithAppliedFormat(String entireText) {

        int endBeginningTagIndex = 1;

        while(entireText.charAt(endBeginningTagIndex) == '#'){
            endBeginningTagIndex++;
        }
        endBeginningTagIndex++;
        this.beginningTag = entireText.substring(0, endBeginningTagIndex);

        int endTagIndex = entireText.indexOf("\\n");
        if(endTagIndex == -1){
            endTagIndex = entireText.length();
        }

        this.content = entireText.substring(endBeginningTagIndex, endTagIndex);
        System.out.println("end Index: " + endTagIndex );
        generateSubTags();
        System.out.println("Start tag: " + this.beginningTag);
        System.out.println("content: " + this.content);
        System.out.println("End tag: " + this.endTag);
        if(endTagIndex != entireText.length())
            endTagIndex += 2;
        if (entireText.charAt(0) != '#'){
            //this formatting won't be applied to the data inside; However, there is still an opportunity to format sub tags
            // in other words this will be used in html rendering and not
           validFormat = false;
        }

        return endTagIndex;
    }
}
