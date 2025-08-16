Para generar un proyecto con quarkus con maven es de la siguiente manera:

```bash
mvn io.quarkus:quarkus-maven-plugin:3.15.1:create -DprojectGroupId=org.acme -DprojectArtifactId=reservation-service -Dextensions=quarkus-rest-jackson,quarkus-rest-client-jackson,quarkus-smallrye-openapi -DnoCode
cd reservation-service
```