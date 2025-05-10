package com.inditex.core.priceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Price Service.
 * <p>
 * This class contains the main method, which serves as the entry point for the Spring Boot application.
 * When the application is started, it will launch the Spring Boot application context, initializing all necessary
 * components, services, and repositories as defined in the application configuration.
 * </p>
 *
 * <p>
 * The application is annotated with SpringBootApplication, which is a combination of several annotations:
 * Configuration, EnableAutoConfiguration, and ComponentScan. These annotations enable the
 * automatic configuration of the application context, component scanning, and the ability to run the application.
 * </p>
 */
@SpringBootApplication
public class PriceServiceApplication {

    /**
     * The main method that starts the Spring Boot application.
     * <p>
     * This method launches the application by invoking {@link SpringApplication#run(Class, String...)}.
     * It initializes the application context, scans for components, and starts the embedded web server.
     * </p>
     *
     * @param args command-line arguments passed to the application (if any).
     */
    public static void main(String[] args) {
        SpringApplication.run(PriceServiceApplication.class, args);
    }
}
