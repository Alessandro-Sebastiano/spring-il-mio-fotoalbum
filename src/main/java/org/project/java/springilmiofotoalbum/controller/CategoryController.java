package org.project.java.springilmiofotoalbum.controller;

import jakarta.validation.Valid;
import org.project.java.springilmiofotoalbum.exceptions.CategoryNotFoundException;
import org.project.java.springilmiofotoalbum.model.AlertMessages;
import org.project.java.springilmiofotoalbum.model.Category;
import org.project.java.springilmiofotoalbum.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String index(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("category", new Category());
        return "/categories/index";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("category") Category formCategory, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        boolean errors = bindingResult.hasErrors();

        if (errors) {
            List<Category> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            return "/categories/index";
        }

        categoryService.createCategory(formCategory);
        redirectAttributes.addFlashAttribute("alert", new AlertMessages(AlertMessages.typeAlert.SUCCESS, "Created category"));
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Category result = categoryService.getById(id);
            model.addAttribute("category", result);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "/categories/index";
        } catch (CategoryNotFoundException e) {
            redirectAttributes.addFlashAttribute("alert", new AlertMessages(AlertMessages.typeAlert.ERROR, "Category not found"));
        }
        return "redirect:/categories";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("category") Category formCategory, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        boolean errors = bindingResult.hasErrors();

        if (errors) {
            List<Category> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            return "/categories/index";
        }

        categoryService.editCategory(id, formCategory);
        redirectAttributes.addFlashAttribute("alert", new AlertMessages(AlertMessages.typeAlert.SUCCESS, "Edited category"));
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            categoryService.delete(id);
            redirectAttributes.addFlashAttribute("alert", new AlertMessages(AlertMessages.typeAlert.SUCCESS, "Deleted category"));
        } catch (CategoryNotFoundException e) {
            redirectAttributes.addFlashAttribute("alert", new AlertMessages(AlertMessages.typeAlert.ERROR, "Category not deleted, not found"));
        }
        return "redirect:/categories";
    }

}
