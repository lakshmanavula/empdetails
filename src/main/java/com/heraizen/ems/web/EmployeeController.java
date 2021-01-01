package com.heraizen.ems.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heraizen.ems.auth.IAuthenticationFacade;
import com.heraizen.ems.dto.EmployeeDTO;
import com.heraizen.ems.exception.DataNotFoundException;
import com.heraizen.ems.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/emp/")
public class EmployeeController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IAuthenticationFacade authFacade;
	
	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employeeDTO) {
		return employeeService.addEmployee(employeeDTO);
	}

	@GetMapping(value = "all")
	public List<EmployeeDTO> getEmployees(EmployeeDTO employeeDTO) {
		log.info("The user :{} is request for all employees",authFacade.authentication().getName());
		return employeeService.getAllEmployee();
	}

	@GetMapping(value = "search/{name}")
	public List<EmployeeDTO> searchEmployee(@PathVariable("name") String str) throws DataNotFoundException {
		return employeeService.searchEmployee(str);
	}

	
	@PreAuthorize(value = "hasRole('ADMIN')")
	@DeleteMapping(value = "{id}")
	public Response deleteEmp(@PathVariable("id") String empNo) throws DataNotFoundException {
		boolean res = employeeService.deleteEmployee(empNo);
		if (res) {
			Response response = Response.builder().message("Employee Deleted Successfully").build();
			return response;
		}
		throw new DataNotFoundException("Data not available");
	}

	@PutMapping
	public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employeeDTO) throws DataNotFoundException {
		return employeeService.updateEmployee(employeeDTO);
	}

	@PostMapping(value = "addall")
	public List<EmployeeDTO> updateEmployee(@RequestBody List<EmployeeDTO> employeeDTOList)
			throws DataNotFoundException {
		return employeeService.addEmployees(employeeDTOList);
	}

}
