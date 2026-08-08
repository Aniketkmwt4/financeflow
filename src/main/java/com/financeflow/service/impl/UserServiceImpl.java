package com.financeflow.service.impl;



import com.financeflow.dto.profile.ChangePasswordRequest;
import com.financeflow.dto.profile.ProfileResponse;
import com.financeflow.dto.profile.UpdateProfileRequest;
import com.financeflow.dto.user.RegisterRequest;
import com.financeflow.dto.user.UserResponse;
import com.financeflow.entity.User;
import com.financeflow.enums.Role;
import com.financeflow.exception.custom.BadRequestException;
import com.financeflow.exception.custom.ResourceAlreadyExistsException;
import com.financeflow.mapper.UserMapper;
import com.financeflow.repository.UserRepository;
import com.financeflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserResponse registerUser(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already exists");
        }

        User user = userMapper.toEntity(
                request,
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole(Role.USER);

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }

    @Override
    public ProfileResponse getProfile(User user) {
        return mapToProfileResponse(user);
    }

    @Override
    public ProfileResponse updateProfile( User user, UpdateProfileRequest request) {

        user.setName(request.getFullName());
        User updatedUser = userRepository.save(user);

        return mapToProfileResponse(updatedUser);
    }

    @Override
    public void changePassword(User user, ChangePasswordRequest request) {

        // 1. New password and confirm password should match
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new BadRequestException(
                    "New password and confirm password do not match"
            );
        }

        // 2. Verify current password
        if (!passwordEncoder.matches(
                request.getCurrentPassword(),
                user.getPassword())) {

            throw new BadRequestException("Current password is incorrect");
        }

        // 3. New password should be different
        if (passwordEncoder.matches(
                request.getNewPassword(),
                user.getPassword())) {

            throw new BadRequestException(
                    "New password must be different from current password"
            );
        }

        // 4. Encode and save
        user.setPassword(
                passwordEncoder.encode(request.getNewPassword())
        );

        userRepository.save(user);
    }

    private ProfileResponse mapToProfileResponse(User user) {
        return ProfileResponse.builder()
                .fullName(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .registeredAt(user.getCreatedAt())
                .build();
    }

}