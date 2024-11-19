package org.example.dtos.requests.ItemDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data()
public class CreateItemDto {
    @NotBlank()
    private String itemName;

    @NotBlank()
    private String itemImage;

    @NotBlank()
    private String description;

    @NotBlank()
    private float price;

    @NotBlank()
    private float salePrice;

    @NotBlank()
    private boolean saleActive;
}
