package citas_medicas;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CitasMedicasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitasMedicasApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("API Rest - Citas medicas")
						.version("v1")
						.description("Un API Rest para un sistema de citas medicas, donde se puede loguear siendo paciente o medico, y dentro poder asignar citas a pacientes con sus respectivos diagnosticos, al mismo tiempo tambien se pueden asignar médicos a pacientes y viceversa.")
						.termsOfService("http://swagger.io/terms/")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
}
