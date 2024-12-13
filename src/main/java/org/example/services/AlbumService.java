package org.example.services;

import org.example.entities.AlbumsEntity;
import org.example.exceptions.RuntimeExceptions.ExistedException;
import org.example.exceptions.RuntimeExceptions.NotExistsException;
import org.example.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service()
public class AlbumService {
    @Autowired()
    private AlbumRepository albumRepository;

    public AlbumsEntity getAlbumByName(String albumName) {
        return albumRepository.getAlbumByName(albumName)
                .orElseThrow(() -> new NotExistsException("Album '" + albumName + "' not exist"));
    }

    public AlbumsEntity getAlbumById(UUID albumId) {
        return albumRepository.findById(albumId)
                .orElseThrow(() -> new NotExistsException("Album '" + albumId + "' not exist"));
    }

    public AlbumsEntity createAlbum(AlbumsEntity albumsEntity) {
        if(albumRepository.existsByName(albumsEntity.getAlbumName())) {
            throw new ExistedException("Album '" + albumsEntity.getAlbumName() + "' existed");
        }

        return albumRepository.save(albumsEntity);
    }

    public AlbumsEntity updateAlbum(AlbumsEntity albumsEntity) {
        if (albumRepository.existsById(albumsEntity.getAlbumId())) {
            return albumRepository.save(albumsEntity);
        }

        throw new NotExistsException("Album '" + albumsEntity.getAlbumId() + "' not exist");
    }

    public void deleteAlbum(UUID albumId) {
        if (albumRepository.existsById(albumId)) {
            albumRepository.deleteById(albumId);
            return;
        }

        throw new NotExistsException("Album '" + albumId + "' not exist");
    }
}
