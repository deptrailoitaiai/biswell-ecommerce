package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity(name = "Albums")
@Table(name = "Albums")
@Getter()
@Setter()
public class AlbumsEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "album_id")
    private UUID albumId;

    @Column(name = "album_name")
    private String albumName;

    @Column(name = "album_image")
    private String albumImage;

    @OneToMany(mappedBy = "albumsEntity", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<ImagesOfAlbumEntity> imagesEntity;
}
