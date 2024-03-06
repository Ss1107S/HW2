package by.orlova.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Position extends BaseEntity {
    private String name;

    @JsonIgnore
    private List<Employee> employeeList;

    public Position() {
    }

    public Position(String name) {
        this.name = name;
    }

    public Position(String name, List<Employee> employeeList) {
        this.name = name;
        this.employeeList = employeeList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "Position{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}