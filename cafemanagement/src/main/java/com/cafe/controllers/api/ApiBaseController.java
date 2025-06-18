package com.cafe.controllers.api;

import java.util.function.Supplier;

import org.springframework.http.ResponseEntity;

import com.cafe.exceptions.UserMessageException;
import com.cafe.global.ApiResult;

public abstract class ApiBaseController {

    // Method execute với custom success message (giữ nguyên cho backward compatibility)
    protected <T> ResponseEntity<ApiResult<T>> execute(Supplier<T> supplier, String successMessage) {
        try {
            T data = supplier.get();
            return ResponseEntity.ok(ApiResult.success(data, successMessage));
        } catch (UserMessageException ex) {
            return ResponseEntity.badRequest().body(ApiResult.fail(ex.getMessage()));
        } catch (Exception ex) {
            ApiResult<T> error = new ApiResult<>();
            error.setStatus(false);
            error.setUserMessage("Đã xảy ra lỗi.");
            error.setInternalMessage(ex.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }

    // Method execute với default message (giữ nguyên cho backward compatibility)
    protected <T> ResponseEntity<ApiResult<T>> execute(Supplier<T> supplier) {
        return execute(supplier, "Thành công");
    }

    // Method mới cho việc xử lý ApiResult được tạo sẵn trong service
    protected <T> ResponseEntity<ApiResult<T>> executeApiResult(Supplier<ApiResult<T>> supplier) {
        try {
            ApiResult<T> result = supplier.get();
            return ResponseEntity.ok(result);
        } catch (UserMessageException ex) {
            return ResponseEntity.badRequest().body(ApiResult.fail(ex.getMessage()));
        } catch (Exception ex) {
            ApiResult<T> error = new ApiResult<>();
            error.setStatus(false);
            error.setUserMessage("Đã xảy ra lỗi.");
            error.setInternalMessage(ex.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }
}