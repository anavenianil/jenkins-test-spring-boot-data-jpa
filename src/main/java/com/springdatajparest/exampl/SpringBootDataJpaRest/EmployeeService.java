package com.springdatajparest.exampl.SpringBootDataJpaRest;

import java.util.ArrayList;
import java.util.List;



public interface EmployeeService {

	public List<Employee> retrieveEmployees();

	public Employee getEmployee(Long employeeId);

	public void saveEmployee(Employee employee);

	public void deleteEmployee(Long employeeId);

	public void updateEmployee(Employee employee);
	
	public ArrayList<Employee> saveMultipleEmployees(ArrayList<Employee> employees);
	
	public Object[] testJPA(String department);

	public Vendor saveVendor(Vendor vendor);
}
