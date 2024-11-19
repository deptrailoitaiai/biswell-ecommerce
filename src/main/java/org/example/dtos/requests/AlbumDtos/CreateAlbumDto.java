package org.example.dtos.requests.AlbumDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data()
public class CreateAlbumDto {
    @NotBlank()
    private String albumName;

    @NotBlank()
    private String albumImage;
}
