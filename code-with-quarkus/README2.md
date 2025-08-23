# apuntes
Caracteristicas de Quarkus
[]: # Quarkus es un framework Java diseñado para crear aplicaciones nativas en la nube y microservicios. Algunas de sus características más destacadas son:
1. Desarrollo optimizado: Quarkus está diseñado para ser rápido y eficiente en el desarrollo de aplicaciones Java, permitiendo a los desarrolladores centrarse en la lógica de negocio en lugar de
   preocuparse por la configuración y el rendimiento.   
2. Hot Reload: Quarkus permite recargar automáticamente los cambios en el código sin necesidad de reiniciar la aplicación, lo que acelera el proceso de desarrollo.
2. Extensiones: Quarkus utiliza un sistema de extensiones que permite agregar funcionalidades específicas a la aplicación, como soporte para REST, bases de datos, seguridad, etc.
3. Compilación nativa: Quarkus permite compilar aplicaciones Java en ejecutables nativos, lo que mejora el rendimiento y reduce el tiempo de inicio.
4. Integración con GraalVM: Quarkus se integra con GraalVM para permitir la creación de ejecutables nativos optimizados para el entorno de producción.
5. Configuración centralizada: Quarkus utiliza un archivo de configuración centralizado (`application.properties`) para gestionar la configuración de la aplicación, lo que facilita la
6. Dev UI: Quarkus proporciona una interfaz de usuario de desarrollo (Dev UI)
7. Soporte para MicroProfile: Quarkus es compatible con MicroProfile, lo que permite desarrollar aplicaciones Java para microservicios de manera más sencilla.

Anotaciones comunes en Quarkus
[]: # Quarkus utiliza varias anotaciones para definir y configurar componentes en una aplicación. Algunas de las anotaciones más comunes son:
1. QuarkusMain: Indica la clase principal de la aplicación Quarkus.
2. ApplicationScoped: Define un bean con un ciclo de vida de aplicación, es decir, una instancia única durante toda la vida de la aplicación.
3. RequestScoped: Define un bean con un ciclo de vida por solicitud, es decir, una nueva instancia para cada solicitud HTTP.
4. Singleton: Define un bean como un singleton, es decir, una única instancia compartida en toda la aplicación.
5. Inject: Inyecta una dependencia en un bean.
6. ConfigProperty: Inyecta una propiedad de configuración en un bean.
7. Path: Define la ruta base para un recurso REST.
8. GET, POST, PUT, DELETE: Anotaciones para definir métodos HTTP en recursos REST.
9. Produces: Define el tipo de contenido que un método REST puede producir.
10. Consumes: Define el tipo de contenido que un método REST puede consumir.
11. RestClient: Indica que una interfaz es un cliente REST.
12. RegisterRestClient: Registra una interfaz como un cliente REST para su uso con MicroProfile Rest Client.
13. OpenAPIDefinition: Define la configuración de OpenAPI para la aplicación.
14. Tag: Define etiquetas para agrupar operaciones en la documentación de OpenAPI.
15. Operation: Proporciona información adicional sobre una operación REST para la documentación de OpenAPI.
16. Parameter: Define parámetros para operaciones REST en la documentación de OpenAPI.
17. Schema: Define esquemas para modelos de datos en la documentación de OpenAPI.
18. SecurityScheme: Define esquemas de seguridad para la documentación de OpenAPI.
19. RolesAllowed: Define roles permitidos para acceder a un recurso o método.
20. PermitAll: Permite el acceso a todos los usuarios a un recurso o método.
21. DenyAll: Niega el acceso a todos los usuarios a un recurso o método.
22. Transactional: Define que un método debe ejecutarse dentro de una transacción.
23. Observes: Indica que un método observa eventos específicos.
24. Startup: Indica que un bean debe inicializarse al inicio de la aplicación.
25. Shutdown: Indica que un bean debe limpiarse al apagar la aplicación.
26. Health: Define un endpoint de salud para la aplicación.
    27. Metrics: Define métricas para la aplicación.
    []: # Estas anotaciones son solo algunas de las muchas que Quarkus ofrece para facilitar el desarrollo de aplicaciones Java. La documentación oficial de Quarkus proporciona una lista completa y detallada
    []: # de todas las anotaciones disponibles y su uso. 