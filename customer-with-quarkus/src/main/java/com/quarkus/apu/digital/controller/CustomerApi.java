package com.quarkus.apu.digital.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quarkus.apu.digital.entities.Customer;
import com.quarkus.apu.digital.entities.Product;
import com.quarkus.apu.digital.repositories.CustomerRepository;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonArray;
import io.vertx.mutiny.core.Vertx;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.mutiny.ext.web.client.WebClient;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
public class CustomerApi {
  @Inject
  CustomerRepository cr;
  @Inject
  Vertx vertx;

  private WebClient webClient;

  @PostConstruct
  void initialize() {
    this.webClient = WebClient.create(vertx,
        new WebClientOptions()
            .setDefaultHost("localhost")
            .setDefaultPort(8081).setSsl(false).setTrustAll(true));
  }

  @GET
  @Blocking
  public List<Customer> getCustomers() {
    return cr.getAllCustomers();
  }

  //crear un endpoint por busqueda por id
  @GET
  @Path("/{Id}")
  @Blocking
  public Customer getCustomerById(@PathParam("Id") Long Id) {
    return cr.findCustomer(Id);
  }

  @GET
  @Path("/{Id}/products")
  @Blocking
  public Uni<Customer> getByIdProduct(@PathParam("Id") Long Id) {
    return Uni.combine().all().unis(getCustomerReactive(Id), getProductReactive(Id))
        .combinedWith((v1, v2) -> {
          v1.getProducts().forEach(p -> {
            v2.forEach(prod -> {
              if (p.getProduct().equals(prod.getId())) {
                p.setCode(prod.getCode());
                p.setName(prod.getName());
                p.setDescription(prod.getDescription());
              }
            });
          });
          return v1;
        });
  }

  @POST
  @Blocking
  public Response addCustomer(Customer c) {
    cr.createCustomer(c);
    return Response.ok().build();
  }

  @PUT
  @Blocking
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
  @Blocking
  @Path("/{Id}")
  public Response deleteCustomer(@PathParam("Id") Long Id) {
    cr.deleteCustomer(cr.findCustomer(Id));
    return Response.ok().build();
  }

  private Uni<Customer> getCustomerReactive(Long id) {
    Customer customer = cr.findCustomer(id);
    Uni<Customer> item = Uni.createFrom().item(customer);
    return item;
  }

  private Uni<List<Product>> getProductReactive(Long id) {
    return webClient.get(8081, "localhost", "/product").send()
        .onFailure().invoke(response -> log.error("Error recuperando productos  ", response))
        .onItem().transform(res -> {
          List<Product> products = new ArrayList<Product>();
          JsonArray obj = res.bodyAsJsonArray();
          obj.forEach(item -> {
            log.info("See Objects : " + item.toString());
            ObjectMapper mapper = new ObjectMapper();
            Product p = mapper.convertValue(item, Product.class);
            products.add(p);
          });
          return products;
        });
  }
}
