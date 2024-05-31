package com.example.crud.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.exception.UpdateNotPossibleException;
import com.example.crud.exception.UserNotFoundException;
import com.example.crud.model.Employee;
import com.example.crud.repository.EmployeeRepository;
import com.example.crud.serviceI.EmployeeInterfaces;

@Service
public class EmployeeImpl implements EmployeeInterfaces {

	@Autowired
	EmployeeRepository empr;

	@Override
	public Employee saveEmployeeData(Employee employee) {
		Employee save = empr.save(employee);
		return save;
	}

	@Override
	public List<Employee> getAllData() {
		return empr.findAll();
	}

	@Override
	public Employee getSingleUser(int emp_Id) {
		Optional<Employee> byId = empr.findById(emp_Id);
		if (byId.isPresent()) {
			return byId.get();
		} else {
			throw new UserNotFoundException("No Record Found For Employee ID:- " + emp_Id);
		}
	}

	@Override
	public Employee updateSingleData(String emp_Name, Employee employee) {
		Employee checkEmployee = empr.findByEmp_Name(emp_Name);
		if (checkEmployee == null) {
			throw new UpdateNotPossibleException("Name is not present to update " + emp_Name);
		} else {
			empr.save(employee);
			return empr.findByEmp_Name(emp_Name);
		}
	}

	@Override
	public Employee updateUser(int emp_Id, Employee updateDetails) {
		Optional<Employee> byId = empr.findById(emp_Id);
		if (!byId.isPresent()) {
			return null;
		} else {
			updateDetails.setEmp_Id(emp_Id);
			empr.save(updateDetails);
			return updateDetails;
		}
	}

	@Override
	public boolean deleteEmployeeId(int emp_Id) {
		Optional<Employee> optional = empr.findById(emp_Id);
		if (!optional.isPresent()) {
			return false;
		} else {
			empr.deleteById(emp_Id);
			return true;
		}
	}
}
