package com.adk.markdown.repo;

import com.adk.markdown.model.SavedFile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileRepo extends MongoRepository<SavedFile, String> {
}
