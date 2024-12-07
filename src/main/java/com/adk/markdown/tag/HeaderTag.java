package com.adk.markdown.tag;

import java.util.Arrays;
import java.util.List;

public class HeaderTag extends BaseTag {

    public HeaderTag() {
        this.tagValues.addAll(List.of("# ## ### #### ##### ######".split("\\s+")));
        //Supports
        this.supportedSubtags.addAll(Arrays.asList(new BaseTag[]{new HeaderTag()}));
    }
}
