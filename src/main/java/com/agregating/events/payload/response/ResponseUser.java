package com.agregating.events.payload.response;

import com.agregating.events.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUser {
    private Long id;
    private String username;
    private String email;
    private Collection<Role> roles;
}
