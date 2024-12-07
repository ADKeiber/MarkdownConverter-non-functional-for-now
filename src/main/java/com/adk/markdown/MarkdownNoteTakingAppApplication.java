package com.adk.markdown;

import com.adk.markdown.tag.BaseTag;
import com.adk.markdown.tag.HeaderTag;
import com.adk.markdown.tag.SupportedTags;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
public class MarkdownNoteTakingAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(MarkdownNoteTakingAppApplication.class, args);

		for(BaseTag bt: SupportedTags.tags){

		}
		final String[] text = {"#### *#Bold Text* \\n ##### Regular text "};
		Map<Integer, BaseTag> foundTags = new HashMap<>();

		//Finds all instances of tag values in a text and
		SupportedTags.tags.forEach(
				tag -> {
//					List<String> foundStrings = new ArrayList<>();
//					tag.getTagValues().forEach( value -> {
//							if(text[0].contains(value)){
//								text[0] = text[0].substring(text[0].indexOf(value), value.length());
//							}
//						}
//					);
//					text[0]
//							tag.getTagValues().stream()
//							.filter(value -> text[0].indexOf(value) != -1).toList();
//					if(!foundStrings.isEmpty()){
//                        for (String foundString : foundStrings) {
//                            foundTags.put(text[0].indexOf(foundString), tag);
//                        }
//					}

				}
		);
		System.out.println(foundTags);
		for(int i = 0; i < text[0].length(); i++) {
			if(foundTags.get(i) != null){
				int index = foundTags.get(i).getTextWithAppliedFormat(text[0]);
				System.out.println("Remainder text: " + text[0].substring(index));
			}
		}

//		HeaderTag headerTag = new HeaderTag();
//
//		int index = headerTag.getTextWithAppliedFormat(text);
//		System.out.println("Remainder text: " + text.substring(index));

	}

}
