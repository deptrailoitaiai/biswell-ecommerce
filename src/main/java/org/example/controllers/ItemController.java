package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dtos.requests.ItemDtos.CreateItemRequestDto;
import org.example.dtos.requests.ItemDtos.DeleteItemRequestDto;
import org.example.dtos.requests.ItemDtos.UpdateItemRequestDto;
import org.example.entities.ItemsEntity;
import org.example.services.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("/item")
public class ItemController {
    @Autowired()
    private ItemService itemService;

    @Autowired()
    private ModelMapper entityNonNull;

    @GetMapping("id/{id}")
    public Optional<ItemsEntity> getItemById(@PathVariable() UUID itemId) {
        return itemService.getItemById(itemId);
    }

    @GetMapping("name/{name}")
    public Optional<ItemsEntity> getItemByName(@PathVariable() String itemName) {
        return itemService.getItemByName(itemName);
    }

    @PostMapping()
    public ItemsEntity createItem(@Valid() @RequestBody() CreateItemRequestDto createItemRequestDto) {
        ItemsEntity itemsEntity = entityNonNull.map(createItemRequestDto, ItemsEntity.class);

        return itemService.saveItem(itemsEntity);
    }

    @PatchMapping()
    public ItemsEntity updateItem(@Valid() @RequestBody() UpdateItemRequestDto updateItemRequestDto) {
        ItemsEntity itemsEntity = entityNonNull.map(updateItemRequestDto, ItemsEntity.class);

        return itemService.saveItem(itemsEntity);
    }

    @DeleteMapping()
    public void deleteItem(@Valid() @RequestBody() DeleteItemRequestDto deleteItemRequestDto) {
        itemService.deleteItem(deleteItemRequestDto.getItemId());
    }

}
