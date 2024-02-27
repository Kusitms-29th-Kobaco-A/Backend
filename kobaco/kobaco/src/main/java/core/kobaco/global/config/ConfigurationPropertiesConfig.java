package core.kobaco.global.config;

import core.kobaco.global.jwt.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class ConfigurationPropertiesConfig {
}
