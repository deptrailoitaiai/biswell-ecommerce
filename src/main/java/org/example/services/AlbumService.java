package org.example.services;

import org.example.entities.AlbumsEntity;
import org.example.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service()
public class AlbumService {
    @Autowired()
    private AlbumRepository albumRepository;

    public Optional<AlbumsEntity> getAlbumByName(String albumName) {
        return albumRepository.getAlbumByName(albumName);
    }

    public Optional<AlbumsEntity> getAlbumById(UUID albumId) {
        return albumRepository.findById(albumId);
    }

    public AlbumsEntity saveAlbum(AlbumsEntity albumsEntity) {
        return albumRepository.save(albumsEntity);
    }

    public void deleteAlbum(UUID albumId) {
        albumRepository.deleteById(albumId);
    }
}
