package core.kobaco.global.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;

@Configuration    // 스프링 실행시 설정파일 읽어드리기 위한 어노테이션
@EnableSwagger2    // Swagger2를 사용하겠다는 어노테이션
@SuppressWarnings("unchecked")	// warning밑줄 제거를 위한 태그

public class SwaggerConfig extends WebMvcConfigurationSupport {
    //리소스 핸들러 설
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    @Bean
    public Docket ImageUploadApi() {
        return getDocket("이미지업로드", PathSelectors.regex("/ImageUpload.*"));
    }

    @Bean
    public Docket recommendApi() {
        return getDocket("추천", PathSelectors.regex("/recommend.*"));
    }

    @Bean
    public Docket relatedRecommendApi() {
        return getDocket("관련영상추천", PathSelectors.regex("/relatedRecommend.*"));
    }

    @Bean
    public Docket allApi() {
        return getDocket("전체", PathSelectors.regex("/*.*"));
    }

    public Docket getDocket(String groupName, Predicate<String> predicate) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage("core.kobaco"))
                .paths(predicate)
                .apis(RequestHandlerSelectors.any())
                .build();
    }

    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .displayRequestDuration(true)
                .validatorUrl("")
                .build();
    }
}
