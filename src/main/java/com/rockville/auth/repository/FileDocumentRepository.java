package com.rockville.auth.repository;

import com.rockville.auth.model.domain.FileDocument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDocumentRepository extends CrudRepository<FileDocument, String> {
    List<FileDocument> findAllByIdIsNotNull();

}
