package org.example.dtos.responses.CategoryDtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;

@Data()
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetCategoryResponseDto {
    private UUID categoryId;
    private String categoryName;
    private String description;
    private String categoryImage;
}
