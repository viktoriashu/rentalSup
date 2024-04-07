package com.viktoria.rentalSup.dao;

import com.viktoria.rentalSup.entity.Role;
import com.viktoria.rentalSup.exception.DaoException;
import com.viktoria.rentalSup.util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleDao implements Dao<Long, Role> {

    private static final RoleDao INSTANCE = new RoleDao();

    public static RoleDao getInstance() {
        return INSTANCE;
    }


    private static final String SAVE_SQL = """
            INSERT INTO rental_sup_board.role(role_name)
            VALUES (?);
            """;

    private static final String DELETE_SQL = """
            DELETE FROM rental_sup_board.role
            WHERE id = ?
            """;

    private static final String UPDATE_SQL = """
            UPDATE rental_sup_board.role
            SET role_name = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT rental_sup_board.role.id,
            role_name
            FROM rental_sup_board.role
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;


    @Override
    public boolean delete(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public Role save(Role role) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, role.getRoleName());

            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                role.setId(generatedKeys.getLong("id"));
            }

            return role;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public void update(Role role) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, role.getRoleName());
            preparedStatement.setLong(2, role.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }

    }

    @Override
    public Optional<Role> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);

            var resultSet = preparedStatement.executeQuery();
            Role role = null;
            if (resultSet.next()) {
                role = buildRole(resultSet);
            }

            return Optional.ofNullable(role);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }

    }

    public Optional<Role> findById(Long id, Connection connection) {
        try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);

            var resultSet = preparedStatement.executeQuery();
            Role role = null;
            if (resultSet.next()) {
                role = new Role(
                        resultSet.getLong("id"),
                        resultSet.getString("role_name")
                );
            }

            return Optional.ofNullable(role);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }


    @Override
    public List<Role> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Role> roles = new ArrayList<>();
            while (resultSet.next()) {
                roles.add(buildRole(resultSet));
            }
            return roles;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }


    private Role buildRole(ResultSet resultSet) throws SQLException {
        return new Role(resultSet.getLong("id"),
                resultSet.getString("role_name"));
    }
}
