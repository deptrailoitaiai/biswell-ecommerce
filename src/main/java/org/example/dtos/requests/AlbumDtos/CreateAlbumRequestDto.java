package org.example.dtos.requests.AlbumDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data()
public class CreateAlbumRequestDto {
    @NotBlank()
    private String albumName;

    @NotBlank()
    private String albumImage;
}
