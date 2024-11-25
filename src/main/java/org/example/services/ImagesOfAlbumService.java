package org.example.services;

import org.example.entities.ImagesOfAlbumEntity;
import org.example.exceptions.ImageOfAlbumExceptions.ImageOfAlbumNotExistException;
import org.example.repositories.ImagesOfAlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service()
public class ImagesOfAlbumService {
    @Autowired()
    private ImagesOfAlbumRepository imagesOfAlbumRepository;

    public ImagesOfAlbumEntity getImageById(UUID imageId) {
        return imagesOfAlbumRepository.findById(imageId)
                .orElseThrow(() -> new ImageOfAlbumNotExistException("image '" + imageId + "' not exist"));
    }

    public ImagesOfAlbumEntity createImage(ImagesOfAlbumEntity imagesOfAlbumEntity) {
        return imagesOfAlbumRepository.save(imagesOfAlbumEntity);
    }

    public ImagesOfAlbumEntity updateImage(ImagesOfAlbumEntity imagesOfAlbumEntity) {
        if (imagesOfAlbumRepository.existsById(imagesOfAlbumEntity.getImageId())){
            return imagesOfAlbumRepository.save(imagesOfAlbumEntity);
        }

        throw new ImageOfAlbumNotExistException("image '" + imagesOfAlbumEntity.getImageId() + "' not exist");
    }

    public void deleteImage(UUID imageId) {
        if(imagesOfAlbumRepository.existsById(imageId)) {
            imagesOfAlbumRepository.deleteById(imageId);
        }

        throw new ImageOfAlbumNotExistException("image '" + imageId + "' not exist");
    }
}
