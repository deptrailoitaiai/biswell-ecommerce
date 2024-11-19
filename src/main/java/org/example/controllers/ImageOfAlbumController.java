package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dtos.requests.ImageOfAlbumDtos.CreateImageOfAlbumDto;
import org.example.dtos.requests.ImageOfAlbumDtos.DeleteImageOfAlbumDto;
import org.example.dtos.requests.ImageOfAlbumDtos.UpdateImageOfAlbumDto;
import org.example.entities.ImagesOfAlbumEntity;
import org.example.services.ImagesOfAlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("/imageAlbum")
public class ImageOfAlbumController {
    @Autowired()
    private ImagesOfAlbumService imagesOfAlbumService;

    @Autowired()
    private ModelMapper entityNonNull;

    @GetMapping("/{id}")
    public Optional<ImagesOfAlbumEntity> getImageById(UUID imageId) {
        return imagesOfAlbumService.getImageById(imageId);
    }

    @PostMapping()
    public ImagesOfAlbumEntity createImage(@Valid() @RequestBody() CreateImageOfAlbumDto createImageOfAlbumDto) {
        ImagesOfAlbumEntity imagesOfAlbumEntity = entityNonNull.map(createImageOfAlbumDto, ImagesOfAlbumEntity.class);

        return imagesOfAlbumService.saveImage(imagesOfAlbumEntity);
    }

    @PatchMapping()
    public ImagesOfAlbumEntity updateImage(@Valid() @RequestBody() UpdateImageOfAlbumDto updateImageOfAlbumDto) {
        ImagesOfAlbumEntity imagesOfAlbumEntity = entityNonNull.map(updateImageOfAlbumDto, ImagesOfAlbumEntity.class);

        return imagesOfAlbumService.saveImage(imagesOfAlbumEntity);
    }

    @DeleteMapping()
    public void deleteImage(@Valid() @RequestBody()DeleteImageOfAlbumDto deleteImageOfAlbumDto) {
        imagesOfAlbumService.deleteImage(deleteImageOfAlbumDto.getImageId());
    }
}
