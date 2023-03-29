/**
 * 
 */
package com.employee.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

/**
 * @author Tanuja N
 *
 */
@Configuration
public class OpenAPIConfig {

	@Value("http://localhost:9091")
	private String devUrl;

	@Bean
	public OpenAPI myAPI() {
		Server devServer = new Server();
		devServer.setUrl(devUrl);
		devServer.setDescription("Server URL in Development environment");

		Contact contact = new Contact();
		contact.setEmail("tanuja232002@gmail.com");
		contact.setName("TN456");

		Info info = new Info().title("Employee Management System API").version("1.0").contact(contact)
				.description("This API exposes endpoints to manage employees.");

		return new OpenAPI().info(info).servers(List.of(devServer));

	}
}
