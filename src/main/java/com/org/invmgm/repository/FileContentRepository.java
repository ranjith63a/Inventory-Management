package com.org.invmgm.repository;

import com.org.invmgm.model.FileContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileContentRepository extends JpaRepository<FileContent, Long> {
}
