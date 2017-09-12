package fr.woorib.beacon.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

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
    System.out.println("RESOLVED "+key+" = "+result);
    return result;
  }
}

