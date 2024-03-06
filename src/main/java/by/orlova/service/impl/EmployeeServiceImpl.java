package by.orlova.service.impl;

import by.orlova.entity.Employee;
import by.orlova.dao.EmployeeDao;
import by.orlova.dao.exception.DaoException;
import by.orlova.dao.impl.EmployeeDaoImpl;
import by.orlova.service.DepartmentService;
import by.orlova.service.EmployeeService;
import by.orlova.service.PositionService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao = new EmployeeDaoImpl();
    private final DepartmentService departmentService = new DepartmentServiceImpl();
    private final PositionService positionService = new PositionServiceImpl();

    @Override
    public List<Employee> findAll() {
        List<Employee> employeeList;
        try {
            employeeList = employeeDao.findAll();
            employeeList.forEach(employee ->
            {
                employee
                        .setDepartment(departmentService
                                .findEntityById(
                                        employee.getDepartment().getId()));
                employee.setPositionList(positionService
                        .findPositionByEmployeeId(employee.getId()));
            });

        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }

    @Override
    public Employee findEntityById(Integer id) {
        Employee employeeById;
        try {
            employeeById = employeeDao.findEntityById(id);
            if (employeeById != null) {
                employeeById.setDepartment(departmentService
                        .findEntityById(
                                employeeById.getDepartment().getId()));
                employeeById.setPositionList(positionService.findPositionByEmployeeId(id));
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        return employeeById;
    }

    @Override
    public boolean delete(Employee employee) {
        boolean result;
        try {
            boolean a = employeeDao.deleteEmployeeFromEmployeesPositionsTable(employee.getId());
            boolean b = employeeDao.delete(employee);
            result = a && b;
        } catch (DaoException e) {
            throw new RuntimeException("Something went wrong with employee: " + employee + ". " + e);
        }
        return result;
    }

    @Override
    public boolean delete(Integer id) {
        boolean result;
        try {
            boolean a = employeeDao.deleteEmployeeFromEmployeesPositionsTable(id);
            boolean b = employeeDao.delete(id);
            result = a && b;
        } catch (DaoException e) {
            throw new RuntimeException("Something went wrong with employee with id: " + id + ". " + e);
        }
        return result;
    }

    @Override
    public boolean create(Employee employee) {
        try {
            return employeeDao.create(employee);
        } catch (DaoException e) {
            throw new RuntimeException("Something went wrong with during creation of employee: " + employee + ". " + e);
        }
    }

    @Override
    public Employee update(Employee employee) {
        try {
            return employeeDao.update(employee);
        } catch (DaoException e) {
            throw new RuntimeException("Something went wrong with during update of employee: " + employee + ". " + e);
        }
    }

    @Override
    public List<Employee> findEmployeeBySurname(String surname) {
        try {
            return employeeDao.findEmployeeBySurname(surname);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

}