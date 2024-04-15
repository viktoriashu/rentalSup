package com.viktoria.rentalSup.dao;

import com.viktoria.rentalSup.entity.Role;
import com.viktoria.rentalSup.exception.DaoException;
import com.viktoria.rentalSup.dataSource.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleDao implements Dao<Role, Integer> {

    private static final String ROLE_ID = "id";
    private static final String ROLE_NAME = "role_name";


    private RoleDao() {
    }

    private static final RoleDao INSTANCE = new RoleDao();

    public static RoleDao getInstance() {
        return INSTANCE;
    }


    private static final String SAVE_SQL = """
            INSERT INTO role(role_name)
            VALUES (?);
            """;

    private static final String DELETE_SQL = """
            DELETE FROM role
            WHERE id = ?
            """;

    private static final String UPDATE_SQL = """
            UPDATE role
            SET role_name = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT role.id,
            role_name
            FROM role
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
                role.setId(generatedKeys.getInt(ROLE_ID));
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
            preparedStatement.setInt(2, role.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }

    }

    @Override
    public Optional<Role> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);

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

    public Optional<Role> findById(Integer id, Connection connection) {
        try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();
            Role role = null;
            if (resultSet.next()) {
                role = new Role(
                        resultSet.getInt(ROLE_ID),
                        resultSet.getString(ROLE_NAME)
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
        return new Role(resultSet.getInt(ROLE_ID),
                resultSet.getString(ROLE_NAME));
    }
}
