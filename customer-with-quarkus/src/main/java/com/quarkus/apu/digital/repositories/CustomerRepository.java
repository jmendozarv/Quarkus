package com.quarkus.apu.digital.repositories;

import com.quarkus.apu.digital.entities.Customer;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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
