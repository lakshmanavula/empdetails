package com.heraizen.ems.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.heraizen.ems.domain.Employee;
import com.heraizen.ems.dto.EmployeeDTO;
import com.heraizen.ems.exception.DataNotFoundException;
import com.heraizen.ems.repo.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	private static Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
		Assert.notNull(employeeDTO, "Employee Can not be null");
		log.info("Employee with going add :{}", employeeDTO.getEname());

		Employee emp = modelMapper.map(employeeDTO, Employee.class);

		emp = employeeRepo.save(emp);
		log.info("Employee with saved id:{}", emp.getEmpno());
		employeeDTO = modelMapper.map(emp, EmployeeDTO.class);

		return employeeDTO;

	}

	@Override
	public List<EmployeeDTO> getAllEmployee() {

		List<Employee> empList = employeeRepo.findAll();
		List<EmployeeDTO> employeeDTOList = modelMapper.map(empList, new TypeToken<List<EmployeeDTO>>() {
		}.getType());

		return employeeDTOList;
	}

	@Override
	public List<EmployeeDTO> searchEmployee(String str) throws DataNotFoundException {
		Assert.notNull(str, "Searching string can not be null");
		log.info("Name: {}", str);
		MatchOperation match = Aggregation.match(Criteria.where("ename").regex(str, "i"));
		Aggregation query = Aggregation.newAggregation(match);
		log.info("Query" + query);
		AggregationResults<Employee> empRes = mongoOperations.aggregate(query, "employee", Employee.class);
		List<Employee> employeeList = empRes.getMappedResults();

		if (!(employeeList.isEmpty())) {
			List<EmployeeDTO> employeeDTOList = modelMapper.map(employeeList, new TypeToken<List<EmployeeDTO>>() {
			}.getType());
			return employeeDTOList;
		}
		throw new DataNotFoundException("Data Not Availale");

	}

	@Override
	public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) throws DataNotFoundException {
		Assert.notNull(employeeDTO, "Employee can not be null");
		Optional<Employee> emp = employeeRepo.findById(employeeDTO.getEmpno());
		if (emp.isPresent()) {
			Employee employee = modelMapper.map(employeeDTO, Employee.class);
			employee = employeeRepo.save(employee);

			return modelMapper.map(employee, EmployeeDTO.class);
		}
		throw new DataNotFoundException("Data not available");
	}

	@Override
	public boolean deleteEmployee(String empNo) throws DataNotFoundException {
		Assert.notNull(empNo, "Empno can not be null");
		Optional<Employee> emp = employeeRepo.findById(empNo);

		if (emp.isPresent()) {
			employeeRepo.deleteById(empNo);
			return true;
		}
		throw new DataNotFoundException("Data not available");

	}

	@Override
	public EmployeeDTO getEmployee(String empNo) throws DataNotFoundException {
		Optional<Employee> emp = employeeRepo.findById(empNo);
		if (emp.isPresent()) {
			Employee employee = emp.get();
			EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
			return employeeDTO;
		}
		throw new DataNotFoundException("Data not available");
	}

	@Override
	public boolean deteleAllEmployee() throws DataNotFoundException {
		List<Employee> empList = employeeRepo.findAll();
		if (!(empList.isEmpty())) {
			employeeRepo.deleteAll();
			return true;
		}
		throw new DataNotFoundException("Data not available");
	}

	@Override
	public List<EmployeeDTO> addEmployees(List<EmployeeDTO> employeeDTOList) throws DataNotFoundException {

		if (employeeDTOList.isEmpty()) {
			throw new DataNotFoundException("Data Not Found ");
		} else {
			List<Employee> employeeList = modelMapper.map(employeeDTOList, new TypeToken<List<Employee>>() {
			}.getType());
			employeeList = employeeRepo.saveAll(employeeList);
			employeeDTOList = modelMapper.map(employeeList, new TypeToken<List<EmployeeDTO>>() {
			}.getType());

			return employeeDTOList;

		}

	}

}
