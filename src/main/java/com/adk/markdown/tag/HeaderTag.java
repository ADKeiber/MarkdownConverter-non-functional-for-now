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

        this.beginningTag = entireText.substring(0, endBeginningTagIndex);

        int endTagIndex = endBeginningTagIndex;

        while(entireText.charAt(endTagIndex) == '\n'){
            endTagIndex++;
        }

        this.content = entireText.substring(endBeginningTagIndex, endTagIndex);
        System.out.println("end Index: " + endTagIndex );
        generateSubTags();
        System.out.println("Start tag: " + this.beginningTag);
        System.out.println("content: " + this.content);
        System.out.println("End tag: " + this.endTag);
        return endTagIndex;
    }
}
