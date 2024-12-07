package com.adk.markdown.util;

import java.util.*;

public class StringTokenizer {
    public static Set<String> markupTokens = Set.of("# ## ### ##### ###### ====== ------ *  _ ** __ ~~ ".split("\\s+"));
    public static List<String> tokenize(String text){
        List<String> tokens = new ArrayList<>();

        int elementIndex = text.indexOf('<');

        return tokens;
    }
}
