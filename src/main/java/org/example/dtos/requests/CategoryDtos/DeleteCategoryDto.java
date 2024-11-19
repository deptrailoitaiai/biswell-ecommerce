package org.example.dtos.requests.CategoryDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data()
public class DeleteCategoryDto {
    @NotBlank()
    private UUID categoryId;
}
