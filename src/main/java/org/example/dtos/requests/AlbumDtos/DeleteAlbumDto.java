package org.example.dtos.requests.AlbumDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data()
public class DeleteAlbumDto {
    @NotBlank()
    private UUID albumId;
}
