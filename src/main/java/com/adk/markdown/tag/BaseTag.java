package com.adk.markdown.tag;

import lombok.Data;

import java.util.*;

@Data
public abstract class BaseTag {


    public Set<String> tagValues = new HashSet<>();

    /**
     * These tags represent the parts of a formatted tag area
     * 1) the beginning tag that starts the formatting
     * 2) the content contained inside of the tag
     * 3) the end tag the signifies the end of the format
     */
    public String beginningTag;
    public String content;
    public String hiddenContent; // This is used when there is hidden data (link and images)
    public String endTag;

    /**
     * This is needed to see if the actual format of the tag is valid
     */
    public boolean validFormat = true;
    public List<BaseTag> supportedSubtags = new LinkedList<>();

    public Map<Integer, BaseTag> subtags = new HashMap<>();

    //use Index Of to find
    //Needs to have a collection containing all Tag types and when one is detected the substring for that needs
    protected void generateSubTags(){
//        System.out.println("Generating tags");
        //IN this we need to go through the {@link #textWithAppliedTagFormat} and attempt to identify supported subtags and process them
        // in theory this would run after getTextWithAppliedFormat is ran and it would cascade down through all supported subtags and split them into beginning tag, end tag, and content
        //Finds all instances of tag values in a text and
        supportedSubtags.forEach(
                tag -> {
                    List<String> foundStrings = tag.getTagValues().stream()
                            .filter(content::contains).toList();
                    if(!foundStrings.isEmpty()){
                        for (String foundString : foundStrings) {
                            subtags.put(content.indexOf(foundString), tag);
                        }
                    }

                }
        );
        int lastProcessedIndex = 0;
        System.out.println(subtags);

        while(lastProcessedIndex <= content.length()) {

            if(subtags.get(lastProcessedIndex) != null){

                int endIndex = subtags.get(lastProcessedIndex).getTextWithAppliedFormat(content.substring(lastProcessedIndex));
                lastProcessedIndex += endIndex;
//                System.out.println("Remainder text: " + content.substring(lastProcessedIndex));
//                System.out.println(" ");
            }
            lastProcessedIndex++;
        }
    }

    /**
     * Find Beginning tag, content, endtag, and subtags (generateSubTags)
     * @param entireText
     * @return index of the endtag so a
     */
    public abstract int getTextWithAppliedFormat(String entireText);
}
