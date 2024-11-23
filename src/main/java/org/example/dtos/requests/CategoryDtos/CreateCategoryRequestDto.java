package org.example.dtos.requests.CategoryDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data()
public class CreateCategoryRequestDto {
    @NotBlank()
    private String categoryName;

    @NotBlank()
    private String description;

    @NotBlank()
    private String categoryImage;
}
