package org.example.dtos.requests.ItemDtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data()
public class DeleteItemRequestDto {
    @NotNull()
    private UUID itemId;
}
