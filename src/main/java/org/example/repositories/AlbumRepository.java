package org.example.repositories;

import org.example.entities.AlbumsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository()
public interface AlbumRepository extends JpaRepository<AlbumsEntity, UUID> {
    @Query(value = "SELECT a FROM Albums a WHERE a.albumName = :albumName")
    Optional<AlbumsEntity> getAlbumByName (@Param("albumName") String albumName);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Albums a WHERE a.albumName = :albumName")
    boolean existsByName(String albumName);
}
