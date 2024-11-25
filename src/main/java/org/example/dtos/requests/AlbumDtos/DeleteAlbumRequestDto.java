package org.example.dtos.requests.AlbumDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data()
public class DeleteAlbumRequestDto {
    @NotNull()
    private UUID albumId;
}
