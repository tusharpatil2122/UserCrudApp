package com.example.crud.serviceI;

import java.util.List;

import com.example.crud.model.Employee;

public interface EmployeeInterfaces {

	public Employee saveEmployeeData(Employee employee);

	public List<Employee> getAllData();

	public Employee getSingleUser(int emp_Id);

	public boolean deleteEmployeeId(int emp_Id);

	public Employee updateSingleData(String emp_Name, Employee employee);

	public Employee updateUser(int emp_Id, Employee updateDetails);

}
