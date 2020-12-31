package com.heraizen.ems.service;

import java.util.List;

import com.heraizen.ems.dto.EmployeeDTO;
import com.heraizen.ems.exception.DataNotFoundException;



public interface EmployeeService {
	public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) ;

	public List<EmployeeDTO> getAllEmployee();
	
	public List<EmployeeDTO> addEmployees(List<EmployeeDTO> employeeDTOList) throws  DataNotFoundException;

	public List<EmployeeDTO> searchEmployee(String str) throws DataNotFoundException;

	public EmployeeDTO updateEmployee(EmployeeDTO e) throws DataNotFoundException;

	public boolean deleteEmployee(String empNo) throws DataNotFoundException;

	public EmployeeDTO getEmployee(String empNo) throws DataNotFoundException;

	boolean deteleAllEmployee() throws DataNotFoundException;
}
