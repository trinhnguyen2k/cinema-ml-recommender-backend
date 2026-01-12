package com.cinema.recommender.controller.auth;

import com.cinema.recommender.dto.auth.RegisterRequest;
import com.cinema.recommender.entity.User;
import com.cinema.recommender.entity.enums.UserRole;
import com.cinema.recommender.entity.enums.UserStatus;
import com.cinema.recommender.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setRole(UserRole.USER);
        user.setStatus(UserStatus.ACTIVE);

        return userRepository.save(user);
    }
}
