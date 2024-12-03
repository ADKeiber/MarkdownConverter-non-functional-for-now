package com.adk.markdown.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @PostMapping("/upload")
    public ResponseEntity<Object> uploadFile() {

        return new ResponseEntity<>("postService.getAllPosts()", HttpStatus.OK);
    }
}
