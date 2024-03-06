package by.orlova.service.impl;

import by.orlova.TestDataBuilder;
import by.orlova.entity.Position;
import by.orlova.service.PositionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class PositionServiceImplTest {

    private final PositionService positionService = new PositionServiceImpl();
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
    void findAllPositions() {
        testDataBuilder.fillTables();
        List<Position> allPositions = positionService.findAll();
        assertThat(allPositions).isNotEmpty();
    }

    @Test
    void findEntityById() {
        testDataBuilder.fillTables();
        IntStream.rangeClosed(1, 10).forEach(value -> assertThat(
                positionService.findEntityById(value))
                .isInstanceOf(Position.class));
    }

    @Test
    void findPositionByEmployeeId() {
        testDataBuilder.fillTables();
        IntStream.rangeClosed(1, 14)
                .forEach(value ->
                        assertThat(positionService.findPositionByEmployeeId(value)
                                .stream().findAny().get())
                                .isInstanceOf(Position.class));

    }

    @Test
    void deletePositionById() {
        testDataBuilder.fillTables();
        boolean actual = positionService.delete(1);
        assertThat(actual).isTrue();
    }

    @Test
    void deletePositionByEntity() {
        testDataBuilder.fillTables();
        Position entityById = positionService.findEntityById(1);
        boolean actual = positionService.delete(entityById);
        assertThat(actual).isTrue();

    }
}
