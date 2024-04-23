package com.viktoria.rentalSup.dao.impl;

import com.viktoria.rentalSup.dao.Dao;
import com.viktoria.rentalSup.dataSource.ConnectionManager;
import com.viktoria.rentalSup.entity.StatusSup;
import com.viktoria.rentalSup.entity.Sup;
import com.viktoria.rentalSup.exception.DaoException;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.*;

@NoArgsConstructor(access = PRIVATE)

public class SupDao implements Dao<Sup, Long> {

    private static final String SUP_ID = "id";
    private static final String MODEL = "model";

    private static final String SS_ID = "id";
    private static final String SS_STATUS = "status";


    private static final SupDao INSTANCE = new SupDao();

    public static SupDao getInstance() {
        return INSTANCE;
    }

    private final StatusSupDao statusSupDao = StatusSupDao.getInstance();


    private static final String DELETE_SQL = """
            DELETE FROM sup
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO sup(model, id_status_sup)
            VALUES (?, ?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE sup
            SET model = ?,
            id_status_sup = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT sup.id,
            model,
            ss.status
            FROM sup
            JOIN public.status_sup ss
            on sup.id_status_sup = ss.id
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE sup.id = ?
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
    public Sup save(Sup sup) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, sup.getModel());
            preparedStatement.setInt(2, sup.getStatusSup().getId());

            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                sup.setId(generatedKeys.getLong(SUP_ID));
            }
            return sup;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public void update(Sup sup) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, sup.getModel());
            preparedStatement.setInt(2, sup.getStatusSup().getId());
            preparedStatement.setLong(3, sup.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }


    @Override
    public Optional<Sup> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);

            var resultSet = preparedStatement.executeQuery();
            Sup sup = null;
            if (resultSet.next()) {
                sup = buildSup(resultSet);
            }

            return Optional.ofNullable(sup);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public List<Sup> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Sup> sups = new ArrayList<>();
            while (resultSet.next()) {
                sups.add(buildSup(resultSet));
            }
            return sups;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    private Sup buildSup(ResultSet resultSet) throws SQLException {
        var statusSup = StatusSup.builder()
                .id(resultSet.getInt(SS_ID))
                .status(resultSet.getString(SS_STATUS))
                .build();
        return Sup.builder()
                .id(resultSet.getLong(SUP_ID))
                .model(resultSet.getString(MODEL))
                .statusSup(statusSup)
                .build();
    }
}
