package ru.rentplatform.auditservice.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

    @Bean
    public OpenAPI auditServiceOpenAPI(
            @Value("${app.swagger.gateway-url}") String gatewayUrl,
            @Value("${app.swagger.audit-service-url}") String auditServiceUrl
    ) {
        return new OpenAPI()
                .info(new Info()
                        .title("Audit Service API")
                        .description("API для логов и аудита Rent Platform")
                        .version("1.0.0"))
                .servers(List.of(
                        new Server().url(gatewayUrl).description("Gateway"),
                        new Server().url(auditServiceUrl).description("Audit Service (Direct)")
                ));
    }
}
