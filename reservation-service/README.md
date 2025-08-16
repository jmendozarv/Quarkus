# reservation-service

1. Para las fechas de inicio y fin proporcionadas por el usuario, se devuelve una lista de coches disponibles para alquilar. 
2. Reserva un coche específico para un conjunto de fechas.

### quarkus-rest-jackson
* Elegimos la extensión quarkus-rest-jackson porque queremos usar la biblioteca Jackson para manejar la serialización y deserialización JSON de solicitudes y respuestas de los servicios REST que expone el servicio de reserva.

### quarkus-rest
*  proporciona la funcionalidad principal de REST, es una dependencia de quarkus-rest-jackson (que solo le agrega el soporte de Jackson), por lo que no tenemos que agregar quarkus-rest explícitamente.


### quarkus-rest-client-jackson
* _Usamos la extensión quarkus-rest-client-jackson porque queremos que Jackson gestione la serialización y deserialización de JSON de las solicitudes REST posteriores que crea el servicio de reservas. Nuevamente, la funcionalidad principal del cliente REST se incorpora transitivamente como la extensión quarkus-rest-client._

### quarkus-smallrye-openapi
* _La extensión quarkus-smallrye-openapi permite generar un documento OpenAPI que documenta nuestra API REST expuesta. También crea una interfaz de usuario sencilla que podemos usar para probar los endpoints REST que desarrollamos._

Antes de crear cualquier código, asegúrese de iniciar el modo Dev de Quarkus, lo que puede hacerse, como recordará, ejecutando la CLI de Quarkus:

```bash
mvn quarkus:dev
```


### @Singleton
* _La anotación @Singleton se usa para indicar que la clase es un singleton, lo que significa que solo habrá una instancia de esta clase en toda la aplicación. Esto es útil para servicios que no necesitan ser instanciados varias veces y pueden compartir el mismo estado._
* _Esta anotación forma parte de la especificación de Inyección de Contexto y Dependencia (CDI). Nos permite usar esta clase como implementación de InventoryClient donde la aplicación la requiera._

## OPEN API
- You can check the response from http://localhost :8081/q/openapi if you want to see the whole document.