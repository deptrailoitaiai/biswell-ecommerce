package org.example.services;

import org.example.entities.ItemsEntity;
import org.example.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service()
public class ItemService {
    @Autowired()
    private ItemRepository itemRepository;

    public Optional<ItemsEntity> getItemById(UUID itemId) {
        return itemRepository.findById(itemId);
    }

    public ItemsEntity saveItem(ItemsEntity itemsEntity) {
        return itemRepository.save(itemsEntity);
    }

    public void deleteItem(UUID itemId) {
        itemRepository.deleteById(itemId);
    }

    public Optional<ItemsEntity> getItemByName(String userName) {
        return itemRepository.getItemByName(userName);
    }
}
