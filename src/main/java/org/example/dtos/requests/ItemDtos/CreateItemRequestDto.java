package org.example.dtos.requests.ItemDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data()
public class CreateItemRequestDto {
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
