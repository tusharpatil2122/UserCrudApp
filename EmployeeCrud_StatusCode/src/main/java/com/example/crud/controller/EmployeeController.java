package com.example.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.exception.UpdateNotPossibleException;
import com.example.crud.exception.UserNotFoundException;
import com.example.crud.model.Employee;
import com.example.crud.serviceI.EmployeeInterfaces;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeInterfaces empi;

	@PostMapping("/postEmployee")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		Employee saveEmployee = empi.saveEmployeeData(employee);
		return new ResponseEntity<Employee>(saveEmployee, HttpStatus.CREATED);
	}

	@GetMapping("/getEmployee")
	public ResponseEntity<List<Employee>> getAllData() {
		List<Employee> list = empi.getAllData();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/getEmployee/{emp_Id}")
	public ResponseEntity<Employee> exposeUser(@PathVariable int emp_Id) {
		Employee empId = empi.getSingleUser(emp_Id);
		return new ResponseEntity<Employee>(empId, HttpStatus.OK);
	}

	@PutMapping("/postEmployee/{emp_Id}")
	public ResponseEntity<Employee> updateUserByID(@PathVariable int emp_Id, @RequestBody Employee updateDetails) {
		Employee updatedUser = empi.updateUser(emp_Id, updateDetails);
		if (updatedUser == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		}
	}

	@ExceptionHandler(UpdateNotPossibleException.class)
	public ResponseEntity<String> updateNameException(UpdateNotPossibleException updateNot) {
		return new ResponseEntity<String>(updateNot.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/updateSingleData/{name}")
	public ResponseEntity<Employee> UpdateSingleData(@PathVariable String name, @RequestBody Employee u) {
		Employee singlUser = empi.updateSingleData(name, u);
		ResponseEntity<Employee> responseList = new ResponseEntity<Employee>(singlUser, HttpStatus.OK);
		return responseList;
	}

	@DeleteMapping("/postEmployee/{emp_Id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable int emp_Id) {
		boolean isDeleted = empi.deleteEmployeeId(emp_Id);
		if (!isDeleted) {
			return new ResponseEntity<>("Failed to delete employee with ID " + emp_Id, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>("Employee with ID " + emp_Id + " deleted successfully.", HttpStatus.OK);
		}
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFound(UserNotFoundException ue) {
		return new ResponseEntity<String>(ue.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UpdateNotPossibleException.class)
	public ResponseEntity<String> handleUpdateNotPossible(UpdateNotPossibleException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
