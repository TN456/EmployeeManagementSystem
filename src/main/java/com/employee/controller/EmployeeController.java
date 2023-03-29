package com.employee.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.model.Employee;
import com.employee.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * @author Tanuja N
 *
 */
@Tag(name = "CRUD Rest API's for Employee Resource", description = "Employee Management System APIs")
@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Operation(description = "Save Employee Rest API is used to save employee in Mongodb", summary = "Add Employee Rest API")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "HTTP Status 201 - Created"),
			@ApiResponse(responseCode = "404", description = "HTTP Status 404 - Not Found"),
			@ApiResponse(responseCode = "500", description = "HTTP Status 500 - Internal Server Error") })
	@PostMapping("/addEmployee")
	public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}

	@Operation(description = "Get All Employees Rest API is used to get all employees from Mongodb", summary = "Get All Employee Rest API")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "HTTP Status 200 - Success"),
			@ApiResponse(responseCode = "404", description = "HTTP Status 404 - Not Found"),
			@ApiResponse(responseCode = "500", description = "HTTP Status 500 - Internal Server Error") })
	@GetMapping("/employee")
	public List<Employee> getAllEmployee() {
		return employeeService.getAllEmployee();
	}

	@Operation(description = "Get Employee By Id Rest API is used to get employee by Id from Mongodb", summary = "Add Employee By Id Rest API")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "HTTP Status 200 - Success"),
			@ApiResponse(responseCode = "404", description = "HTTP Status 404 - Not Found"),
			@ApiResponse(responseCode = "500", description = "HTTP Status 500 - Internal Server Error") })
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int employeeId) {
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
	}

	@Operation(description = "Updae Employee Rest API is used to update employee in Mongodb", summary = "Update Employee Rest API")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "HTTP Status 200 - Success"),
			@ApiResponse(responseCode = "404", description = "HTTP Status 404 - Not Found"),
			@ApiResponse(responseCode = "500", description = "HTTP Status 500 - Internal Server Error") })
	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee employee, @PathVariable("id") int employeeId) {
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, employeeId), HttpStatus.OK);
	}

	@Operation(description = "Delete Employee Rest API is used to delete employee from Mongodb", summary = "Delete Employee Rest API")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "HTTP Status 200 - Success"),
			@ApiResponse(responseCode = "404", description = "HTTP Status 404 - Not Found"),
			@ApiResponse(responseCode = "500", description = "HTTP Status 500 - Internal Server Error") })
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id) {
		employeeService.deleteEmployee(id);
		return new ResponseEntity<String>("Employee deleted successfully", HttpStatus.OK);
	}
}
