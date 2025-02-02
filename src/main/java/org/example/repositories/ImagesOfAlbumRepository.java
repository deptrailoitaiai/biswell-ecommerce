package org.example.repositories;

import org.example.entities.ImagesOfAlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository()
public interface ImagesOfAlbumRepository extends JpaRepository<ImagesOfAlbumEntity, UUID> {
}
