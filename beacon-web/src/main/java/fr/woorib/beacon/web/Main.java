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
      System.setProperty("JDBC_DRIVER", System.getProperty("JDBC_DRIVER", "org.postgresql.Driver"));
      System.setProperty("DATABASE_URL", System.getProperty("DATABASE_URL", "jdbc:hsqldb:mem:testdb"));
      SpringApplication.run(Main.class, args);
    }
}

