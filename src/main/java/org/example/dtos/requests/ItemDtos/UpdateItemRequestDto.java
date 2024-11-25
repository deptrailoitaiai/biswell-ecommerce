package org.example.dtos.requests.ItemDtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data()
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateItemRequestDto {
    @NotNull()
    private UUID categoryId;

    private String itemName;

    private String itemImage;

    private String description;

    private float price;

    private float salePrice;

    private boolean saleActive;

    private int sold;
}
