package org.apurimacdigital;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import java.util.logging.Logger;

@ApplicationScoped
public class AppLifeCycleBean {
  private static final Logger LOG = Logger.getLogger(AppLifeCycleBean.class.getName());
  void onStart(@Observes StartupEvent ev) {
    LOG.info("The application is starting...");
  }
  void onStop(@Observes ShutdownEvent ev) {
    LOG.info("The application is stopping...");
  }
}
