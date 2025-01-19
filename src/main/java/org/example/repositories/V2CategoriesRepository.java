package org.example.repositories;

import org.example.entities.V2Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface V2CategoriesRepository extends JpaRepository<V2Categories, Long> {

}
