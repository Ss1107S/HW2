package by.orlova.dao;

import by.orlova.dao.db.ConnectionCreator;
import by.orlova.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommonDaoUtils {
    public boolean deleteEntityById(Integer id, String sqlQuery) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean result = false;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            int i = statement.executeUpdate();
            if (i > 0) result = true;
        } catch (SQLException e) {
            try {
                throw new DaoException(e);
            } catch (DaoException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
}
