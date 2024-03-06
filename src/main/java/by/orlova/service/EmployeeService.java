package by.orlova.service;

import by.orlova.entity.Employee;

import java.util.List;

public interface EmployeeService extends BaseService<Integer, Employee> {

    List<Employee> findEmployeeBySurname(String surname);

}