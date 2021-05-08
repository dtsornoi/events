package com.agregating.events.service;

import com.agregating.events.domain.User;
import com.agregating.events.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for CustomUserServiceImplementation
 *
 * @author Dmitri Tšornõi
 */

@ExtendWith(SpringExtension.class)
class CustomUserServiceImplementationTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserServiceImplementation customUserService;

    @Test
    @DisplayName("When findByUsername() called, should return User")
    void whenFindByUsernameCalled_returnUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("user");

       Mockito.when(customUserService.findByUsername("user")).thenReturn((Optional.of(user)));

       User result = customUserService.findByUsername("user")
               .orElseThrow(() -> new RuntimeException("No such user"));

       assertNotNull(result);
       assertNotNull(result.getUsername());
       assertEquals(user.getUsername(), result.getUsername());
       assertEquals(user, result);

    }

    @Test
    @DisplayName("When findByEmail() called, should return user")
    void whenFindByEmailCalled_returnUser() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@email.com");

        Mockito.when(customUserService.findByEmail("test@mail.com")).thenReturn(Optional.of(user));

        User result = customUserService.findByEmail("test@mail.com")
                .orElseThrow(() -> new RuntimeException(
                        String.format("User with email: %s not found", user.getEmail())));

        assertNotNull(result);
        assertNotNull(result.getEmail());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user, result);
    }

    @Test
    @DisplayName("When findById() called, should return user")
    void whenFindByIdCalled_ReturnUser() {
        User user = new User();
        user.setId(1L);

        Mockito.when(customUserService.findById(1L)).thenReturn(Optional.of(user));

        User result = customUserService.findById(1L)
                .orElseThrow(() -> new RuntimeException(
                        String.format("User with id: %s not found", user.getId())));

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(user, result);
        assertEquals(user.getId(), result.getId());
    }

    @Test
    @DisplayName("When saveUser() called, should return saved user")
    void whenSaveUserCalled_returnSavedUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("user");

        Mockito.when(customUserService.saveUser(user)).thenReturn(user);

        User result = customUserService.saveUser(user);

        assertEquals(user, result);
        assertEquals(user.getId(), result.getId());
        assertNotNull(result.getId());
        assertNotNull(result);
    }

    @Test
    @DisplayName("When existsByUsernameCalled(), should return True")
    void whenExistsByUsernameCalled_shouldReturnTrue() {
        User user = new User();
        user.setId(1L);
        user.setUsername("user");

        Mockito.when(customUserService.existsByUsername("user")).thenReturn(true);

        boolean result = customUserService.existsByUsername("user");

        assertTrue(result);
    }

    @Test
    @DisplayName("When existsByEmail() is called, should return true")
    void whenExistsByEmailCalled_shouldReturnTrue() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@mail.com");

        Mockito.when(customUserService.existsByEmail("test@mail.com")).thenReturn(true);

        boolean result = customUserService.existsByEmail("test@mail.com");

        assertTrue(result);
    }
}