package com.adk.markdown.controller;

import com.adk.markdown.service.LanguageToolWrapperService;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {

    @Autowired
    LanguageToolWrapperService languageToolWrapperService;

    @GetMapping("/supportedLanguages")
    public ResponseEntity<Object> getSupportedLanguages() throws IOException, InterruptedException {

        return new ResponseEntity<>(languageToolWrapperService.getSupportedLanguages(), HttpStatus.OK);
    }

    @PostMapping(path = "/grammarCheck/plainText",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> processPlainText(String text, String language) throws IOException, InterruptedException {
        Map<String, String> params = new HashMap<>();
        params.put("text", text);
        params.put("language", language);
        return new ResponseEntity<>(languageToolWrapperService.checkGrammar(params), HttpStatus.OK);
    }

    @PostMapping(path = "/grammarCheck/markup",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> processMarkdown(String data, String language) throws IOException, InterruptedException {
        Map<String, String> params = new HashMap<>();
        params.put("text", data);
        params.put("language", language);
        return new ResponseEntity<>(languageToolWrapperService.checkGrammar(params), HttpStatus.OK);
    }

}
