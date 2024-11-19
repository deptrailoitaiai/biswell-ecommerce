package org.example.services;

import org.example.entities.ImagesOfAlbumEntity;
import org.example.repositories.ImagesOfAlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service()
public class ImagesOfAlbumService {
    @Autowired()
    private ImagesOfAlbumRepository imagesOfAlbumRepository;

    public Optional<ImagesOfAlbumEntity> getImageById(UUID imageId) {
        return imagesOfAlbumRepository.findById(imageId);
    }

    public ImagesOfAlbumEntity saveImage(ImagesOfAlbumEntity imagesOfAlbumEntity) {
        return imagesOfAlbumRepository.save(imagesOfAlbumEntity);
    }

    public void deleteImage(UUID imageId) {
        imagesOfAlbumRepository.deleteById(imageId);
    }
}
