package com.adk.markdown.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ReplacementDTO implements Serializable {
    @JsonProperty("value")
    private String value;
}
