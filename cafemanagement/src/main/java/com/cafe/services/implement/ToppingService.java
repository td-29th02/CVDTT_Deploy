package com.cafe.services.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.dtos.topping.AddToppingsDto;
import com.cafe.dtos.topping.CreateToppingDto;
import com.cafe.dtos.topping.DrinkWithToppingsDto;
import com.cafe.dtos.topping.ToppingResponseDto;
import com.cafe.entities.Drink;
import com.cafe.entities.Topping;
import com.cafe.repositories.DrinkRepository;
import com.cafe.repositories.ToppingRepository;
import com.cafe.services.interfaces.IToppingService;
import com.cafe.services.patterns.decorator.DrinkItem;
import com.cafe.services.patterns.decorator.DrinkToppingBuilder;

@Service
public class ToppingService implements IToppingService {

    @Autowired
    private ToppingRepository toppingRepository;
    
    @Autowired
    private DrinkRepository drinkRepository;

    @Override
    public ToppingResponseDto createTopping(CreateToppingDto request) {
        // Check if topping with same name already exists (only check active toppings)
        if (toppingRepository.findByNameAndIsActiveTrue(request.getName()).isPresent()) {
            throw new IllegalArgumentException("Topping voi ten nay da ton tai");
        }

        // Create new topping
        Topping topping = new Topping();
        topping.setName(request.getName());
        topping.setPrice(request.getPrice());
        topping.setIsActive(request.getIsActive()); // Use value from request instead of hardcoded true
        
        // Save to database
        topping = toppingRepository.save(topping);
        
        return new ToppingResponseDto(topping);
    }

    @Override
    public List<ToppingResponseDto> getAllToppings() {
        // Return all toppings ordered by active status (active first), then by name
        return toppingRepository.findAllByOrderByIsActiveDescNameAsc()
                .stream()
                .map(ToppingResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public ToppingResponseDto getToppingById(Integer id) {
        Topping topping = toppingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Khong ton tai topping co id: " + id));
        
        return new ToppingResponseDto(topping);
    }

    @Override
    public ToppingResponseDto updateTopping(Integer id, CreateToppingDto request) {
        Topping existingTopping = toppingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Khong ton tai topping co id: " + id));

        // Check if another active topping with same name exists (only if we're making this topping active)
        if (request.getIsActive()) {
            toppingRepository.findByNameAndIsActiveTrue(request.getName())
                    .ifPresent(topping -> {
                        if (!topping.getId().equals(id)) {
                            throw new IllegalArgumentException("Topping voi ten nay da ton tai");
                        }
                    });
        }

        // Update topping properties
        existingTopping.setName(request.getName());
        existingTopping.setPrice(request.getPrice());
        existingTopping.setIsActive(request.getIsActive()); // Allow updating isActive status

        // Save updated topping
        existingTopping = toppingRepository.save(existingTopping);
        
        return new ToppingResponseDto(existingTopping);
    }

    @Override
    public void deleteTopping(Integer id) {
        Topping topping = toppingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Khong ton tai topping co id: " + id));
                
        toppingRepository.delete(topping);
    }

    @Override
    public DrinkWithToppingsDto addToppingsToDrink(AddToppingsDto request) {
        // Validate drink exists and is active
        Drink drink = drinkRepository.findById(request.getDrinkId())
                .orElseThrow(() -> new IllegalArgumentException("Khong ton tai do uong co id: " + request.getDrinkId()));
                
        if (!drink.getIsActive()) {
            throw new IllegalArgumentException("Do uong khong hoat dong");
        }

        // Validate all toppings exist and are active
        List<Topping> toppings = toppingRepository.findByIdInAndIsActiveTrue(request.getToppingIds());
        if (toppings.size() != request.getToppingIds().size()) {
            throw new IllegalArgumentException("Mot so topping khong ton tai hoac khong hoat dong");
        }

        // Use Decorator Pattern to build drink with toppings
        DrinkItem decoratedDrink = DrinkToppingBuilder.buildDrinkWithToppings(drink, toppings);

        // Convert toppings to response DTOs
        List<ToppingResponseDto> toppingDtos = toppings.stream()
                .map(ToppingResponseDto::new)
                .collect(Collectors.toList());

        // Return result
        return new DrinkWithToppingsDto(
            drink.getId(),
            drink.getName(),
            drink.getBasePrice(),
            toppingDtos,
            decoratedDrink.getPrice()
        );
    }
}