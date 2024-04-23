package com.viktoria.rentalSup.dao.impl;

import com.viktoria.rentalSup.dao.Dao;
import com.viktoria.rentalSup.dataSource.ConnectionManager;
import com.viktoria.rentalSup.entity.StatusSup;
import com.viktoria.rentalSup.exception.DaoException;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static lombok.AccessLevel.*;

@NoArgsConstructor(access = PRIVATE)

public class StatusSupDao implements Dao<StatusSup, Integer> {

    private static final String STATUS_SUP_ID = "id";
    private static final String STATUS_SUP = "status";


    private static final StatusSupDao INSTANCE = new StatusSupDao();

    public static StatusSupDao getInstance() {
        return INSTANCE;
    }

    private static final String SAVE_SQL = """
            INSERT INTO status_sup(status)
            VALUES (?);
            """;

    private static final String DELETE_SQL = """
            DELETE FROM status_sup
            WHERE id = ?
            """;

    private static final String UPDATE_SQL = """
            UPDATE status_sup
            SET status = ?
            WHERE id = ?
            """;


    private static final String FIND_ALL_SQL = """
            SELECT status_sup.id,
            status
            FROM status_sup
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;


    @Override
    public boolean delete(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);

            //throw new DaoException(String.format("Error when deleting status sup. Status sup with id %s not found", id), throwable.getCause());
        }
    }

    @Override
    public StatusSup save(StatusSup statusSup) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, statusSup.getStatus());

            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                statusSup.setId(generatedKeys.getInt(STATUS_SUP_ID));
            }

            return statusSup;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
            //throw new DaoException ("Error when saving status sup"), throwable.getCause());
        }
    }

    @Override
    public void update(StatusSup statusSup) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, statusSup.getStatus());
            preparedStatement.setInt(2, statusSup.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throw new DaoException(throwables);
            //throw new DaoException ("Error updating status sup"), throwable.getCause());
        }
    }

    @Override
    public Optional<StatusSup> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();
            StatusSup statusSup = null;
            if (resultSet.next()) {
                statusSup = buildStatusSup(resultSet);
            }

            return Optional.ofNullable(statusSup);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
            //throw new DaoException(String.format("Status sup with id %s not found", id), throwable.getCause());
        }
    }

    public Optional<StatusSup> findById(Integer id, Connection connection) {
        try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();
            StatusSup statusSup = null;
            if (resultSet.next()) {
                statusSup = new StatusSup(
                        resultSet.getInt(STATUS_SUP_ID),
                        resultSet.getString(STATUS_SUP));
            }
            return Optional.ofNullable(statusSup);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public List<StatusSup> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<StatusSup> statusSups = new ArrayList<>();
            while (resultSet.next()) {
                statusSups.add(buildStatusSup(resultSet));
            }
            return statusSups;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    private StatusSup buildStatusSup(ResultSet resultSet) throws SQLException{
        return StatusSup.builder()
                .id(resultSet.getInt(STATUS_SUP_ID))
                .status(resultSet.getString(STATUS_SUP))
                .build();
    }

}
