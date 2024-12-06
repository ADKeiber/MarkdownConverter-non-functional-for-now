package com.adk.markdown.service;

import com.adk.markdown.dto.LanguageDTO;
import com.adk.markdown.dto.MatchDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LanguageToolWrapperService {

    private final Environment env;
    private String languageToolURL;
    private final ObjectMapper objectMapper;
    private HttpClient client;

    @Autowired
    LanguageToolWrapperService(Environment env, ObjectMapper objectMapper){
        this.env = env;
        this.objectMapper = objectMapper;
        languageToolURL = env.getProperty("language-tool-api.url");
        this.client = HttpClient.newHttpClient();
    }

    public List<LanguageDTO> getSupportedLanguages() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(languageToolURL + "/v2/languages"))
                .method("GET", HttpRequest.BodyPublishers.noBody()).build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

       return objectMapper.readValue(response.body(),  objectMapper.getTypeFactory().constructCollectionType(List.class, LanguageDTO.class));
    }

    public List<MatchDTO> checkGrammar(Map<String,String> params) throws IOException, InterruptedException {

        HttpRequest.BodyPublisher bp;
        //This is for markup option
        if( params.get("text") != null){
            /**
             * Need to process data into proper format
             * ex:
             *  'A <b>Let test the grammar</b>'
             *
             *  becomes
             *
             * {"annotation":[
             *  {"text": "A "},
             *  {"markup": "<b>"},
             *  {"text": "Let test the grammar"},
             *  {"markup": "</b>"}
             * ]}
             */
            String text = params.get("text");
        } else {
            bp = getParamsUrlEncoded(params);
        }
        HttpRequest request= HttpRequest.newBuilder()
                .uri(URI.create(languageToolURL + "/v2/check"))
                .headers("Content-Type", "application/x-www-form-urlencoded")
//                .POST(getParamsUrlEncoded(params))
//                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(),  objectMapper.getTypeFactory().constructCollectionType(List.class, HttpRequest.class));
    }

    private HttpRequest.BodyPublisher getParamsUrlEncoded(Map<String, String> parameters) {

        String urlEncoded = parameters.entrySet()
                .stream()
                .map(e -> e.getKey() + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));
        return HttpRequest.BodyPublishers.ofString(urlEncoded);
    }

}
