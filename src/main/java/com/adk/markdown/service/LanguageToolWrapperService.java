package com.adk.markdown.service;

import com.adk.markdown.model.languagetool.Language;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class LanguageToolWrapperService {

    private final Environment env;
    private String languageToolURL;
    private final ObjectMapper objectMapper;

    @Autowired
    LanguageToolWrapperService(Environment env, ObjectMapper objectMapper){
        this.env = env;
        this.objectMapper = objectMapper;
        languageToolURL = env.getProperty("language-tool-api.url");
    }

    public List<Language> getSupportedLanguages() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(languageToolURL + "/v2/languages"))
                .method("GET", HttpRequest.BodyPublishers.noBody()).build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

       return objectMapper.readValue(response.body(), List.class);

    }

}
