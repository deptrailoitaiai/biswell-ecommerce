package org.example.repositories;

import org.example.entities.AlbumsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository()
public interface AlbumRepository extends JpaRepository<AlbumsEntity, UUID> {
}
