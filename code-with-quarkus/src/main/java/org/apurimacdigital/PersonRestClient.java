package org.apurimacdigital;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import java.util.List;
import java.util.Set;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "https://stage.code.quarkus.io/api")
public interface PersonRestClient {

  @GET
  @Path("/extensions")
  Set<Extension> getExtensionById(@QueryParam("id") String id);

  class Extension {
    public String id;
    public String name;
    public String shortName;
    public List<String> keywords;
  }
}

