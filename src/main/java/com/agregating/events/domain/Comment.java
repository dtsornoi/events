package com.agregating.events.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

/**
 * POJO for comments
 *
 * @author Dmitri Tšornõi
 */
@Document(collection = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

  @Id private UUID id;

  @NotBlank
  @Size(max = 20)
  private String title;

  @NotBlank
  @Size(min = 10, max = 150)
  private String comment;

  private Date postedOn;

  private User user;

  private Event event;
}
