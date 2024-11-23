package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dtos.requests.AlbumDtos.CreateAlbumRequestDto;
import org.example.dtos.requests.AlbumDtos.DeleteAlbumRequestDto;
import org.example.dtos.requests.AlbumDtos.UpdateAlbumRequestDto;
import org.example.entities.AlbumsEntity;
import org.example.services.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("/album")
public class AlbumController {
    @Autowired()
    private AlbumService albumService;

    @Autowired()
    private ModelMapper entityNonNull;

    @GetMapping("/id/{id}")
    public Optional<AlbumsEntity> getAlbumById(@PathVariable("id") UUID id) {
        return albumService.getAlbumById(id);
    }

    @GetMapping("/name/{name}")
    public Optional<AlbumsEntity> getAlbumByName(@PathVariable("name") String name) {
        return albumService.getAlbumByName(name);
    }

    @PostMapping()
    public AlbumsEntity createAlbum(@Valid() @RequestBody() CreateAlbumRequestDto createAlbumRequestDto) {
        AlbumsEntity albumsEntity = entityNonNull.map(createAlbumRequestDto, AlbumsEntity.class);

        return albumService.saveAlbum(albumsEntity);
    }

    @PatchMapping()
    public AlbumsEntity updateAlbum(@Valid() @RequestBody() UpdateAlbumRequestDto updateAlbumRequestDto) {
        AlbumsEntity albumsEntity = entityNonNull.map(updateAlbumRequestDto, AlbumsEntity.class);

        return albumService.saveAlbum(albumsEntity);
    }

    @DeleteMapping()
    public void deleteAlbum(@Valid() @RequestBody() DeleteAlbumRequestDto deleteAlbumRequestDto) {
        albumService.deleteAlbum(deleteAlbumRequestDto.getAlbumId());
    }
}
