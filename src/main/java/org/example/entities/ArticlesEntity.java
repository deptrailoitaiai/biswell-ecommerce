package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "Articles")
@Table(name = "Articles")
@Getter()
@Setter()
public class ArticlesEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "article_id")
    private UUID articleId;

    @Column(name = "article_name")
    @Lob()
    private String articleName;

    @Column(name = "summary", columnDefinition = "TEXT")
    private String summary;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "text")
    @Lob()
    private String text;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "article_images", joinColumns = @JoinColumn(name = "article_id"))
    @Column(name = "image_url")
    private List<String> images = new ArrayList<>();

    @Column(name = "create_at")
    @CreationTimestamp()
    private LocalDateTime createAt;
}
