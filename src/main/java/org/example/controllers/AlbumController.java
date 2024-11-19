package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dtos.requests.AlbumDtos.CreateAlbumDto;
import org.example.dtos.requests.AlbumDtos.DeleteAlbumDto;
import org.example.dtos.requests.AlbumDtos.UpdateAlbumDto;
import org.example.entities.AlbumsEntity;
import org.example.services.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public AlbumsEntity createAlbum(@Valid() @RequestBody() CreateAlbumDto createAlbumDto) {
        AlbumsEntity albumsEntity = entityNonNull.map(createAlbumDto, AlbumsEntity.class);

        return albumService.saveAlbum(albumsEntity);
    }

    @PatchMapping()
    public AlbumsEntity updateAlbum(@Valid() @RequestBody()UpdateAlbumDto updateAlbumDto) {
        AlbumsEntity albumsEntity = entityNonNull.map(updateAlbumDto, AlbumsEntity.class);

        return albumService.saveAlbum(albumsEntity);
    }

    @DeleteMapping()
    public void deleteAlbum(@Valid() @RequestBody()DeleteAlbumDto deleteAlbumDto) {
        albumService.deleteAlbum(deleteAlbumDto.getAlbumId());
    }
}
