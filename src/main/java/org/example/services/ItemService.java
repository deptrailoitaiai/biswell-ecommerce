package org.example.services;

import org.example.entities.ItemsEntity;
import org.example.exceptions.ItemExceptions.ItemExistedException;
import org.example.exceptions.ItemExceptions.ItemNotExistException;
import org.example.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service()
public class ItemService {
    @Autowired()
    private ItemRepository itemRepository;

    public ItemsEntity getItemById(UUID itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotExistException("Item '" + itemId + "' not exist"));
    }

    public ItemsEntity getItemByName(String itemName) {
        return itemRepository.getItemByName(itemName)
                .orElseThrow(() -> new ItemNotExistException("Item '" + itemName + "' not exist"));
    }

    public ItemsEntity createItem(ItemsEntity itemsEntity) {
        if(itemRepository.existsByName(itemsEntity.getItemName())) {
            throw new ItemExistedException("Item '" + itemsEntity.getItemName() + "' existed");
        }

        return itemRepository.save(itemsEntity);
    }

    public ItemsEntity updateItem(ItemsEntity itemsEntity) {
        if(itemRepository.existsById(itemsEntity.getItemId())) {
            return itemRepository.save(itemsEntity);
        }

        throw new ItemNotExistException("Item '" + itemsEntity.getItemId() + "' not exist");
    }

    public void deleteItem(UUID itemId) {
        if(itemRepository.existsById(itemId)) {
            itemRepository.deleteById(itemId);
            return;
        }

        throw new ItemNotExistException("Item '" + itemId + "' not exist");
    }
}
