package org.quarkus.apudigital;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private String email;
  private boolean subscribed;
  private String phone;

}
