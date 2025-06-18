package com.cafe.controllers.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.dtos.drinks.CreateDrinkDto;
import com.cafe.dtos.drinks.DrinkResponseDto;
import com.cafe.global.ApiResult;
import com.cafe.services.interfaces.IDrinkService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/drinks")
@SecurityRequirement(name = "Bearer Token")
@Tag(name = "Drink Management")
public class DrinkController extends ApiBaseController {
    private final IDrinkService drinkService;
    
    public DrinkController(IDrinkService drinkService) {
        this.drinkService = drinkService;
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResult<DrinkResponseDto>> createDrink(@Valid @RequestBody CreateDrinkDto request) {
        return execute(() -> drinkService.createDrink(request), "Tạo đồ uống thành công");
    }
    
    @GetMapping
    public ResponseEntity<ApiResult<List<DrinkResponseDto>>> getAllDrinks() {
        return execute(() -> drinkService.getAllDrinks(), "Lấy danh sách đồ uống thành công");
    }
    
    @GetMapping("/type/{type}")
    public ResponseEntity<ApiResult<List<DrinkResponseDto>>> getDrinksByType(@PathVariable String type) {
        return execute(() -> drinkService.getDrinksByType(type), "Lấy danh sách đồ uống theo loại thành công");
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResult<DrinkResponseDto>> getDrinkById(@PathVariable Integer id) {
        return execute(() -> drinkService.getDrinkById(id), "Lấy thông tin đồ uống thành công");
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResult<DrinkResponseDto>> updateDrink(@PathVariable Integer id, @Valid @RequestBody CreateDrinkDto request) {
        return execute(() -> drinkService.updateDrink(id, request), "Cập nhật đồ uống thành công");
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResult<Void>> deleteDrink(@PathVariable Integer id) {
        return execute(() -> {
            drinkService.deleteDrink(id);
            return null;
        }, "Xóa đồ uống thành công");
    }
}