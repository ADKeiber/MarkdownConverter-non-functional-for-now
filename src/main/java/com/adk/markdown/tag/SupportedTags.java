package com.adk.markdown.tag;

import java.util.Set;

/**
 * https://www.markdownguide.org/cheat-sheet/
 */
public class SupportedTags {
//    public static Set<BaseTag> tags = Set.of(new BaseTag[]{ new BlockquoteTag(), new BoldTag(), new CodeTag(),
//            new DefinitionListTag(), new EmojiTag(), new FencedCodeBlockTag(), new FootnoteTag(), new HeaderTag(),
//            new HeadingIdTag(), new HighlightTag(),new HorizontalRuleTag(), new ImageTag(), new ItalicTag(),
//            new LinkTag(), new OrderedListTag(), new StrikethroughTag(), new SuperscriptTag(), new TableTag(),
//            new TaskListTag(), new UnorderedListTag()});
    public static Set<BaseTag> tags = Set.of(new LinkTag());
}
