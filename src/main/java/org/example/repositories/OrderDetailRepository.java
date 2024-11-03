package org.example.repositories;

import org.example.entities.OrderDetailsEntity;
import org.example.entities.embeddables.OrderDetailsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface OrderDetailRepository extends JpaRepository<OrderDetailsEntity, OrderDetailsId> {
}
