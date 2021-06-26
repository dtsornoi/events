package com.agregating.events.service.implementation;

import com.agregating.events.domain.ERole;
import com.agregating.events.domain.Role;
import com.agregating.events.repository.RoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplementationTest {

  @Mock RoleRepository roleRepository;

  @Test
  @DisplayName("Should return Role, when searched by ERole name")
  void shouldReturnRole_WhenSearchedByERoleName() {
    Role role = new Role();
    role.setName(ERole.ROLE_USER);
    role.setId(UUID.randomUUID());

    Mockito.when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(Optional.of(role));

    RoleServiceImplementation roleServiceImplementation = new RoleServiceImplementation(roleRepository);
    Role result = roleServiceImplementation.findByName(ERole.ROLE_USER).get();

    Assertions.assertEquals(role, result);
    Assertions.assertNotNull(result);
  }
}
