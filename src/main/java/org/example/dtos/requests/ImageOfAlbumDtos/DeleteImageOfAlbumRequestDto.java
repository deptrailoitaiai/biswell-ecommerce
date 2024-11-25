package org.example.dtos.requests.ImageOfAlbumDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data()
public class DeleteImageOfAlbumRequestDto {
    @NotNull()
    private UUID imageId;
}
