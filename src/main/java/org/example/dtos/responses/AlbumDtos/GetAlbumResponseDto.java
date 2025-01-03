package org.example.dtos.responses.AlbumDtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;

@Data()
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetAlbumResponseDto {
    private UUID albumId;
    private String albumName;
    private String albumImage;
}
