package org.project.java.springilmiofotoalbum.api;

import org.project.java.springilmiofotoalbum.exceptions.PhotoNotFoundException;
import org.project.java.springilmiofotoalbum.model.Photo;
import org.project.java.springilmiofotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/photos")
public class PhotoRestController {
    @Autowired
    private PhotoService photoService;

    @GetMapping
    public List<Photo> index(@RequestParam(name = "q") Optional<String> keyword) {

        if (keyword.isPresent()) {
            return photoService.getAllFiltered(keyword.get());
        }
        return photoService.getAll();

    }

    @GetMapping("/{id}")
    public Photo show(@PathVariable Integer id) {
        try {
            return photoService.getById(id);
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
