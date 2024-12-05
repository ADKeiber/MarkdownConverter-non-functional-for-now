package com.adk.markdown.controller;

import com.adk.markdown.service.LanguageToolWrapperService;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class MainController {

    @Autowired
    LanguageToolWrapperService languageToolWrapperService;

    @GetMapping("/supportedLanguages")
    public ResponseEntity<Object> getSupportedLanguages() throws IOException, InterruptedException {

        return new ResponseEntity<>(languageToolWrapperService.getSupportedLanguages(), HttpStatus.OK);
    }


    @GetMapping("/grammarCheck") //TODO update
    public ResponseEntity<Object> processPlainText() throws IOException, InterruptedException {

        return new ResponseEntity<>(languageToolWrapperService.getSupportedLanguages(), HttpStatus.OK);
    }

}
