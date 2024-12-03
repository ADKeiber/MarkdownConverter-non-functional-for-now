package com.adk.markdown.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class LanguageToolWrapperService {

    private Environment env;
    private String languageToolURL;

    @Autowired
    LanguageToolWrapperService(Environment env){
        this.env = env;
        languageToolURL = env.getProperty("language-tool-api.url");
    }


}
