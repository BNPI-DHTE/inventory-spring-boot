package hu.bnpi.dhte.inventory;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventorySpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventorySpringBootApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Inventory Item API")
                        .version("1.0.220524")
                        .description("Operations with inventory items")
                        .contact(new Contact().name("Attila Ferenc").email("ferenca@bnpi.hu")));
    }
}
