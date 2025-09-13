package com.quarkus.apu.digital.controller;

import com.quarkus.apu.digital.entities.Customer;
import com.quarkus.apu.digital.repositories.CustomerRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerApi {
  @Inject
  CustomerRepository cr;

  @GET
  public List<Customer> getCustomers() {
    return cr.getAllCustomers();
  }

  //crear un endpoint por busqueda por id
  @GET
  @Path("/{Id}")
  public Customer getCustomerById(@PathParam("Id") Long Id) {
    return cr.findCustomer(Id);
  }

  @POST
  public Response addCustomer(Customer c) {
    cr.createCustomer(c);
    return Response.ok().build();
  }

  @PUT
  public Response updateCustomer(Customer c) {
    Customer customer = cr.findCustomer(c.getId());
    customer.setCode(c.getCode());
    customer.setAccountNumber(c.getAccountNumber());
    customer.setNames(c.getNames());
    customer.setSurnames(c.getSurnames());
    customer.setPhone(c.getPhone());
    customer.setAddress(c.getAddress());
    customer.setProducts(c.getProducts());
    cr.updateCustomer(customer);
    return Response.ok().build();
  }

  //eliminar un cliente por id
  @DELETE
  @Path("/{Id}")
  public Response deleteCustomer(@PathParam("Id") Long Id) {
    cr.deleteCustomer(cr.findCustomer(Id));
    return Response.ok().build();
  }
}
