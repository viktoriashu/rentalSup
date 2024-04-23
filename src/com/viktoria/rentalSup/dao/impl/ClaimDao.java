package com.viktoria.rentalSup.dao.impl;

import com.viktoria.rentalSup.dao.Dao;
import com.viktoria.rentalSup.dataSource.ConnectionManager;
import com.viktoria.rentalSup.entity.*;
import com.viktoria.rentalSup.exception.DaoException;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.*;

@NoArgsConstructor(access = PRIVATE)

public class ClaimDao implements Dao<Claim, Long> {

    private static final String CLAIM_ID = "id";
    private static final String ID_CLIENT = "id_client";
    private static final String ID_ADMIN = "id_admin";
    private static final String ID_SUP = "id_sup";
    private static final String ID_STATUS_CLAIM = "id_status_claim";
    private static final String DATA_START_RENT = "data_start_rent";
    private static final String DURATION_RENT = "duration_rent";
    private static final String PRICE = "price";


    private static final String USER_TYPE_ID = "id";
    private static final String UT_FIRST_NAME = "first_name";
    private static final String UT_LAST_NAME = "last_name";
    private static final String UT_LOGIN = "login";
    private static final String UT_PASSWORD = "password";
    private static final String UT_NUMBER = "number";
    private static final String UT_ID_ROLE = "id_role";

    private static final String R_ID = "id";
    private static final String R_NAME = "role_name";

    private static final String SS_ID = "id";
    private static final String SS_STATUS = "status";

    private static final String SUP_ID = "id";
    private static final String SUP_MODEL = "model";

    private static final String STATUS_CLAIM_ID = "id";
    private static final String STATUS_CLAIM = "status";


    private static final ClaimDao INSTANCE = new ClaimDao();

    public static ClaimDao getInstance() {
        return INSTANCE;
    }

    private static final UserTypeDao userTypeDao = UserTypeDao.getInstance();

    private static final String DELETE_SQL = """
            DELETE FROM claim
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO claim(id_client, id_admin, id_sup, id_status_claim, data_start_rent,
            duration_rent,price)
            VALUES (?, ?, ?, ?, ?, ?, ?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE claim
            SET id_client = ?,
            id_admin = ?,
            id_sup = ?,
            id_status_claim = ?,
            data_start_rent = ?,
            duration_rent = ?,
            price = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT claim.id,
            id_client,
                ut1.first_name,
                ut1.last_name,
                ut1.login,
                ut1.password,
                ut1.number,
                    r1.role_name,
            id_admin,
                ut2.first_name,
                ut2.last_name,
                ut2.login,
                ut2.password,
                ut2.number,
                    r2.role_name,
            id_sup,
                s.model,
                ss.status,
            id_status_claim,
                sc.status,
            data_start_rent,
            duration_rent,
            price
            FROM claim
                JOIN user_type ut1 on ut1.id = claim.id_client
                JOIN user_type ut2 on ut2.id = claim.id_admin
                JOIN role r1 on r1.id = ut1.id_role
                JOIN role r2 on r2.id = ut2.id_role
                JOIN public.sup s on s.id = claim.id_sup
                JOIN public.status_sup ss on ss.id = s.id_status_sup
                JOIN public.status_claim sc on sc.id = claim.id_status_claim
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE claim.id = ?
            """;

    //закончила тут

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
    public Claim save(Claim claim) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, claim.getClient().getId());
            preparedStatement.setLong(2, claim.getAdmin().getId());
            preparedStatement.setLong(3, claim.getSup().getId());
            preparedStatement.setInt(4, claim.getSup().getStatusSup().getId());
            //Тут проблема
            preparedStatement.setTimestamp(5, Timestamp.valueOf(claim.getDataStartRent().atStartOfDay()));
            //
            preparedStatement.setInt(6, claim.getDurationRent());
            preparedStatement.setBigDecimal(7, claim.getPrice());


            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                claim.setId(generatedKeys.getLong(CLAIM_ID));
            }
            return claim;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public void update(Claim claim) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setLong(1, claim.getClient().getId());
            preparedStatement.setLong(2, claim.getAdmin().getId());
            preparedStatement.setLong(3, claim.getSup().getId());
            preparedStatement.setInt(4, claim.getSup().getStatusSup().getId());
            //Тут проблема
            preparedStatement.setTimestamp(5, Timestamp.valueOf(claim.getDataStartRent().atStartOfDay()));
            //
            preparedStatement.setInt(6, claim.getDurationRent());
            preparedStatement.setBigDecimal(7, claim.getPrice());
            preparedStatement.setLong(8, claim.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public Optional<Claim> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);

            var resultSet = preparedStatement.executeQuery();
            Claim claim = null;
            if (resultSet.next()) {
                claim = buildClaim(resultSet);
            }

            return Optional.ofNullable(claim);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public List<Claim> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Claim> claims = new ArrayList<>();
            while (resultSet.next()) {
                claims.add(buildClaim(resultSet));
            }
            return claims;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    private Claim buildClaim(ResultSet resultSet) throws SQLException {
        var userTypeRole = Role.builder()
                .id(resultSet.getInt(R_ID))
                .roleName(resultSet.getString(R_NAME))
                .build();
        var userType = UserType.builder()
                .id(resultSet.getLong(USER_TYPE_ID))
                .firstName(resultSet.getString(UT_FIRST_NAME))
                .lastName(resultSet.getString(UT_LAST_NAME))
                .login(resultSet.getString(UT_LOGIN))
                .password(resultSet.getString(UT_PASSWORD))
                .number(resultSet.getString(UT_NUMBER))
                .role(userTypeRole)
                .build();
        var statusSup = StatusSup.builder()
                .id(resultSet.getInt(SS_ID))
                .status(resultSet.getString(SS_STATUS))
                .build();

        var sup = Sup.builder()
                .id(resultSet.getLong(SUP_ID))
                .model(resultSet.getString(SUP_MODEL))
                .statusSup(statusSup)
                .build();

        var statusClaim = StatusClaim.builder()
                .id(resultSet.getInt(STATUS_CLAIM_ID))
                .status(resultSet.getString(STATUS_CLAIM))
                .build();

        return Claim.builder()
                .id(resultSet.getLong(CLAIM_ID))
                .client(userType)
                .admin(userType)
                .sup(sup)
                .statusClaim(statusClaim)
                .dataStartRent(resultSet.getTimestamp(DATA_START_RENT).toLocalDateTime().toLocalDate())
                .durationRent(resultSet.getInt(PRICE))
                .build();
    }
}

