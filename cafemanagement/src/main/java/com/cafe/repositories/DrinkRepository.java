package com.cafe.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafe.entities.Drink;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Integer> {
    List<Drink> findByTypeAndIsActiveTrue(String type);
    List<Drink> findByIsActiveTrue();
    List<Drink> findAll(); // Để lấy tất cả drinks bao gồm cả inactive
    Optional<Drink> findByNameAndType(String name, String type);
    
    // Thêm các method mới nếu cần
    List<Drink> findByIsActive(Boolean isActive);
    List<Drink> findByTypeAndIsActive(String type, Boolean isActive);
    
    // Thêm method để sắp xếp tất cả drinks (active trước)
    List<Drink> findAllByOrderByIsActiveDescNameAsc();
    List<Drink> findByTypeOrderByIsActiveDescNameAsc(String type);
}