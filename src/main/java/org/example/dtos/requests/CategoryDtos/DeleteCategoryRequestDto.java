package org.example.dtos.requests.CategoryDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data()
public class DeleteCategoryRequestDto {
    @NotBlank()
    private UUID categoryId;
}
