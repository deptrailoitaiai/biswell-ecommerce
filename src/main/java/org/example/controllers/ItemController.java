package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dtos.requests.ItemDtos.CreateItemDto;
import org.example.dtos.requests.ItemDtos.DeleteItemDto;
import org.example.dtos.requests.ItemDtos.UpdateItemDto;
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
    public ItemsEntity createItem(@Valid() @RequestBody() CreateItemDto createItemDto) {
        ItemsEntity itemsEntity = entityNonNull.map(createItemDto, ItemsEntity.class);

        return itemService.saveItem(itemsEntity);
    }

    @PatchMapping()
    public ItemsEntity updateItem(@Valid() @RequestBody() UpdateItemDto updateItemDto) {
        ItemsEntity itemsEntity = entityNonNull.map(updateItemDto, ItemsEntity.class);

        return itemService.saveItem(itemsEntity);
    }

    @DeleteMapping()
    public void deleteItem(@Valid() @RequestBody()DeleteItemDto deleteItemDto) {
        itemService.deleteItem(deleteItemDto.getItemId());
    }

}
