package com.inditex.core.priceservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up OpenAPI documentation for the Price Service API.
 * <p>
 * This class defines the OpenAPI configuration for exposing the REST endpoints via Swagger UI.
 * It provides the OpenAPI metadata and groups the API under a "public" group for easy access and documentation.
 * </p>
 *
 * @author Imanol Villalba Rodríguez
 * @since 2025-05-10
 */
@Configuration
public class OpenApiConfig {

    /**
     * Creates a {@link GroupedOpenApi} bean for the public API group.
     * <p>
     * This bean configures the API documentation group that includes all endpoints under {@code /api/**}.
     * It is used to categorize the public endpoints in the Swagger UI.
     * </p>
     *
     * @return a {@link GroupedOpenApi} object that groups the public API under the "public" group
     */
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/api/**")
                .build();
    }

    /**
     * Creates a custom {@link OpenAPI} bean that configures the API metadata.
     * <p>
     * This method sets the title, version, and description of the API. The metadata will be displayed in
     * the Swagger UI and will provide users with information about the API's functionality and version.
     * </p>
     *
     * @return an {@link OpenAPI} object containing the metadata for the Price Service API
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("price-service")
                        .version("v1")
                        .description("REST endpoint to query the product price database, providing information on applicable prices based on brand, product, and date.")
                );
    }
}