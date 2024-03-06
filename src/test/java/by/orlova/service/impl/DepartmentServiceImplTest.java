package by.orlova.service.impl;

import by.orlova.TestDataBuilder;
import by.orlova.entity.Department;
import by.orlova.service.DepartmentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class DepartmentServiceImplTest {

    private final DepartmentService departmentService = new DepartmentServiceImpl();
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
    void findAllDepartments() {
        testDataBuilder.fillTables();
        List<Department> departmentList = departmentService.findAll();
        assertThat(departmentList).isNotEmpty();
    }

    @Test
    void findEntityById() {
        testDataBuilder.fillTables();
        IntStream.rangeClosed(1, 7)
                .forEach(value ->
                        assertThat(departmentService.findEntityById(value))
                                .isInstanceOf(Department.class));

    }

    @Test
    void deleteEntityById() {
        testDataBuilder.fillTables();
        boolean delete = departmentService.delete(1);
        assertThat(delete).isTrue();

    }

    @Test
    void deleteDepartmentByEntity() {
        testDataBuilder.fillTables();
        Department entityById = departmentService.findEntityById(1);
        boolean actual = departmentService.delete(entityById);
        assertThat(actual).isTrue();
    }

    @Test
    void createDepartment() {
        Department testDepartment = new Department("test department");
        boolean actual = departmentService.create(testDepartment);
        assertThat(actual).isTrue();
        assertThat(departmentService.findAll()).isNotEmpty();

    }
}