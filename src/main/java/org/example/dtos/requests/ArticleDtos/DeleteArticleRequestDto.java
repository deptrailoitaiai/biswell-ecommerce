package org.example.dtos.requests.ArticleDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data()
public class DeleteArticleRequestDto {
    @NotNull()
    private UUID articleId;
}
