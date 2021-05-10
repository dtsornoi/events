package com.agregating.events.controller;

import com.agregating.events.domain.User;
import com.agregating.events.service.CustomUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;


/**
 * Tests for UserRestController
 *
 * @author Dmitri Tšornõi
 */

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserRestControllerTest {

    @Mock
    private CustomUserService service;

    @InjectMocks
    private UserRestController controller;

    @Test
    @DisplayName("When findById() is called, return User with requested id and status 200")
    void whenFindByIdCalled_returnUserWithRequestedId() throws Exception {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("mockUser");

        Mockito.when(service.findById(Mockito.anyLong())).thenReturn(Optional.of(mockUser));
        ResponseEntity<User> responseEntity = controller.findById(1L);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo(mockUser);
    }
}