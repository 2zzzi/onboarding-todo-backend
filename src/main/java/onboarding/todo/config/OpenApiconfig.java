package onboarding.todo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiconfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info().title("Todo 관리자페이지")
                .description("by Jihun");

        SecurityScheme jwt = new SecurityScheme()
                .name("JWT token")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        return new OpenAPI()
                .info(info)
                .components(new Components()
                .addSecuritySchemes("JWT Token", jwt));
    }
}
