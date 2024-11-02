package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "ImagesOfAlbum")
@Table(name = "ImagesOfAlbum")
@Getter()
@Setter()
public class ImagesOfAlbumEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "image_id")
    private UUID imageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", referencedColumnName = "album_id")
    private AlbumsEntity albumsEntity;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;
}
