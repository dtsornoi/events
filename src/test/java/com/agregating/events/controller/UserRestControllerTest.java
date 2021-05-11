package com.agregating.events.controller;

import com.agregating.events.domain.User;
import com.agregating.events.payload.response.ResponseUser;
import com.agregating.events.repository.UserRepository;
import com.agregating.events.service.CustomUserService;
import com.agregating.events.service.CustomUserServiceImplementation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Tests for UserRestController
 *
 * @author Dmitri Tšornõi
 */

@SpringBootTest
@AutoConfigureMockMvc
class UserRestControllerTest {

    @MockBean
    private CustomUserServiceImplementation service;

    @Autowired
    private MockMvc mockMvc;

    @BeforeTestClass
    public void authZ(){

    }

    @Test
    @DisplayName("When findById() is called, return User with requested id and status 200")
    void whenFindByIdCalled_returnUserWithRequestedId() throws Exception {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("mockUser");

        Mockito.doReturn(Optional.of(mockUser))
                .when(service).findById(1L);

        mockMvc.perform(get("/1")).andExpect(status().isOk());
    }
}