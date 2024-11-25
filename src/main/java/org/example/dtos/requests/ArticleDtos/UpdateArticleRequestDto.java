package org.example.dtos.requests.ArticleDtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data()
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateArticleRequestDto {
    @NotNull()
    private UUID articleId;

    private String articleName;

    private String text;
}
