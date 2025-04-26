package com.br.luizalab.comunicacao.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Plataforma de Comunicação")
                        .version("1.0")
                        .description("API para gerenciamento de agendamentos de comunicação.")
                        .contact(new Contact()
                                .name("Dev - Pedro Nunes")
                                .email("pehennu@gmail.com")
                                .url("https://github.com/pehennu/api-comunicacao-luizalab"))
                        .license(new License()
                                .name("Meu linkedin")
                                .url("https://www.linkedin.com/in/pedro-nunes-457209199/"))
                );
    }
}