package org.example.dtos.responses.ItemDtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;

@Data()
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetItemResponseDto {
    private UUID itemId;
    private UUID categoryId;
    private String itemName;
    private String itemImage;
    private String description;
    private float price;
    private float salePrice;
    private boolean saleActive;
    private int sold;
}
