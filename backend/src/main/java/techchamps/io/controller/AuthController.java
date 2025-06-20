package techchamps.io.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techchamps.io.service.UserService;
import techchamps.io.config.JwtUtil;
import techchamps.io.dto.SignupRequest;
import techchamps.io.dto.SigninRequest;
import techchamps.io.dto.UserResponse;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        if (userService.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        if (userService.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        var user = userService.registerUser(request.getEmail(), request.getUsername(), request.getPassword());
        return ResponseEntity.ok(new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getRole().name()));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest request) {
        var userOpt = userService.findByUsername(request.getUsername());
        if (userOpt.isPresent() && userService.checkPassword(userOpt.get(), request.getPassword())) {
            String token = jwtUtil.generateToken(request.getUsername());
            return ResponseEntity.ok(Collections.singletonMap("token", token));
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
} 