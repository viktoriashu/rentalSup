package com.viktoria.rentalSup.dao.impl;

import com.viktoria.rentalSup.dao.Dao;
import com.viktoria.rentalSup.dataSource.ConnectionManager;
import com.viktoria.rentalSup.entity.StatusClaim;
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
public class StatusClaimDao implements Dao<StatusClaim, Integer> {

    private static final String STATUS_CLAIM_ID = "id";
    private static final String STATUS_CLAIM = "status";


    private static final StatusClaimDao INSTANCE = new StatusClaimDao();

    public static StatusClaimDao getInstance() {
        return INSTANCE;
    }


    private static final String SAVE_SQL = """
            INSERT INTO status_claim(status)
            VALUES (?);
            """;

    private static final String DELETE_SQL = """
            DELETE FROM status_claim
            WHERE id = ?
            """;

    private static final String UPDATE_SQL = """
            UPDATE status_claim
            SET status = ?
            WHERE id = ?
            """;


    private static final String FIND_ALL_SQL = """
            SELECT status_claim.id,
            status
            FROM status_claim
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

            //throw new DaoException(String.format("Error when deleting status claim. Status claim with id %s not found", id), throwable.getCause());
        }
    }

    @Override
    public StatusClaim save(StatusClaim statusClaim) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, statusClaim.getStatus());

            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                statusClaim.setId(generatedKeys.getInt(STATUS_CLAIM_ID));
            }

            return statusClaim;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
            //throw new DaoException ("Error when saving status claim"), throwable.getCause());
        }
    }

    @Override
    public void update(StatusClaim statusClaim) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, statusClaim.getStatus());
            preparedStatement.setInt(2, statusClaim.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throw new DaoException(throwables);
            //throw new DaoException ("Error updating status claim"), throwable.getCause());
        }
    }

    @Override
    public Optional<StatusClaim> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();
            StatusClaim statusClaim = null;
            if (resultSet.next()) {
                statusClaim = buildStatusSup(resultSet);
            }

            return Optional.ofNullable(statusClaim);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
            //throw new DaoException(String.format("Status sup claim id %s not found", id), throwable.getCause());
        }

    }

    public Optional<StatusClaim> findById(Integer id, Connection connection) {
        try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();
            StatusClaim statusClaim = null;
            if (resultSet.next()) {
                statusClaim = new StatusClaim(
                        resultSet.getInt(STATUS_CLAIM_ID),
                        resultSet.getString(STATUS_CLAIM));
            }
            return Optional.ofNullable(statusClaim);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public List<StatusClaim> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<StatusClaim> statusClaims = new ArrayList<>();
            while (resultSet.next()) {
                statusClaims.add(buildStatusSup(resultSet));
            }
            return statusClaims;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }
    private StatusClaim buildStatusSup(ResultSet resultSet) throws SQLException{
        return StatusClaim.builder()
                .id(resultSet.getInt(STATUS_CLAIM_ID))
                .status(resultSet.getString(STATUS_CLAIM))
                .build();
    }

}
