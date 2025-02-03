package org.spdgroup.bigbitebackend.utils.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info()
                        .title("Big Bite API")
                        .description("API desarrollada con Spring Boot conectada a una base de datos MySQL, " +
                                "que al ser consumida por un cliente, en este caso un e-commerce de hamburguesas, " +
                                "Big Bite, permite la gestion y persistencia de productos, pedidos y asientos contables.\n\n" +
                                "Para probar la pagina en si y su interaccion con esta API, puedes hacerlo a traves del siguiente enlace: https://big-bite-teal.vercel.app \n\n" +
                                "Repositorios:\n" +
                                "- Backend: [https://github.com/Alvaro-Rubina/BigBite-Backend](https://github.com/Alvaro-Rubina/BigBite-Backend)\n" +
                                "- Frontend: [https://github.com/candelabp/Big-bite](https://github.com/candelabp/Big-bite)")
                );
    }
}