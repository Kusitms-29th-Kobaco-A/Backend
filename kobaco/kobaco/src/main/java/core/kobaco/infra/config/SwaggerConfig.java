package core.kobaco.infra.config;

import core.kobaco.global.jwt.JwtHeaderConsts;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@SecurityScheme(
    type= SecuritySchemeType.APIKEY,
    name = JwtHeaderConsts.AUTHORIZATION_HEADER,
    in = SecuritySchemeIn.HEADER, description = "Bearer {token}"
)
@OpenAPIDefinition(
    servers = {
        @Server(url="dev.simproject.kr", description = "Default Server url"),
        @Server(url="localhost:8080", description = "Local Server url")
    }
)
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi api(){
        return GroupedOpenApi.builder()
            .group("API")
            .addOpenApiCustomizer(openApi -> openApi.info(
                new io.swagger.v3.oas.models.info.Info()
                    .title("Kobaco API")
                    .version("1.0")
                    .description("Kobaco API")
            ).security(
                List.of(new SecurityRequirement().addList(JwtHeaderConsts.AUTHORIZATION_HEADER))
            )).pathsToMatch("/api/**")
            .build();
    }
}
