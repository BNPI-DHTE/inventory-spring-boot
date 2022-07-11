package hu.bnpi.dhte.inventory;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }

    @Bean
    public OpenAPI createOpenApiUI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Management of inventory")
                        .version("0.0.2-SNAPSHOT")
                        .description("REST Api for inventory CRUD operations")
                        .contact(new Contact().name("Attila Ferenc")));
    }
}
