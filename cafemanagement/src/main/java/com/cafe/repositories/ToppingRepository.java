package com.cafe.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafe.entities.Topping;

@Repository
public interface ToppingRepository extends JpaRepository<Topping, Integer> {
    // Find only active toppings
    List<Topping> findByIsActiveTrue();
    
    // Find active topping by name
    Optional<Topping> findByNameAndIsActiveTrue(String name);
    
    // Find active toppings by IDs
    List<Topping> findByIdInAndIsActiveTrue(List<Integer> ids);
    
    // Find all toppings ordered by name
    List<Topping> findAllByOrderByNameAsc();
    
    // Find all toppings ordered by isActive DESC, then by name ASC
    List<Topping> findAllByOrderByIsActiveDescNameAsc();
    
    // Check if topping name exists (both active and inactive)
    Optional<Topping> findByName(String name);
}