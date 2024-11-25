package org.example.dtos.requests.ItemDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data()
public class CreateItemRequestDto {
    @NotBlank()
    private String itemName;

    @NotBlank()
    private String itemImage;

    @NotBlank()
    private String description;

    @NotNull()
    private float price;

    @NotNull()
    private float salePrice;

    @NotNull()
    private boolean saleActive;
}
