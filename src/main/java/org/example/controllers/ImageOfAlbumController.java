package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dtos.requests.ImageOfAlbumDtos.CreateImageOfAlbumRequestDto;
import org.example.dtos.requests.ImageOfAlbumDtos.DeleteImageOfAlbumRequestDto;
import org.example.dtos.requests.ImageOfAlbumDtos.UpdateImageOfAlbumRequestDto;
import org.example.dtos.responses.GlobalResponseDto;
import org.example.dtos.responses.ImageOfAlbumDtos.CreateImageOfAlbumResponseDto;
import org.example.dtos.responses.ImageOfAlbumDtos.GetImageOfAlbumResponseDto;
import org.example.dtos.responses.ImageOfAlbumDtos.UpdateImageOfAlbumResponseDto;
import org.example.entities.ImagesOfAlbumEntity;
import org.example.services.ImagesOfAlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("/imageAlbum")
public class ImageOfAlbumController {
    @Autowired()
    private ImagesOfAlbumService imagesOfAlbumService;

    @Autowired()
    private ModelMapper objectAssign;

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponseDto<GetImageOfAlbumResponseDto>> getImageById(UUID imageId) {
        ImagesOfAlbumEntity imagesOfAlbumEntity = imagesOfAlbumService.getImageById(imageId);

        GlobalResponseDto<GetImageOfAlbumResponseDto> response = new GlobalResponseDto<>(
                true,
                "Data retrieved successfully.",
                objectAssign.map(imagesOfAlbumEntity, GetImageOfAlbumResponseDto.class)
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<GlobalResponseDto<CreateImageOfAlbumResponseDto>> createImage(@Valid() @RequestBody() CreateImageOfAlbumRequestDto createImageOfAlbumRequestDto) {
        ImagesOfAlbumEntity imagesOfAlbumEntity = imagesOfAlbumService.createImage(objectAssign.map(createImageOfAlbumRequestDto, ImagesOfAlbumEntity.class));

        GlobalResponseDto<CreateImageOfAlbumResponseDto> response = new GlobalResponseDto<>(
                true,
                "Created successfully.",
                objectAssign.map(imagesOfAlbumEntity, CreateImageOfAlbumResponseDto.class)
        );

        return ResponseEntity.ok(response);
    }

    @PatchMapping()
    public ResponseEntity<GlobalResponseDto<UpdateImageOfAlbumResponseDto>> updateImage(@Valid() @RequestBody() UpdateImageOfAlbumRequestDto updateImageOfAlbumRequestDto) {
        ImagesOfAlbumEntity imagesOfAlbumEntity = imagesOfAlbumService.updateImage(objectAssign.map(updateImageOfAlbumRequestDto, ImagesOfAlbumEntity.class));

        GlobalResponseDto<UpdateImageOfAlbumResponseDto> response = new GlobalResponseDto<>(
                true,
                "Updated successfully.",
                objectAssign.map(imagesOfAlbumEntity, UpdateImageOfAlbumResponseDto.class)
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping()
    public ResponseEntity<GlobalResponseDto<String>> deleteImage(@Valid() @RequestBody() DeleteImageOfAlbumRequestDto deleteImageOfAlbumRequestDto) {
        imagesOfAlbumService.deleteImage(deleteImageOfAlbumRequestDto.getImageId());

        GlobalResponseDto<String> response = new GlobalResponseDto<>(
                true,
                "Deleted successfully."
        );

        return ResponseEntity.ok(response);
    }
}
