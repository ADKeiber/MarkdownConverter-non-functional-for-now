package com.adk.markdown.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class CategoryDTO implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;
}
