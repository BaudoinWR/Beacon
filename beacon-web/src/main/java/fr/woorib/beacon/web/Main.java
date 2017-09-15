package fr.woorib.beacon.web;

import java.math.BigDecimal;
import javax.inject.Inject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import fr.woorib.beacon.data.BeaconEntry;
import fr.woorib.beacon.services.BeaconService;

/**
 * Created by Veryeld on 02/04/2017.
 */

@SpringBootApplication
@EnableAutoConfiguration(exclude=HibernateJpaAutoConfiguration.class) // added as per https://stackoverflow.com/a/38637273
public class Main {

    public static void main(String[] args) {
      System.setProperty("JDBC_DRIVER", getEnvSystemProperty("JDBC_DRIVER", "org.postgresql.Driver"));
      System.setProperty("JDBC_DATABASE_URL", getEnvSystemProperty("JDBC_DATABASE_URL", "jdbc:hsqldb:mem:testdb"));
      SpringApplication.run(Main.class, args);
    }

  /**
   * Looks for a property in Environment then System.getProperti  es
   * @param key
   * @param def
   * @return the value found or def
   */
  private static String getEnvSystemProperty(String key, String def) {
    String getenv = System.getenv(key);
    String result = getenv != null ? getenv : System.getProperty(key, def);
    return result;
  }

  @Component
  public static class ApplicationReadyEventApplicationListener implements ApplicationListener<ApplicationReadyEvent> {
    @Inject
    BeaconService beaconService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
      BeaconEntry beacon = beaconService.getBeacon(1);
      if (beacon == null) {
        beaconService.setBeacon(1, new BigDecimal(42.333), new BigDecimal(12.444));
      }
    }
  }
}

