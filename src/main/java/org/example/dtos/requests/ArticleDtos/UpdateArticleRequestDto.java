package org.example.dtos.requests.ArticleDtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data()
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateArticleRequestDto {
    @NotBlank()
    private UUID articleId;

    private String articleName;

    private String text;
}
