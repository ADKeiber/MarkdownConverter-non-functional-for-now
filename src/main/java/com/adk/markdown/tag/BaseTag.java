package com.adk.markdown.tag;

import java.util.List;
import java.util.Set;

public abstract class BaseTag {

    Set<String> tagValues;

    String formattedArea; //This will be uniquely set in the class that extends this based on unique criteria for each type of tag

    List<BaseTag> supportedSubtags;

    List<BaseTag> subtags;

//    //Some markup tags can be subtags of other some can't (example of can: Strikethrough make up, example of can't: Headers
//    boolean canBeSubtag;
//
//    //Some tags don't support subtags like links
//    boolean supportsSubtags;

    //use Index Of to find
    //Needs to have a collection containing all Tag types and when one is detected the substring for that needs
    public void generateSubTags(){

    }
}
