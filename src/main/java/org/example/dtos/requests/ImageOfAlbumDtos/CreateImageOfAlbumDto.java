package org.example.dtos.requests.ImageOfAlbumDtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data()
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateImageOfAlbumDto {
    @NotBlank()
    private UUID albumId;

    @NotBlank()
    private String description;

    @NotBlank()
    private String image;
}
