package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dtos.requests.ItemDtos.CreateItemRequestDto;
import org.example.dtos.requests.ItemDtos.DeleteItemRequestDto;
import org.example.dtos.requests.ItemDtos.UpdateItemRequestDto;
import org.example.dtos.responses.GlobalResponseDto;
import org.example.dtos.responses.ItemDtos.CreateItemResponseDto;
import org.example.dtos.responses.ItemDtos.GetItemResponseDto;
import org.example.dtos.responses.ItemDtos.UpdateItemResponseDto;
import org.example.entities.ItemsEntity;
import org.example.services.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/item")
public class ItemController {
    @Autowired()
    private ItemService itemService;

    @Autowired()
    private ModelMapper objectAssign;

    @GetMapping("/id/{id}")
    public ResponseEntity<GlobalResponseDto<GetItemResponseDto>> getItemById(@PathVariable("id") UUID id) {
        ItemsEntity itemsEntity = itemService.getItemById(id);

        GlobalResponseDto<GetItemResponseDto> response = new GlobalResponseDto<>(
                true,
                "Data retrieved successfully.",
                objectAssign.map(itemsEntity, GetItemResponseDto.class)
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<GlobalResponseDto<GetItemResponseDto>> getItemByName(@PathVariable("name") String name) {
        ItemsEntity itemsEntity = itemService.getItemByName(name);

        GlobalResponseDto<GetItemResponseDto> response = new GlobalResponseDto<>(
                true,
                "Data retrieved successfully.",
                objectAssign.map(itemsEntity, GetItemResponseDto.class)
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<GlobalResponseDto<CreateItemResponseDto>> createItem(@Valid() @RequestBody() CreateItemRequestDto createItemRequestDto) {
        ItemsEntity itemsEntity = itemService.createItem(objectAssign.map(createItemRequestDto, ItemsEntity.class));

        GlobalResponseDto<CreateItemResponseDto> response = new GlobalResponseDto<>(
                true,
                "Created successfully.",
                objectAssign.map(itemsEntity, CreateItemResponseDto.class)
        );

        return ResponseEntity.ok(response);
    }

    @PatchMapping()
    public ResponseEntity<GlobalResponseDto<UpdateItemResponseDto>> updateitem(@Valid() @RequestBody() UpdateItemRequestDto updateItemRequestDto) {
        ItemsEntity itemsEntity = itemService.updateItem(objectAssign.map(updateItemRequestDto, ItemsEntity.class));

        GlobalResponseDto<UpdateItemResponseDto> response = new GlobalResponseDto<>(
                true,
                "Updated successfully.",
                objectAssign.map(itemsEntity, UpdateItemResponseDto.class)
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping()
    public ResponseEntity<GlobalResponseDto<String>> deleteItem(@Valid() @RequestBody() DeleteItemRequestDto deleteItemRequestDto) {
        itemService.deleteItem(deleteItemRequestDto.getItemId());

        GlobalResponseDto<String> response = new GlobalResponseDto<>(
                true,
                "Deleted successfully."
        );

        return ResponseEntity.ok(response);
    }
}
