package com.example.crud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int emp_Id;
	private String emp_Name;
	private String emp_Password;
	private String emp_Username;
	private int emp_Age;
	private long emp_MobileNumber;

	public int getEmp_Id() {
		return emp_Id;
	}

	public void setEmp_Id(int emp_Id) {
		this.emp_Id = emp_Id;
	}

	public String getEmp_Name() {
		return emp_Name;
	}

	public void setEmp_Name(String emp_Name) {
		this.emp_Name = emp_Name;
	}

	public String getEmp_Password() {
		return emp_Password;
	}

	public void setEmp_Password(String emp_Password) {
		this.emp_Password = emp_Password;
	}

	public String getEmp_Username() {
		return emp_Username;
	}

	public void setEmp_Username(String emp_Username) {
		this.emp_Username = emp_Username;
	}

	public int getEmp_Age() {
		return emp_Age;
	}

	public void setEmp_Age(int emp_Age) {
		this.emp_Age = emp_Age;
	}

	public long getEmp_MobileNumber() {
		return emp_MobileNumber;
	}

	public void setEmp_MobileNumber(long emp_MobileNumber) {
		this.emp_MobileNumber = emp_MobileNumber;
	}
}
