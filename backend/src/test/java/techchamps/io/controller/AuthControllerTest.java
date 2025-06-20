package techchamps.io.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import techchamps.io.repository.UserRepository;
import techchamps.io.model.User;
import techchamps.io.model.Role;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest(classes = techchamps.io.App.class)
@AutoConfigureMockMvc
@Transactional
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    @Test
    void adminSigninShouldSucceed() throws Exception {
        String json = "{" +
                "\"username\": \"admin\"," +
                "\"password\": \"admin1234\"}";
        mockMvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
        // Assert admin has ADMIN role
        User admin = userRepository.findByUsername("admin").orElseThrow();
        org.junit.jupiter.api.Assertions.assertEquals(Role.ADMIN, admin.getRole());
    }

    @Test
    void signupShouldSucceed() throws Exception {
        String unique = java.util.UUID.randomUUID().toString().substring(0, 8);
        String username = "user" + unique;
        String email = unique + "@test.com";
        String json = String.format("{\"email\":\"%s\",\"username\":\"%s\",\"password\":\"pass1234\"}", email, username);
        mockMvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.username").value(username))
                .andExpect(jsonPath("$.email").value(email))
                .andExpect(jsonPath("$.role").value("USER"));
        // Assert new user has USER role
        User user = userRepository.findByUsername(username).orElseThrow();
        org.junit.jupiter.api.Assertions.assertEquals(Role.USER, user.getRole());
    }

    @Test
    void signupWithExistingUsernameShouldFail() throws Exception {
        String json = "{" +
                "\"email\": \"uniqueemail@test.com\"," +
                "\"username\": \"admin\"," +
                "\"password\": \"pass1234\"}";
        mockMvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Username already exists"));
    }

    @Test
    void signupWithExistingEmailShouldFail() throws Exception {
        String json = "{" +
                "\"email\": \"admin@test.nl\"," +
                "\"username\": \"uniqueuser\"," +
                "\"password\": \"pass1234\"}";
        mockMvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Email already exists"));
    }

    @Test
    void signinWithWrongPasswordShouldFail() throws Exception {
        String json = "{" +
                "\"username\": \"admin\"," +
                "\"password\": \"wrongpass\"}";
        mockMvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid credentials"));
    }

    @Test
    void signinWithNonExistentUsernameShouldFail() throws Exception {
        String json = "{" +
                "\"username\": \"nouser\"," +
                "\"password\": \"pass1234\"}";
        mockMvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid credentials"));
    }
} 