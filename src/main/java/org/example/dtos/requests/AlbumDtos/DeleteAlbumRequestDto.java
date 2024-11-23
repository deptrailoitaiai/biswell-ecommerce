package org.example.dtos.requests.AlbumDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data()
public class DeleteAlbumRequestDto {
    @NotBlank()
    private UUID albumId;
}
