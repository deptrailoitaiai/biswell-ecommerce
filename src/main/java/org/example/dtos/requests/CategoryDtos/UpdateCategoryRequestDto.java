package org.example.dtos.requests.CategoryDtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;


@Data()
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateCategoryRequestDto {
    @NotNull()
    private UUID categoryId;

    private String categoryName;

    private String description;

    private String categoryImage;
}
