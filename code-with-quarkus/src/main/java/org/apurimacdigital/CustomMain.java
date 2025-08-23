package org.apurimacdigital;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain // Indica que esta es la clase principal de Quarkus
public class CustomMain {
  public static void main(String[] args) {
    Quarkus.run(CustomApp.class, args);
  }
  // Con esta clase interna, puedes definir la lógica personalizada de la aplicación
  // al implementar la interfaz QuarkusApplication
  // Al ejecutar la aplicación, se llamará al método run de esta clase
  // Puedes manejar argumentos de línea de comandos y controlar el ciclo de vida de la aplicación
  // Aquí puedes agregar cualquier lógica que necesites al iniciar la aplicación
  // Por ejemplo, inicializar recursos, configurar servicios, etc.
  // La aplicación permanecerá en ejecución hasta que se detenga explícitamente
  // Puedes devolver un código de salida personalizado si es necesario
  // En este ejemplo, simplemente imprime un mensaje y espera a que la aplicación se detenga
  // Puedes personalizar este comportamiento según tus necesidades
  // Recuerda que esta es una implementación básica y puedes expandirla según los requisitos de tu aplicación
  // Asegúrate de manejar adecuadamente las excepciones y los recursos según sea necesario
  // Esta clase es opcional y puedes omitirla si no necesitas lógica personalizada al iniciar
  // La clase CustomApp implementa QuarkusApplication para definir el comportamiento de la aplicación
  // El método run es el punto de entrada cuando se inicia la aplicación
  public static class CustomApp implements QuarkusApplication {
    @Override
    public int run(String... args) throws Exception {
      // Lógica personalizada al iniciar la aplicación
      System.out.println("Aplicación Quarkus está corriendo con CustomApp...");
      Quarkus.waitForExit(); // Mantiene la aplicación en ejecución hasta que se detenga
      return 0; // Código de salida
    }
  }
}
