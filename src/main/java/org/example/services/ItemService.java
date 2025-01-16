package org.example.services;

import java.util.UUID;

import org.example.entities.ItemsEntity;
import org.example.exceptions.RuntimeExceptions.ExistedException;
import org.example.exceptions.RuntimeExceptions.NotExistsException;
import org.example.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service()
public class ItemService {
    @Autowired()
    private ItemRepository itemRepository;

    public ItemsEntity getItemById(UUID itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new NotExistsException("Item '" + itemId + "' not exist"));
    }

    public ItemsEntity getItemByName(String itemName) {
        return itemRepository.getItemByName(itemName)
                .orElseThrow(() -> new NotExistsException("Item '" + itemName + "' not exist"));
    }

    public ItemsEntity createItem(ItemsEntity itemsEntity) {
        if(itemRepository.existsByName(itemsEntity.getItemName())) {
            throw new ExistedException("Item '" + itemsEntity.getItemName() + "' existed");
        }

        return itemRepository.save(itemsEntity);
    }

    public ItemsEntity updateItem(ItemsEntity itemsEntity) {
        if(itemRepository.existsById(itemsEntity.getItemId())) {
            return itemRepository.save(itemsEntity);
        }

        throw new NotExistsException("Item '" + itemsEntity.getItemId() + "' not exist");
    }

    public void deleteItem(UUID itemId) {
        if(itemRepository.existsById(itemId)) {
            itemRepository.deleteById(itemId);
            return;
        }

        throw new NotExistsException("Item '" + itemId + "' not exist");
    }
}
