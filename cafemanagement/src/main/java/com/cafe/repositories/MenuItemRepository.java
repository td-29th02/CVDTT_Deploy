package com.cafe.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafe.entities.MenuItem;

@Repository  
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByCategoryNameAndIsAvailableTrue(String categoryName);
    List<MenuItem> findByDrinkIdAndIsAvailableTrue(Integer drinkId);
    Optional<MenuItem> findByDrinkIdAndCategoryId(Integer drinkId, Integer categoryId);
}
