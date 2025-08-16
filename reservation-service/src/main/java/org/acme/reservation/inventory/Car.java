package org.acme.reservation.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Car {

  public Long id;
  public String licensePlateNumber;
  public String manufacturer;
  public String model;


}
