package org.project.java.springilmiofotoalbum.service;

import org.project.java.springilmiofotoalbum.exceptions.PhotoNotFoundException;
import org.project.java.springilmiofotoalbum.model.Photo;
import org.project.java.springilmiofotoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {

    @Autowired
    PhotoRepository photoRepository;

    public List<Photo> getAll() {
        return photoRepository.findAll(Sort.by("title"));
    }

    public Photo createPhoto(Photo formPhoto) {
        Photo storePhoto = new Photo();
        storePhoto.setTitle(formPhoto.getTitle());
        storePhoto.setDescription(formPhoto.getDescription());
        storePhoto.setUrl(formPhoto.getUrl());
        storePhoto.setVisible(formPhoto.isVisible());
        return photoRepository.save(storePhoto);
    }


    public Photo editPhoto(Integer id, Photo formPhoto) throws PhotoNotFoundException {
        Photo updatePhoto = getById(id);
        updatePhoto.setTitle(formPhoto.getTitle());
        updatePhoto.setDescription(formPhoto.getDescription());
        updatePhoto.setUrl(formPhoto.getUrl());
        updatePhoto.setVisible(formPhoto.isVisible());
        return photoRepository.save(updatePhoto);
    }

    public Photo getById(Integer id) throws PhotoNotFoundException {
        Optional<Photo> result = photoRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new PhotoNotFoundException(Integer.toString(id));
        }
    }

    public boolean delete(Integer id) {
        try {
            getById(id);
        } catch (PhotoNotFoundException e) {
            throw new PhotoNotFoundException(Integer.toString(id));
        }
        try {
            photoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
