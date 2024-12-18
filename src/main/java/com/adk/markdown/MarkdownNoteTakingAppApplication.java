package com.adk.markdown;

import com.adk.markdown.tag.BaseTag;
import com.adk.markdown.tag.HeaderTag;
import com.adk.markdown.tag.SupportedTags;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.http.HttpClient;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@SpringBootApplication
public class MarkdownNoteTakingAppApplication {

	/**
	 * Ne
	 * @param args
	 */
	public static void main(String[] args) {

		SpringApplication.run(MarkdownNoteTakingAppApplication.class, args);
//		String text = "##-## **Bold Text** \\n ##### Regular text ";
//		String text = "``` Test this is a test to test the stuff ``` a\n outside";

		String text = "> this is a test \n and a new line ` test";

		Map<Integer, BaseTag> foundTags = new HashMap<>();

		//TODO NEED TO keep track of 'dead indices' which are indices that don't have an applied format (this includes spaces between formatted content)
		//Prehaps we could do this in a class/method that has the context
		//Finds all instances of tag values in a text and
		SupportedTags.tags.forEach(
				tag -> {
					List<String> foundStrings = tag.getTagValues().stream()
							.filter(text::contains).toList();
					if(!foundStrings.isEmpty()){
                        for (String foundString : foundStrings) {
                            foundTags.put(text.indexOf(foundString), tag);
                        }
					}

				}
		);
		int lastProcessedIndex = 0;
		System.out.println(foundTags);

		while(lastProcessedIndex <= text.length()) {

			if(foundTags.get(lastProcessedIndex) != null){

				int endIndex = foundTags.get(lastProcessedIndex).getTextWithAppliedFormat(text.substring(lastProcessedIndex));
				lastProcessedIndex += endIndex;
				System.out.println("Remainder text: " + text.substring(lastProcessedIndex));
				System.out.println(" ");
			}
			lastProcessedIndex++;
		}
	}

}
