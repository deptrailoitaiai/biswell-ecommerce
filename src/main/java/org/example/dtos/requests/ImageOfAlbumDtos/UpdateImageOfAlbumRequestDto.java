package org.example.dtos.requests.ImageOfAlbumDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data()
public class UpdateImageOfAlbumRequestDto {
    @NotBlank()
    private UUID imageId;

    private String description;

    private String image;
}
