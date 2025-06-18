package com.cafe.services.interfaces;

import java.util.List;

import com.cafe.dtos.users.ChangeRequest;
import com.cafe.dtos.users.CreateRequest;
import com.cafe.dtos.users.GetRequest;
import com.cafe.dtos.users.UpdateRequest;
import com.cafe.global.ApiResult;

public interface IUserService {
    ApiResult<List<GetRequest>> getUsers();

    ApiResult<GetRequest> getUserById(Integer id);

    ApiResult<String> createUser(CreateRequest dto);

    ApiResult<String> updateUser(Integer id, UpdateRequest dto);

    ApiResult<String> deleteUser(Integer id);

    ApiResult<String> changePassword(Integer id, ChangeRequest dto);
}
