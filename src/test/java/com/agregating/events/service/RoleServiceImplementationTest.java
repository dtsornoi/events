package com.agregating.events.service;

import com.agregating.events.domain.ERole;
import com.agregating.events.domain.Role;
import com.agregating.events.repository.RoleRepository;
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
 * Tests for RoleServiceImplementation
 *
 * @author Dmitri Tšornõi
 */

@ExtendWith(SpringExtension.class)
class RoleServiceImplementationTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImplementation roleService;

    @Test
    @DisplayName("When findByName() is called, should return ERole")
    void whenFindByNameCalled_returnERole(){
        Role role = new Role();
        role.setId(1L);
        role.setName(ERole.ROLE_USER);


        Mockito.when(roleService.findByName(ERole.ROLE_USER)).thenReturn(Optional.of(role));

        Role result = roleService.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("No role"));

        assertEquals(role, result);
    }
}