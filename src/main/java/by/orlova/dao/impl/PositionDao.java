package by.orlova.dao;

import by.orlova.entity.Position;
import by.orlova.dao.exception.DaoException;

import java.util.List;

public interface PositionDao extends BaseDao<Integer, Position> {
    List<Position> findPositionByEmployeeId(int employeeId) throws DaoException;

    boolean deletePositionFromEmployeesPositionsTable(int positionId);

}