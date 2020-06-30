package com.haulmatic.springbootassingment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class SpringBootAssingmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAssingmentApplication.class, args);
    }
    @Bean
    public Docket swaggerConfiguration(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.regex("/roles.*"))
                .build()
                .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo(){
        return new ApiInfo(
                "Assigment API",
                "Haulmatic Spring Boot Assigment API Documantation",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact("Chathuranga Priyadarshana", "no privet web", "bmcpthilakawansha@gmail.com" ),
                "API License",
                "http://javabrains.io",
                Collections.emptyList()

        );
    }
}
