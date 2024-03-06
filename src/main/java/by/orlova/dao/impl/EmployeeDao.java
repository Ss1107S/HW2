package by.orlova.dao;

import by.orlova.entity.Employee;
import by.orlova.dao.exception.DaoException;

import java.util.List;

public interface EmployeeDao extends BaseDao<Integer, Employee> {
    List<Employee> findEmployeeBySurname(String surname) throws DaoException;
    boolean deleteEmployeeFromEmployeesPositionsTable(int employeeId);
}