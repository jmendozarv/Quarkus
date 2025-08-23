package org.apurimacdigital;

import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.Set;
import java.util.stream.Collectors;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/extensions")
public class ExtensionInfoController {

  @Inject
  ExtensionInfo extensionInfo;

  @ConfigProperty(name = "greeting.message", defaultValue = "Hello, Quarkus!")
  private String greeting;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Set<JsonObject> extensionInfo() {
    Set<JsonObject> extensions = extensionInfo.doSomething().stream().map(extension -> {
        return Json.createObjectBuilder()
            .add("id", extension.id)
            .add("name", extension.name)
            .add("shortName", extension.shortName)
            .add("keywords", Json.createArrayBuilder(extension.keywords))
            .build();
    }).collect(Collectors.toSet());
    return extensions;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("custom/{name}")
  public String getExtensionInfo(@PathParam("name") String name) {
    return greeting + name + ". " +
           "You can implement the logic to fetch and return the extension details here.";
  }
}
