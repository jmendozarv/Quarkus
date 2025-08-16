package org.acme.reservation.reservation;

import jakarta.inject.Singleton;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Singleton
public class InMemoryReservationsRepository implements ReservationsRepository {

  private final AtomicLong ids = new AtomicLong(0);
  private final List<Reservation> store =
      new CopyOnWriteArrayList<>();
//#1 Esta lista (segura para subprocesos) es esencialmente nuestro almacén de datos.

  @Override
  public List<Reservation> findAll() {
    return Collections.unmodifiableList(store);
  }

  @Override
  public Reservation save(Reservation reservation) {
    reservation.id =
        ids.incrementAndGet(); //Asigna un ID único a la reserva que estamos a punto de guardar

    store.add(reservation);
    return reservation;
  }
}
