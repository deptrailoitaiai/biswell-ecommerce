package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
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

    @Column(name = "text")
    @Lob()
    private String text;

    @Column(name = "create_at")
    @CreationTimestamp()
    private LocalDateTime createAt;
}
