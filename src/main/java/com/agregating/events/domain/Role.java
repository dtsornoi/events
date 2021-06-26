package com.agregating.events.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/**
 * POJO for storing role enums
 *
 * @author Dmitri Tšornõi
 */
@Document(collection = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

  @Id private UUID id;

  private ERole name;
}
