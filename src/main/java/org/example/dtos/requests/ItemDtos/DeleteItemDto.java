package org.example.dtos.requests.ItemDtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data()
public class DeleteItemDto {
    @NotBlank()
    private UUID itemId;
}
