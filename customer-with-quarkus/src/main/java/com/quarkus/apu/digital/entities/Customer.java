package com.quarkus.apu.digital.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
  @OneToMany(mappedBy = "customer", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
  @JsonManagedReference
  private List<Product> products;

}
