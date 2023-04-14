package org.project.java.springilmiofotoalbum.controller;

import jakarta.validation.Valid;
import org.project.java.springilmiofotoalbum.exceptions.PhotoNotFoundException;
import org.project.java.springilmiofotoalbum.model.AlertMessages;
import org.project.java.springilmiofotoalbum.model.Photo;
import org.project.java.springilmiofotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String show(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Photo showPhoto = photoService.getById(id);
            model.addAttribute("photo", showPhoto);
            return "/photo/show";
        } catch (PhotoNotFoundException e) {
            redirectAttributes.addFlashAttribute("alert", new AlertMessages(AlertMessages.typeAlert.ERROR, "Photo not found"));
        }
        return "redirect:/photos";
    }


    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("photo", new Photo());
        return "/photo/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        boolean errors = bindingResult.hasErrors();

        if (errors) {
            return "/photo/create";
        }

        photoService.createPhoto(formPhoto);
        redirectAttributes.addFlashAttribute("alert", new AlertMessages(AlertMessages.typeAlert.SUCCESS, "Created photo"));
        return "redirect:/photos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Photo result = photoService.getById(id);
            model.addAttribute("photo", result);
            return "/photo/edit";
        } catch (PhotoNotFoundException e) {
            redirectAttributes.addFlashAttribute("alert", new AlertMessages(AlertMessages.typeAlert.ERROR, "Photo not found"));
        }
        return "redirect:/photos";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        boolean errors = bindingResult.hasErrors();

        if (errors) {
            return "/photo/create";
        }

        photoService.editPhoto(id, formPhoto);
        redirectAttributes.addFlashAttribute("alert", new AlertMessages(AlertMessages.typeAlert.SUCCESS, "Edited photo"));
        return "redirect:/photos/" + id;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            photoService.delete(id);
            redirectAttributes.addFlashAttribute("alert", new AlertMessages(AlertMessages.typeAlert.SUCCESS, "Deleted photo"));
        } catch (PhotoNotFoundException e) {
            redirectAttributes.addFlashAttribute("alert", new AlertMessages(AlertMessages.typeAlert.ERROR, "Photo not deleted, not found"));
        }
        return "redirect:/photos";
    }

}
