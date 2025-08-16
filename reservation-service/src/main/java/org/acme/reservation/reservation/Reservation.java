package org.acme.reservation.reservation;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Reservation {
  public Long id;
  public Long cardId;
  public LocalDate startDay;
  public LocalDate endDay;

  /**
   * Verificar si la duración indicada coincide con la de esta reserva
   * @return true si las fechas coinciden con la reserva, false.
   * En caso contrario.
   */
  public boolean isReserved(LocalDate startDay, LocalDate endDay) {
    return (!(this.endDay.isBefore(startDay) ||
        this.startDay.isAfter(endDay)));
  }




}
