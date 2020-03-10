package com.springdatajparest.exampl.SpringBootDataJpaRest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class EmployeeRestController {
	private static final Logger LOGGER = LogManager.getLogger(EmployeeRestController.class);

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	DataSource dataSource;

	@Autowired
	private CacheManager cacheManager;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/api/employees")
	public List<Employee> getEmployees() {
		LOGGER.debug("/api/employees --->@GetMapping  method called");
		List<Employee> employees = employeeService.retrieveEmployees();
		return employees;
	}

	@GetMapping("/api/employees/{employeeId}")
	public Employee getEmployee(@PathVariable(name = "employeeId") Long employeeId) {
		LOGGER.debug("/api/employees/{employeeId} --->@GetMapping method called");
		return employeeService.getEmployee(employeeId);
	}

	@PostMapping("/api/employees")
	public void saveEmployee(Employee employee) {
		LOGGER.debug("/api/employees---> @PostMapping method called");
		if (employee.getSalary() == null) {
			throw new InvalidFieldException("employee salary must not be null");
		}
		employeeService.saveEmployee(employee);
		System.out.println("Employee Saved Successfully");
	}

	@DeleteMapping("/api/employees/{employeeId}")
	public void deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {
		LOGGER.debug("/api/employees/{employeeId}--->@DeleteMapping  method called");
		employeeService.deleteEmployee(employeeId);
		System.out.println("Employee Deleted Successfully");
	}

	@PutMapping("/api/employees/{employeeId}")
	public void updateEmployee(@RequestBody Employee employee, @PathVariable(name = "employeeId") Long employeeId) {
		LOGGER.debug("/api/employees/{employeeId}--->@PutMapping  method called");
		Employee emp = employeeService.getEmployee(employeeId);
		if (emp != null) {
			employeeService.updateEmployee(employee);
		}
	}

	@PostMapping("/api/employees/saveAll")
	public ResponseEntity<ArrayList<Employee>> saveAllEmployees(@RequestBody ArrayList<Employee> employees) {
		LOGGER.debug("api/employees/saveAll--->@PostMapping  method called");
		ArrayList<Employee> employees1 = employeeService.saveMultipleEmployees(employees);
		return new ResponseEntity<ArrayList<Employee>>(employees1, HttpStatus.OK);
	}

	@GetMapping("/api/cacheExample/employees")
	@Cacheable(value = "ten-sec-cache")
	public List<Employee> getEmployeesCacheExample() throws InterruptedException {
		Thread.sleep(2000);
		List<Employee> employees = employeeService.retrieveEmployees();
		return employees;
	}

	@Scheduled(fixedRate = 10000)
	public void clearCache() {
		for (String name : cacheManager.getCacheNames()) {
			System.out.println("cache cleared after 10 seconds......>" + new Date());
			LOGGER.debug("cache cleared after 10 seconds......>"+ new Date());
			cacheManager.getCache(name).clear(); // clear cache by name
		}
	}

	@GetMapping("/api/testJPA/{department}")
	public Object[] testJPAQueryAnnotations(@PathVariable("department") String department) {
		return employeeService.testJPA(department);
	}
	
	//one to many testing
	@PostMapping("/api/oneToManyTesting")
	public ResponseEntity<Vendor> saveVendor(@RequestBody Vendor vendor) {
		LOGGER.debug("/api/oneToManyTesting--->@PostMapping  method called");
		Vendor v = employeeService.saveVendor(vendor);
		return new ResponseEntity<Vendor>(v, HttpStatus.OK);
	}

}
