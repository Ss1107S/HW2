package by.orlova.service.impl;

import by.orlova.TestDataBuilder;
import by.orlova.entity.Department;
import by.orlova.entity.Employee;
import by.orlova.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeServiceImplTest {

    private final EmployeeService employeeService = new EmployeeServiceImpl();
    private final TestDataBuilder testDataBuilder = new TestDataBuilder();

    @BeforeEach
    void fillTables() {
        testDataBuilder.createTables();
    }

    @AfterEach
    void clearData() {
        testDataBuilder.close();
    }

    @Test
    void findAllEmployees() {
        testDataBuilder.fillTables();
        List<Employee> all = employeeService.findAll();
        assertThat(all).isNotEmpty();
    }

    @Test
    void findEntityById() {
        testDataBuilder.fillTables();
        IntStream.rangeClosed(1, 10).forEach(value -> assertThat(employeeService.findEntityById(value)).isInstanceOf(Employee.class));
    }

    @Test
    void deleteEmployeeByEmployeeInstance() {
        testDataBuilder.fillTables();
        IntStream.rangeClosed(1, 10).forEach(value -> {
            boolean delete = employeeService.delete(employeeService.findEntityById(value));
            assertThat(delete).isTrue();
            assertThat(employeeService.findEntityById(value)).isNull();
        });

    }

    @Test
    void deleteEmployeeByEmployeeId() {
        testDataBuilder.fillTables();
        IntStream.rangeClosed(1, 10).forEach(value -> {
            boolean deleteFirstTime = employeeService.delete(value);
            assertThat(deleteFirstTime).isTrue();
            assertThat(employeeService.findEntityById(value)).isNull();
            boolean deleteSecondTime = employeeService.delete(value);
            assertThat(deleteSecondTime).isFalse();
        });
    }

    @Test
    void create() {
        Employee testEmployee = new Employee("check name", "check surname", "", new Department(), Collections.emptyList());
        assertThat(employeeService.create(testEmployee)).isTrue();
    }

    @Test
    void update() {
        String nameBefore = "name before update";
        Employee testEmployee = new Employee(nameBefore, "check surname", "", new Department(), Collections.emptyList());
        testEmployee.setId(1);
        employeeService.create(testEmployee);
        String nameAfter = "name after update";
        testEmployee.setFirstName(nameAfter);
        Employee updatedEmployee = employeeService.update(testEmployee);
        assertThat(updatedEmployee).isEqualTo(testEmployee);
    }

    @Test
    void findEmployeeBySurname() {
        testDataBuilder.fillTables();
        List<Employee> testIvanov = employeeService.findEmployeeBySurname("TESTIvanov");
        assertThat(testIvanov.stream().findAny().get()).isInstanceOf(Employee.class);
    }
}