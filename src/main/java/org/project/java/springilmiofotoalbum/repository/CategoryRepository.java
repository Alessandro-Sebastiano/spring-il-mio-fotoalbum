package org.project.java.springilmiofotoalbum.repository;

import org.project.java.springilmiofotoalbum.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
}
