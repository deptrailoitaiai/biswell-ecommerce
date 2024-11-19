package org.example.dtos.requests.ArticleDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data()
public class DeleteArticleDto {
    @NotBlank()
    private UUID articleId;
}
