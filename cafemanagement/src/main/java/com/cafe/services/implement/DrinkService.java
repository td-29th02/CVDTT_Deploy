package com.cafe.services.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.dtos.drinks.CreateDrinkDto;
import com.cafe.dtos.drinks.DrinkResponseDto;
import com.cafe.entities.Drink;
import com.cafe.repositories.DrinkRepository;
import com.cafe.services.interfaces.IDrinkService;

@Service
public class DrinkService implements IDrinkService {

    @Autowired
    private DrinkRepository drinkRepository;

    @Override
    public DrinkResponseDto createDrink(CreateDrinkDto request) {
        // Check if drink with same name and type already exists (chỉ check active
        // drinks)
        drinkRepository.findByNameAndType(request.getName(), request.getType())
                .ifPresent(drink -> {
                    if (drink.getIsActive()) {
                        throw new IllegalArgumentException("Do uong voi ten va loai nay da ton tai");
                    }
                });

        // Tạo drink mới với tất cả thông tin từ request
        Drink drink = new Drink();
        drink.setName(request.getName());
        drink.setType(request.getType());
        drink.setBasePrice(request.getBasePrice());
        drink.setDescription(request.getDescription());
        drink.setBrewingMethod(request.getBrewingMethod());
        drink.setOrigin(request.getOrigin());
        drink.setCaffeineLevel(request.getCaffeineLevel());
        drink.setIsActive(request.getIsActive() != null ? request.getIsActive() : true);

        // Save to database
        drink = drinkRepository.save(drink);

        return new DrinkResponseDto(drink);
    }

    @Override
    public List<DrinkResponseDto> getAllDrinks() {
        // Trả về TẤT CẢ drinks (cả active và inactive), sắp xếp active trước
        return drinkRepository.findAllByOrderByIsActiveDescNameAsc()
                .stream()
                .map(DrinkResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<DrinkResponseDto> getAllDrinksIncludeInactive() {
        // Method này cũng trả về tất cả drinks (giống getAllDrinks)
        return getAllDrinks();
    }

    // Method để chỉ lấy drinks active (cho customer nếu cần)
    public List<DrinkResponseDto> getActiveDrinks() {
        return drinkRepository.findByIsActiveTrue()
                .stream()
                .map(DrinkResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<DrinkResponseDto> getDrinksByType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Loai do uong khong duoc de trong");
        }

        // Trả về tất cả drinks theo type (cả active và inactive)
        return drinkRepository.findByTypeOrderByIsActiveDescNameAsc(type)
                .stream()
                .map(DrinkResponseDto::new)
                .collect(Collectors.toList());
    }

    // Method để chỉ lấy drinks active theo type (cho customer nếu cần)
    public List<DrinkResponseDto> getActiveDrinksByType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Loai do uong khong duoc de trong");
        }

        return drinkRepository.findByTypeAndIsActiveTrue(type)
                .stream()
                .map(DrinkResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public DrinkResponseDto getDrinkById(Integer id) {
        Drink drink = drinkRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Khong ton tai do uong co id: " + id));

        return new DrinkResponseDto(drink);
    }

    @Override
    public DrinkResponseDto updateDrink(Integer id, CreateDrinkDto request) {
        Drink existingDrink = drinkRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Khong ton tai do uong co id: " + id));

        // Check if another active drink with same name and type exists
        drinkRepository.findByNameAndType(request.getName(), request.getType())
                .ifPresent(drink -> {
                    if (!drink.getId().equals(id) && drink.getIsActive()) {
                        throw new IllegalArgumentException("Do uong voi ten va loai nay da ton tai");
                    }
                });

        // Update tất cả các trường
        existingDrink.setName(request.getName());
        existingDrink.setType(request.getType());
        existingDrink.setBasePrice(request.getBasePrice());
        existingDrink.setDescription(request.getDescription());
        existingDrink.setBrewingMethod(request.getBrewingMethod());
        existingDrink.setOrigin(request.getOrigin());
        existingDrink.setCaffeineLevel(request.getCaffeineLevel());
        existingDrink.setIsActive(request.getIsActive() != null ? request.getIsActive() : existingDrink.getIsActive());

        // Save updated drink
        existingDrink = drinkRepository.save(existingDrink);

        return new DrinkResponseDto(existingDrink);
    }

    @Override
    public void deleteDrink(Integer id) {
        Drink drink = drinkRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Khong ton tai do uong co id: " + id));

        // Hard delete - xóa hẳn khỏi database
        drinkRepository.delete(drink);
    }

    @Override
    public DrinkResponseDto restoreDrink(Integer id) {
        Drink drink = drinkRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Khong ton tai do uong co id: " + id));

        if (drink.getIsActive()) {
            throw new IllegalArgumentException("Do uong dang hoat dong");
        }

        // Restore drink by setting isActive to true
        drink.setIsActive(true);
        drink = drinkRepository.save(drink);

        return new DrinkResponseDto(drink);
    }

    @Override
    public void permanentDeleteDrink(Integer id) {
        Drink drink = drinkRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Khong ton tai do uong co id: " + id));

        // Hard delete
        drinkRepository.delete(drink);
    }
}