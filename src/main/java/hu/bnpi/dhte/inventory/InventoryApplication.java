package hu.bnpi.dhte.inventory;

import hu.bnpi.dhte.inventory.files.readers.ExcelReader;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static hu.bnpi.dhte.inventory.openapi.constant.SwaggerConstant.*;

@SpringBootApplication
public class InventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }

    @Bean
    @Order(value = Ordered.HIGHEST_PRECEDENCE)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

   @Bean
   public OpenAPI createOpenApiUI() {
       return new OpenAPI()
               .info(new Info()
                       .title(API_TITLE)
                       .version(API_VERSION)
                       .description(API_DESCRIPTION)
                       .contact(new Contact().name("Attila Ferenc")));
   }

    @Bean
    public ExcelReader createExcelReader () {
        return new ExcelReader();
    }
}
