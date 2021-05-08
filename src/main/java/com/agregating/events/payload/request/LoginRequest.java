package com.agregating.events.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * POJO for login request
 *
 * @author Dmitri Tšornõi
 */

@Data
@NoArgsConstructor
public class LoginRequest {

        @NotBlank
        private String username;

        @NotBlank
        private String password;
}
