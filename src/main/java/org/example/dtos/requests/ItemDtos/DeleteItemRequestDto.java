package org.example.dtos.requests.ItemDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data()
public class DeleteItemRequestDto {
    @NotBlank()
    private UUID itemId;
}
