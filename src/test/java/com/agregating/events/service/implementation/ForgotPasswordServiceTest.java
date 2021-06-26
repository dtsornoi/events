package com.agregating.events.service.implementation;

import com.agregating.events.service.CustomUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ForgotPasswordServiceTest {

  @Mock CustomUserService userService;

  @Test
  @DisplayName("Verify Email returns true, when given correct email")
  void verifyEmailReturnsTrue_WhenGivenExistingPassword() {
    Mockito.when(userService.existsByEmail("test@test.com")).thenReturn(true);

    ForgotPasswordService forgotPasswordService = new ForgotPasswordService(userService);
    Boolean result = forgotPasswordService.verifyEmail("test@test.com");

    assertTrue(result);
  }

  @Test
  @DisplayName("Should return false, when given wrong email")
  void shouldReturnFalse_WhenWrongEmailGiven() {
    Mockito.when(userService.existsByEmail("test@test.com")).thenReturn(false);

    ForgotPasswordService forgotPasswordService = new ForgotPasswordService(userService);

    Boolean result = forgotPasswordService.verifyEmail("test@test.com");

    assertFalse(result);
  }
}
