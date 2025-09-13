package com.quarkus.apu.digital.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String code;
  private String accountNumber;
  private String names;
  private String surnames;
  private String phone;
  private String address;
  //un cliente puede tener varios productos
  private List<Product> products;

}
