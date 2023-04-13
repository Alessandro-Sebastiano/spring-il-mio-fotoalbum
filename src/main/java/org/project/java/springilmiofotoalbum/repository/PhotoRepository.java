package org.project.java.springilmiofotoalbum.repository;

import org.project.java.springilmiofotoalbum.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    
}
