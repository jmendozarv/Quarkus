package org.quarkus.apudigital;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.IllegalFormatCodePointException;
import java.util.List;

@Path("/api/contact")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContactResource {

  @Inject
  EntityManager entityManager;

  @GET
  public List<Contact> getAllContacts() {
    List<Contact> fromContact =
        entityManager.createQuery("from Contact", Contact.class).getResultList();
    return fromContact;
  }

  @GET
  @Path("/{id}")
  public Response getContactById(@PathParam("id") Long id) {
    Contact contact = entityManager.find(Contact.class, id);
    if (contact == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(contact).build();
  }

  @PUT
  @Path("/email/{email}")
  @Transactional
  public Response updateContactEmail(@PathParam("email") String email, Contact updatedContact) {
    Contact contact =
        entityManager.createQuery("SELECT c FROM Contact c WHERE c.email = :email", Contact.class)
            .setParameter("email", email)
            .getSingleResult();
    if (contact == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    contact.setEmail(updatedContact.getEmail());
    contact.setName(updatedContact.getName());
    entityManager.merge(contact);
    return Response.ok(contact).build();
  }

  @POST
  @Transactional
  public Response createContact(Contact contact) {
    entityManager.persist(contact);
    return Response.ok(contact).build();
  }

  @GET
  @Path("/email/{email}")
  public Response getContactEmail(@PathParam("email") String email) {
    Contact contact =
        entityManager.createQuery("SELECT c FROM Contact c WHERE c.email = :email", Contact.class)
            .setParameter("email", email).getSingleResult();
    if (contact == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(contact).build();
  }


  @PUT
  @Path("/{id}")
  @Transactional
  public Response updateContact(@PathParam("id") Long id, Contact contact) {
    Contact contactUpdate = entityManager.find(Contact.class, id);
    if (contactUpdate == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    contactUpdate.setName(contact.getName());
    contactUpdate.setEmail(contact.getEmail());
    entityManager.merge(contactUpdate);
    return Response.ok(contactUpdate).build();
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public Response deleteContact(@PathParam("id") Long id) {
    Contact contact = entityManager.find(Contact.class, id);
    if (contact == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    entityManager.remove(contact);
    return Response.ok(contact).build();
  }


}
