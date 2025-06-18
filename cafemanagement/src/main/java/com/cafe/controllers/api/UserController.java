package com.cafe.controllers.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

import com.cafe.dtos.users.ChangeRequest;
import com.cafe.dtos.users.CreateRequest;
import com.cafe.dtos.users.GetRequest;
import com.cafe.dtos.users.UpdateRequest;
import com.cafe.global.ApiResult;
import com.cafe.services.interfaces.IUserService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/users")
@SecurityRequirement(name = "Bearer Token")
@Tag(name = "User Management")
@PreAuthorize("hasRole('ADMIN')")
public class UserController extends ApiBaseController {
    private final IUserService userService;

    // Constructor injection
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<ApiResult<List<GetRequest>>> getUsers() {
        return executeApiResult(() -> userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResult<GetRequest>> getUser(@PathVariable Integer id) {
        return executeApiResult(() -> userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResult<String>> createUser(@RequestBody CreateRequest userCreate) {
        return executeApiResult(() -> userService.createUser(userCreate));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResult<String>> updateUser(@PathVariable Integer id,
            @RequestBody UpdateRequest userUpdate) {
        return executeApiResult(() -> userService.updateUser(id, userUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResult<String>> deleteUser(@PathVariable Integer id) {
        return executeApiResult(() -> userService.deleteUser(id));
    }

    @PatchMapping("/{id}/change-password")
    public ResponseEntity<ApiResult<String>> changePassword(@PathVariable Integer id,
            @RequestBody ChangeRequest changeRequest) {
        return executeApiResult(() -> userService.changePassword(id, changeRequest));
    }
}