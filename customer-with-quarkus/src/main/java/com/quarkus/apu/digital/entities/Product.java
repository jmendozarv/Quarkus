package com.quarkus.apu.digital.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
    uniqueConstraints = {@UniqueConstraint(columnNames = {"customer", "product"})}
)
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "customer", referencedColumnName = "id")
  @JsonBackReference
  private Customer customer;
  @Column
  private Long product;
  @Transient
  private String code;
  @Transient
  private String name;
  @Transient
  private String description;
}
