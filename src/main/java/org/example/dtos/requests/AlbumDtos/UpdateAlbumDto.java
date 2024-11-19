package org.example.dtos.requests.AlbumDtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data()
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateAlbumDto {
    @NotBlank()
    private UUID albumId;

    private String albumName;

    private String albumImage;
}
