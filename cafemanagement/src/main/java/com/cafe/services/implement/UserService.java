package com.cafe.services.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cafe.dtos.users.ChangeRequest;
import com.cafe.dtos.users.CreateRequest;
import com.cafe.dtos.users.GetRequest;
import com.cafe.dtos.users.UpdateRequest;
import com.cafe.entities.User;
import com.cafe.exceptions.UserMessageException;
import com.cafe.global.ApiResult;
import com.cafe.repositories.UserRepository;
import com.cafe.services.interfaces.IUserService;

@Service
public class UserService implements IUserService {
        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        // Constructor injection
        public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
                this.userRepository = userRepository;
                this.passwordEncoder = passwordEncoder;
        }

        public ApiResult<List<GetRequest>> getUsers() {
                List<User> users = userRepository.findAll();

                List<GetRequest> userDtos = users.stream()
                                .map(user -> GetRequest.builder()
                                                .id(user.getId())
                                                .username(user.getUsername())
                                                .role(user.getRole())
                                                .createdDate(user.getCreatedDate())
                                                .build())
                                .collect(Collectors.toList());

                return ApiResult.success(userDtos, "Lấy danh sách người dùng thành công");
        }

        public ApiResult<GetRequest> getUserById(Integer id) {
                User user = userRepository.findById(id)
                                .orElseThrow(() -> new UserMessageException("Không tìm thấy người dùng với id: " + id));

                GetRequest userDto = GetRequest.builder()
                                .id(user.getId())
                                .username(user.getUsername())
                                .role(user.getRole())
                                .createdDate(user.getCreatedDate())
                                .build();

                return ApiResult.success(userDto, "Lấy thông tin người dùng thành công");
        }

        public ApiResult<String> createUser(CreateRequest dto) {
                if (userRepository.existsByUsername(dto.username)) {
                        throw new UserMessageException("Tên đăng nhập đã tồn tại");
                }

                String hashedPassword = passwordEncoder.encode(dto.password);

                User newUser = User.builder()
                                .username(dto.username)
                                .password(hashedPassword)
                                .role(dto.role)
                                .createdDate(dto.createdDate)
                                .build();

                userRepository.save(newUser);

                return ApiResult.success(null, "Thêm mới người dùng thành công");
        }

        public ApiResult<String> updateUser(Integer id, UpdateRequest dto) {
                User user = userRepository.findById(id)
                                .orElseThrow(() -> new UserMessageException("Không tìm thấy người dùng với id: " + id));

                user.setUsername(dto.username);
                user.setRole(dto.role);
                userRepository.save(user);

                return ApiResult.success(null, "Cập nhật người dùng thành công");
        }

        public ApiResult<String> deleteUser(Integer id) {
                User user = userRepository.findById(id)
                                .orElseThrow(() -> new UserMessageException("Không tìm thấy người dùng với id: " + id));

                userRepository.delete(user);

                return ApiResult.success(null, "Xóa người dùng thành công");
        }

        public ApiResult<String> changePassword(Integer id, ChangeRequest dto) {
                User user = userRepository.findById(id)
                                .orElseThrow(() -> new UserMessageException("Không tìm thấy người dùng với id: " + id));

                String hashedPassword = passwordEncoder.encode(dto.newPassword);

                user.setPassword(hashedPassword);
                userRepository.save(user);

                return ApiResult.success(null, "Đổi mật khẩu thành công");
        }
}