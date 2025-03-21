package br.com.edujguerra.msvotacao.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public List<GroupedOpenApi> apis() {
        return Arrays.asList(
                GroupedOpenApi.builder()
                        .group("votacao_api")
                        .pathsToMatch("/votacao/**")
                        .build()
        );
    }
}
