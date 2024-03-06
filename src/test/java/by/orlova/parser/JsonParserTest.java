package by.orlova.parser;

import by.orlova.entity.Department;
import by.orlova.entity.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

class JsonParserTest {

    private final JsonParser parser = new JsonParser();

    @Test
    void parseObjectToJson() {
        Employee testEmployee = new Employee("testName", "testSurname", "testTelephone",
                new Department("testDepartment"), Collections.emptyList());
        String expected = "{\"id\":0,\"firstName\":\"testName\",\"surname\":\"testSurname\",\"telephone\":\"testTelephone\",\"department\":{\"id\":0,\"name\":\"testDepartment\"},\"positionList\":[]}";
        String actual = parser.parseObjectToJson(testEmployee);
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void parseJsonToEmployee() {
        String testJson = "{\"id\":0,\"firstName\":\"testName\",\"surname\":\"testSurname\",\"telephone\":\"testTelephone\",\"department\":{\"id\":0,\"name\":\"testDepartment\"},\"positionList\":[]}";
        Employee expectedEmployee = new Employee("testName", "testSurname", "testTelephone",
                new Department("testDepartment"), Collections.emptyList());
        Employee actualEmployee = parser.parseJsonToObject(testJson);
        Assertions.assertThat(actualEmployee).isEqualTo(expectedEmployee);
    }
}