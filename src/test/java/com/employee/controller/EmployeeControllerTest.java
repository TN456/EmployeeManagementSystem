package com.employee.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.employee.model.Employee;
import com.employee.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Tanuja N
 *
 */
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;

	@Autowired
	private ObjectMapper objectMapper;
	private Employee employee;

	@BeforeEach
	void setUp() {
		employee = new Employee(1, "Tanuja", "2836918", "tanujalsj");
	}

	@Test
	public void testAddEmployee() throws Exception {
		Employee employee1 = new Employee(1, "Tanuja", "2836918123", "tanujalsj@gmail.com");
		Mockito.when(employeeService.saveEmployee(employee1)).thenReturn(employee);
		mockMvc.perform(post("/api/addEmployee")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(employee1)))
				.andExpect(status().isCreated()).andDo(print());
	}

	@Test
	public void testGetEmployee() throws Exception {
		int id = 1;
		Employee employee1 = new Employee(id, "Tanuja", "2836918", "tanujalsj");
		when(employeeService.getEmployeeById(id)).thenReturn(employee1);
		mockMvc.perform(get("/api/employee/{id}", id)).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(id))
				.andExpect(jsonPath("$.name").value(employee1.getName()))
				.andExpect(jsonPath("$.phonenumber").value(employee1.getPhonenumber()))
				.andExpect(jsonPath("$.email").value(employee1.getEmail())).andDo(print());
	}

	@Test
	void testListOfEmployees() throws Exception {
		List<Employee> employees = new ArrayList<>(Arrays.asList(new Employee(1, "Tanuja", "2836918", "tanujalsj"),
				new Employee(1, "Tanuja", "2836918", "tanujalsj"), new Employee(1, "Tanuja", "2836918", "tanujalsj")));

		when(employeeService.getAllEmployee()).thenReturn(employees);
		mockMvc.perform(get("/api/employee")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(employees.size())).andDo(print());
	}

	@Test
	void testUpdateEmployee() throws Exception {
//		int id = 1;
//		Employee employee = new Employee(id, "John", "Doe", "john.doe@example.com");
//	    Employee updatedEmployee = new Employee(id, "Updated", "Updated", "true.com");
//	    when(employeeService.getEmployeeById(id)).thenReturn(employee);
//	    when(employeeService.saveEmployee(any(Employee.class))).thenReturn(updatedEmployee);
//	    
//	    mockMvc.perform(put("/api/employee/{id}", id).contentType(MediaType.APPLICATION_JSON)
//		        .content(objectMapper.writeValueAsString(updatedEmployee)))
//		        .andExpect(status().isOk())
//		        .andExpect(jsonPath("$.name").value(updatedEmployee.getName()))
//		        .andExpect(jsonPath("$.phonenumber").value(updatedEmployee.getPhonenumber()))
//		        .andExpect(jsonPath("$.email").value(updatedEmployee.getEmail()))
//		        .andDo(print());

		Employee employee = new Employee(1, "John", "6424837482", "john.doe@example.com");
	    String jsonRequest = new ObjectMapper().writeValueAsString(employee);

	    when(employeeService.updateEmployee(any(Employee.class), eq(1))).thenReturn(employee);

	    mockMvc.perform(put("/api/employee/1",1)
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(jsonRequest))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.id", is(1)))
	            .andExpect(jsonPath("$.name", is("John")))
	            .andExpect(jsonPath("$.phonenumber", is("6424837482")))
	            .andExpect(jsonPath("$.email", is("john.doe@example.com")));
	}

	@Test
	void testDeleteEmployee() throws Exception {
		int id = 1;

		doNothing().when(employeeService).deleteEmployee(id);
		mockMvc.perform(delete("/api/employee/{id}", id)).andExpect(status().isOk()).andDo(print());
	}
}
