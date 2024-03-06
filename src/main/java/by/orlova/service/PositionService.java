package by.orlova.service;

import by.orlova.entity.Position;

import java.util.List;

public interface PositionService extends BaseService<Integer, Position> {
    List<Position> findPositionByEmployeeId(int employeeId);

}