package org.example.dtos.responses.ImageOfAlbumDtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;

@Data()
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetImageOfAlbumResponseDto {
    private UUID imageId;
    private UUID albumId;
    private String description;
    private String image;
}
