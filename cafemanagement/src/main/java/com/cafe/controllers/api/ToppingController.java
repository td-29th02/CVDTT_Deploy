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

import com.cafe.dtos.topping.AddToppingsDto;
import com.cafe.dtos.topping.CreateToppingDto;
import com.cafe.dtos.topping.DrinkWithToppingsDto;
import com.cafe.dtos.topping.ToppingResponseDto;
import com.cafe.global.ApiResult;
import com.cafe.services.interfaces.IToppingService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/toppings")
@SecurityRequirement(name = "Bearer Token")
@Tag(name = "Topping Management")
public class ToppingController extends ApiBaseController {
    private final IToppingService toppingService;
    
    public ToppingController(IToppingService toppingService) {
        this.toppingService = toppingService;
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResult<ToppingResponseDto>> createTopping(@Valid @RequestBody CreateToppingDto request) {
        return execute(() -> toppingService.createTopping(request), "Tạo topping thành công");
    }
    
    @GetMapping
    public ResponseEntity<ApiResult<List<ToppingResponseDto>>> getAllToppings() {
        return execute(() -> toppingService.getAllToppings(), "Lấy danh sách topping thành công");
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResult<ToppingResponseDto>> getToppingById(@PathVariable Integer id) {
        return execute(() -> toppingService.getToppingById(id), "Lấy thông tin topping thành công");
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResult<ToppingResponseDto>> updateTopping(@PathVariable Integer id, @Valid @RequestBody CreateToppingDto request) {
        return execute(() -> toppingService.updateTopping(id, request), "Cập nhật topping thành công");
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResult<Void>> deleteTopping(@PathVariable Integer id) {
        return execute(() -> {
            toppingService.deleteTopping(id);
            return null;
        }, "Xóa topping thành công");
    }

    @PostMapping("/add-to-drink")
    public ResponseEntity<ApiResult<DrinkWithToppingsDto>> addToppingsToDrink(@Valid @RequestBody AddToppingsDto request) {
        return execute(() -> toppingService.addToppingsToDrink(request), "Thêm topping vào đồ uống thành công");
    }
}