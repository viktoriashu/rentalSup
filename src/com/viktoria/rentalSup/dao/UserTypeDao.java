package com.viktoria.rentalSup.dao;

import com.viktoria.rentalSup.dto.UserTypeFilter;
import com.viktoria.rentalSup.entity.Role;
import com.viktoria.rentalSup.entity.UserType;
import com.viktoria.rentalSup.exception.DaoException;
import com.viktoria.rentalSup.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserTypeDao implements Dao<Long, UserType> {

    public UserTypeDao() {
    }

    private static final UserTypeDao INSTANCE = new UserTypeDao();

    public static UserTypeDao getInstance() {
        return INSTANCE;
    }

    private final RoleDao roleDao = RoleDao.getInstance();

    private static final String DELETE_SQL = """
            DELETE FROM rental_sup_board.user_type
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO rental_sup_board.user_type(first_name, last_name, login, password, number, id_role)
            VALUES (?, ?, ?, ?, ?, ?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE rental_sup_board.user_type
            SET first_name = ?,
            last_name = ?,
            login = ?,
            password = ?,
            number = ?,
            id_role = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT rental_sup_board.user_type.id,
            first_name,
            last_name,
            login,
            password,
            number,
            r.role_name
            FROM rental_sup_board.user_type
            JOIN rental_sup_board.role r
            on user_type.id_role = r.id
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE user_type.id = ?
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
    public UserType save(UserType userType) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, userType.getFirstName());
            preparedStatement.setString(2, userType.getLastName());
            preparedStatement.setString(3, userType.getLogin());
            preparedStatement.setString(4, userType.getPassword());
            preparedStatement.setString(5, userType.getNumber());
            preparedStatement.setLong(6, userType.getRole().getId());

            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                userType.setId(generatedKeys.getLong("id"));
            }
            return userType;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public void update(UserType userType) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, userType.getFirstName());
            preparedStatement.setString(2, userType.getLastName());
            preparedStatement.setString(3, userType.getLogin());
            preparedStatement.setString(4, userType.getPassword());
            preparedStatement.setString(5, userType.getNumber());
            preparedStatement.setLong(6, userType.getRole().getId());
            preparedStatement.setLong(7, userType.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public Optional<UserType> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);

            var resultSet = preparedStatement.executeQuery();
            UserType userType = null;
            if (resultSet.next()) {
                userType = buildUserType(resultSet);
            }

            return Optional.ofNullable(userType);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public List<UserType> findAll(UserTypeFilter filter) {
        List<Object> parameters = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();
        if (filter.number() != null) {
            whereSql.add("number LIKE ?");
            parameters.add("%" + filter.number() + "%");
        }
        if (filter.lastName() != null) {
            whereSql.add("last_name = ?");
            parameters.add(filter.lastName());
        }

        parameters.add(filter.limit());
        parameters.add(filter.offset());

        var where = whereSql.stream()
                .collect(Collectors.joining(" AND ", " WHERE ", " LIMIT ? OFFSET ? "));

        var sql = FIND_ALL_SQL + where;
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }
            System.out.println(preparedStatement);
            var resultSet = preparedStatement.executeQuery();
            List<UserType> userTypes = new ArrayList<>();
            while (resultSet.next()) {
                userTypes.add(buildUserType(resultSet));
            }
            return userTypes;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public List<UserType> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<UserType> userTypes = new ArrayList<>();
            while (resultSet.next()) {
                userTypes.add(buildUserType(resultSet));
            }
            return userTypes;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    private UserType buildUserType(ResultSet resultSet) throws SQLException {
        var role = new Role(
                resultSet.getLong("id"),
                resultSet.getString("role_name")
        );
        return new UserType(resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("login"),
                resultSet.getString("password"),
                resultSet.getString("number"),
                roleDao.findById(resultSet.getLong("id"),
                        resultSet.getStatement().getConnection()).orElse(null));

    }
}
