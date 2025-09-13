package com.quarkus.apu.digital.repositories;

import com.quarkus.apu.digital.entities.Customer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CustomerRepository {
  @Inject
  EntityManager em;

  @Transactional
  public void createCustomer(Customer customer) {
    em.persist(customer);
  }

  @Transactional
  public List<Customer> getAllCustomers() {
    return em.createQuery("SELECT c FROM Customer c", Customer.class)
        .getResultList();
  }

  @Transactional
  public void updateCustomer(Customer customer) {
    em.merge(customer);
  }

  @Transactional
  public void deleteCustomer(Customer customer) {
    em.remove(em.contains(customer) ? customer : em.merge(customer));
  }


  public Customer findCustomer(Long id) {
    return em.find(Customer.class, id);
  }
}
