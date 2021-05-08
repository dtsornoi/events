package com.agregating.events.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO for message response
 *
 * @author Dmitri Tšornõi
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {
    private String message;

}
