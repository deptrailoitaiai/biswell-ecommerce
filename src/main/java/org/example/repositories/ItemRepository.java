package org.example.repositories;

import org.example.entities.ItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository()
public interface ItemRepository extends JpaRepository<ItemsEntity, UUID> {
    @Query(value = "SELECT * FROM Items WHERE item_name = :itemName")
    Optional<ItemsEntity> getItemByName(@Param("itemName") String itemName);
}
