package org.project.java.springilmiofotoalbum.controller;

import jakarta.validation.Valid;
import org.project.java.springilmiofotoalbum.exceptions.PhotoNotFoundException;
import org.project.java.springilmiofotoalbum.model.Photo;
import org.project.java.springilmiofotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/photos")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @GetMapping
    public String index(Model model) {
        List<Photo> list = photoService.getAll();
        model.addAttribute("list", list);
        return "/photo/index";
    }

    @GetMapping("{id}")
    public String show(@PathVariable Integer id, Model model) {
        try {
            Photo showPhoto = photoService.getById(id);
            model.addAttribute("photo", showPhoto);
            return "/photo/show";
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("photo", new Photo());
        return "/photo/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult, Model model) {

        boolean errors = bindingResult.hasErrors();

        if (errors) {
            return "/photo/create";
        }

        photoService.createPhoto(formPhoto);
        return "redirect:/photos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        try {
            Photo result = photoService.getById(id);
            model.addAttribute("photo", result);
            return "/photo/edit";
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult) {
        boolean errors = bindingResult.hasErrors();

        if (errors) {
            return "/photo/create";
        }

        photoService.editPhoto(id, formPhoto);
        return "redirect:/photos";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        try {
            photoService.delete(id);
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/photos";
    }

}
