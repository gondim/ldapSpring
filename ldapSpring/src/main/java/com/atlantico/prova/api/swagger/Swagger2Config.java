package com.atlantico.prova.api.swagger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.any())
          .paths(PathSelectors.regex("/Users.*"))
          .build()
          .useDefaultResponseMessages(false)
          .apiInfo(apiInfo())
          .globalResponseMessage(RequestMethod.GET, responseMessageForGET());
    }
    
    private List<ResponseMessage> responseMessageForGET()
    {
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                .code(500)
                .message("Server error")
                .build());
            add(new ResponseMessageBuilder()
                .code(400)
                .message("Bad request")
                .build());
            add(new ResponseMessageBuilder()
                .code(404)
                .message("Not Found")
                .build());
        }};
    }
    
    private ApiInfo apiInfo() {
    	return new ApiInfoBuilder()
                .title("Ldap API")
                .description("API to create, list, find and delete entry from ldap")
                .version("1.0.0")
                .build();
    }
}
