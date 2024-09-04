package org.frank.cloud.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi PayAPI() {
        return GroupedOpenApi.builder()
                .group("Payment-module")
                .pathsToMatch("/pay/**")
                .build();
    }

    @Bean
    public GroupedOpenApi OtherAPI() {
        return GroupedOpenApi.builder()
                .group("Other-module")
                .pathsToMatch("/other/**", "/others")
                .build();
    }

    @Bean
    public OpenAPI docsOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("spring-cloud-project")
                                .description("REST APIs")
                                .version("v1.0")
                )
                .externalDocs(
                        new ExternalDocumentation()
                                .description("spring-cloud-docs-test")
                                .url("https://google.com")
                );
    }
}
