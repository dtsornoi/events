package com.agregating.events.payload.response;

import com.agregating.events.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUser {
    private UUID id;
    private String username;
    private String email;
    private Collection<Role> roles;
}
