package org.example.dtos.responses.ArticleDtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data()
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetArticleResponseDto {
    private UUID articleId;
    private String articleName;
    private String text;
    private Date createAt;
}
