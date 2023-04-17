package org.project.java.springilmiofotoalbum.service;

import org.project.java.springilmiofotoalbum.exceptions.CategoryNotFoundException;
import org.project.java.springilmiofotoalbum.model.Category;
import org.project.java.springilmiofotoalbum.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll(Sort.by("id"));
    }

    public Category createCategory(Category formCategory) {
        Category storeCategory = new Category();
        storeCategory.setType(formCategory.getType());
        return categoryRepository.save(storeCategory);
    }

    public Category editCategory(Integer id, Category formCategory) throws CategoryNotFoundException {
        Category updateCategory = getById(id);
        updateCategory.setType(formCategory.getType());
        return categoryRepository.save(updateCategory);
    }

    public Category getById(Integer id) throws CategoryNotFoundException {
        Optional<Category> result = categoryRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new CategoryNotFoundException(Integer.toString(id));
        }
    }

    public boolean delete(Integer id) {
        try {
            getById(id);
        } catch (CategoryNotFoundException e) {
            throw new CategoryNotFoundException(Integer.toString(id));
        }
        try {
            categoryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
