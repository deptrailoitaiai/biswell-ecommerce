package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dtos.requests.AlbumDtos.CreateAlbumRequestDto;
import org.example.dtos.requests.AlbumDtos.DeleteAlbumRequestDto;
import org.example.dtos.requests.AlbumDtos.UpdateAlbumRequestDto;
import org.example.dtos.responses.AlbumDtos.CreateAlbumResponseDto;
import org.example.dtos.responses.AlbumDtos.GetAlbumResponseDto;
import org.example.dtos.responses.AlbumDtos.UpdateAlbumResponseDto;
import org.example.dtos.responses.GlobalResponseDto;
import org.example.entities.AlbumsEntity;
import org.example.services.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/album")
public class AlbumController {
    @Autowired()
    private AlbumService albumService;

    @Autowired()
    private ModelMapper objectAssign;

    @GetMapping("/id/{id}")
    public ResponseEntity<GlobalResponseDto<GetAlbumResponseDto>> getAlbumById(@PathVariable("id") UUID id) {
            AlbumsEntity albumsEntity = albumService.getAlbumById(id);

            GlobalResponseDto<GetAlbumResponseDto> response = new GlobalResponseDto<>(
                    true,
                    "Data retrieved successfully.",
                    objectAssign.map(albumsEntity, GetAlbumResponseDto.class)
            );

            return ResponseEntity.ok(response);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<GlobalResponseDto<GetAlbumResponseDto>> getAlbumByName(@PathVariable("name") String name) {
        AlbumsEntity albumsEntity = albumService.getAlbumByName(name);

        GlobalResponseDto<GetAlbumResponseDto> response = new GlobalResponseDto<>(
                true,
                "Data retrieved successfully.",
                objectAssign.map(albumsEntity, GetAlbumResponseDto.class)
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<GlobalResponseDto<CreateAlbumResponseDto>> createAlbum(@Valid() @RequestBody() CreateAlbumRequestDto createAlbumRequestDto) {
        AlbumsEntity albumsEntity = albumService.createAlbum(objectAssign.map(createAlbumRequestDto, AlbumsEntity.class));

        GlobalResponseDto<CreateAlbumResponseDto> response = new GlobalResponseDto<>(
                true,
                "Created successfully.",
                objectAssign.map(albumsEntity, CreateAlbumResponseDto.class)
        );

        return ResponseEntity.ok(response);
    }

    @PatchMapping()
    public ResponseEntity<GlobalResponseDto<UpdateAlbumResponseDto>> updateAlbum(@Valid() @RequestBody() UpdateAlbumRequestDto updateAlbumRequestDto) {
        AlbumsEntity albumsEntity = albumService.updateAlbum(objectAssign.map(updateAlbumRequestDto, AlbumsEntity.class));

        GlobalResponseDto<UpdateAlbumResponseDto> response = new GlobalResponseDto<>(
                true,
                "Updated successfully.",
                objectAssign.map(albumsEntity, UpdateAlbumResponseDto.class)
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping()
    public ResponseEntity<GlobalResponseDto<String>> deleteAlbum(@Valid() @RequestBody() DeleteAlbumRequestDto deleteAlbumRequestDto) {
        albumService.deleteAlbum(deleteAlbumRequestDto.getAlbumId());

        GlobalResponseDto<String> response = new GlobalResponseDto<>(
                true,
                "Deleted successfully."
        );

        return ResponseEntity.ok(response);
    }
}
