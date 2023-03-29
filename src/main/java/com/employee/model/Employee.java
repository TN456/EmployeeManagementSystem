package com.employee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author Tanuja N
 *
 */
@Document(collection = "Employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "Employee Model Information"
)
public class Employee {
	
	@Id
	@Schema(
            description = "Employee Id"
    )
	@NotNull(message = "Employee id should not be null or empty")
	private int id;
	@Schema(
            description = "Employee Name"
    )
	@NotBlank(message = "Employee name should not be null or empty")
	private String name;
	@Schema(
            description = "Employee Phone number"
    )
	@NotBlank(message = "Employee phone number should not be null or empty")
	@Size(min = 10, max = 10, message = "Employee phonenumber should be of 10 digits")
	private String phonenumber;
	@Schema(
            description = "Employee Email"
    )
	@NotBlank(message = "Employee email should not be null or empty")
	@Email(message = "Email should be valid")
	private String email;
}
