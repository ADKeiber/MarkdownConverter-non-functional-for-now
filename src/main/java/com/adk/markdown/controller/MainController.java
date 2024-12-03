package com.adk.markdown.controller;

import com.adk.markdown.service.LanguageToolWrapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MainController {

    @Autowired
    LanguageToolWrapperService languageToolWrapperService;

    @PostMapping("/checkGrammar")
    public ResponseEntity<Object> checkGrammar(@RequestParam("file") MultipartFile file) {

        return new ResponseEntity<>("postService.getAllPosts()", HttpStatus.OK);
    }


}
