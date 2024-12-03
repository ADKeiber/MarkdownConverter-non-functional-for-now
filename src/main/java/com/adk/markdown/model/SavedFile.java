package com.adk.markdown.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class SavedFile {

    @Id
    String id;

}
