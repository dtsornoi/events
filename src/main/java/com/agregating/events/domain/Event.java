package com.agregating.events.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * POJO for Event
 *
 * @author Dmitri Tšornõi
 */
@Document(collection = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

  @Id private UUID id;

  @Size(max = 20, min = 6)
  @NotBlank
  private String title;

  @NotBlank
  @Size(min = 10, max = 150)
  private String description;

  private Date startingFrom;

  private Date endingOn;

  private User user;

  private List<User> subscribedUsers;
}
