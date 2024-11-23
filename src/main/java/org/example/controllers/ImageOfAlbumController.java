package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dtos.requests.ImageOfAlbumDtos.CreateImageOfAlbumRequestDto;
import org.example.dtos.requests.ImageOfAlbumDtos.DeleteImageOfAlbumRequestDto;
import org.example.dtos.requests.ImageOfAlbumDtos.UpdateImageOfAlbumRequestDto;
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
    public ImagesOfAlbumEntity createImage(@Valid() @RequestBody() CreateImageOfAlbumRequestDto createImageOfAlbumRequestDto) {
        ImagesOfAlbumEntity imagesOfAlbumEntity = entityNonNull.map(createImageOfAlbumRequestDto, ImagesOfAlbumEntity.class);

        return imagesOfAlbumService.saveImage(imagesOfAlbumEntity);
    }

    @PatchMapping()
    public ImagesOfAlbumEntity updateImage(@Valid() @RequestBody() UpdateImageOfAlbumRequestDto updateImageOfAlbumRequestDto) {
        ImagesOfAlbumEntity imagesOfAlbumEntity = entityNonNull.map(updateImageOfAlbumRequestDto, ImagesOfAlbumEntity.class);

        return imagesOfAlbumService.saveImage(imagesOfAlbumEntity);
    }

    @DeleteMapping()
    public void deleteImage(@Valid() @RequestBody() DeleteImageOfAlbumRequestDto deleteImageOfAlbumRequestDto) {
        imagesOfAlbumService.deleteImage(deleteImageOfAlbumRequestDto.getImageId());
    }
}
