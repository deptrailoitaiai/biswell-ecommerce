package org.example.dtos.requests.ArticleDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data()
public class CreateArticleRequestDto {
    @NotBlank()
    private String articleName;

    @NotBlank()
    private String text;
}
