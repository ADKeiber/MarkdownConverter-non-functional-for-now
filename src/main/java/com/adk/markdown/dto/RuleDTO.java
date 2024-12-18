package com.adk.markdown.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class RuleDTO implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("subId")
    private String subId;

    @JsonProperty("sourceFile")
    private String sourceFile;

    @JsonProperty("description")
    private String description;

    @JsonProperty("issueType")
    private String issueType;

    @JsonProperty("category")
    private CategoryDTO category;

    @JsonProperty("confidence")
    private String confidence;
}
