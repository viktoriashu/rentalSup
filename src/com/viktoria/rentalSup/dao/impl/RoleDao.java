package com.viktoria.rentalSup.dao.impl;

import com.viktoria.rentalSup.dao.Dao;
import com.viktoria.rentalSup.entity.Role;
import com.viktoria.rentalSup.enums.RoleEnum;
import com.viktoria.rentalSup.exception.DaoException;
import com.viktoria.rentalSup.dataSource.ConnectionManager;
import lombok.AccessLevel;
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

public class RoleDao implements Dao<Role, Integer> {

//для удаления если все работает
//    private static final String ROLE_ID = "id";
//    private static final String ROLE_NAME = "role_name";


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
            throw new DaoException(String.format("Error when deleting user role. Role with id %s not found", id), throwables.getCause());
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
                role.setId(generatedKeys.getInt(RoleEnum.ROLE_ID.getValue()));
            }

            return role;
        } catch (SQLException throwables) {
            throw new DaoException("Error when saving user role", throwables.getCause());
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
            throw new DaoException("Error updating user role", throwables.getCause());
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
            throw new DaoException(String.format("Role with id %s not found", id), throwables.getCause());
        }

    }

    public Optional<Role> findById(Integer id, Connection connection) {
        try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();
            Role role = null;
            if (resultSet.next()) {
                role = new Role(
                        resultSet.getInt(RoleEnum.ROLE_ID.getValue()),
                        resultSet.getString(RoleEnum.ROLE_NAME.getValue())
                );
            }

            return Optional.ofNullable(role);
        } catch (SQLException throwables) {
            throw new DaoException(String.format("Role with id %s not found", id), throwables.getCause());
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
            throw new DaoException("Error when calling a method findAll in user role", throwables.getCause());
        }
    }

    private Role buildRole(ResultSet resultSet) throws SQLException {
        return Role.builder()
                .id(resultSet.getInt(RoleEnum.ROLE_ID.getValue()))
                .roleName(resultSet.getString(RoleEnum.ROLE_NAME.getValue()))
                .build();
    }
}
