package org.acme.reservation.rest;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.acme.reservation.inventory.Car;
import org.acme.reservation.inventory.InventoryClient;
import org.acme.reservation.reservation.Reservation;
import org.acme.reservation.reservation.ReservationsRepository;
import org.jboss.resteasy.reactive.RestQuery;

//Establece la ruta a /reservation
@Path("reservation")
/*
 * @Produces(MediaType.APPLICATION_JSON) garantiza que todos los métodos que gestionan
 * llamadas REST devuelvan una respuesta JSON. Esto hace que Quarkus serialice el
 * resultado del método en JSON al devolver la respuesta HTTP.
 * También establece el encabezado Content-Type en application/json.
 * */
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class ReservationResource {
  //Quarkus crea la instancia de ReservationResource para nosotros llamando a este constructor,
  // proporcionándonos las implementaciones de las interfaces
  // ReservationsRepository e InventoryClient que necesitamos (ya que son beans CDI @Singleton).
  private final ReservationsRepository reservationsRepository;
  private final InventoryClient inventoryClient;


  @GET
  @Path("availability")
  /*
  * Los dos parámetros del método están anotados con la anotación @RestQuery,
  * lo que significa que representan los valores de los parámetros de consulta HTTP startDate y endDate,
  * respectivamente (coincidentes con los nombres de las variables). Los valores deben tener el formato AAAA-MM-DD.
   * */
  public Collection<Car> availability(@RestQuery LocalDate startDate,
                                      @RestQuery LocalDate endDate) {
    // obtain all cars from inventory
    List<Car> availableCars = inventoryClient.allCars();
    // create a map from id to car
    Map<Long, Car> carsById = new HashMap<>();
    for (Car car : availableCars) {
      carsById.put(car.id, car);
    }

    // get all current reservations
    List<Reservation> reservations = reservationsRepository.findAll();
    // for each reservation, remove the car from the map
    for (Reservation reservation : reservations) {
      if (reservation.isReserved(startDate, endDate)) {
        carsById.remove(reservation.getCardId());
      }
    }
    return carsById.values();
  }

  @Consumes(MediaType.APPLICATION_JSON)
  @POST
  public Reservation make(Reservation reservation) {
    // save the reservation
    return reservationsRepository.save(reservation);
  }
}
