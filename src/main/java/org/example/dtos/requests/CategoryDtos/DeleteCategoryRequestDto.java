package org.example.dtos.requests.CategoryDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data()
public class DeleteCategoryRequestDto {
    @NotNull()
    private UUID categoryId;
}
