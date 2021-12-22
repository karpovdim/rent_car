package dao;

import entity.BaseEntity;
import exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pool.ConnectionManager;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends BaseEntity> implements Dao<T> {
    private final Class<T> type;
    private static final Logger LOGGER = LogManager.getLogger();


    @Override
    public Optional<T> findById(int id) {
        try (final Connection connection = ConnectionManager.getConnection();
             final PreparedStatement st = connection.prepareStatement(getSqlSelectById())) {
            st.setInt(1, id);
            return findById(st);
        } catch (SQLException ex) {
            LOGGER.error("Error finding {} with id=[{}]", type.getSimpleName(), id, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public List<T> findAll() {
        final Connection connection = ConnectionManager.getConnection();
        try (final PreparedStatement st = connection.prepareStatement(getSqlSelectAll())) {
            try (final ResultSet rs = st.executeQuery()) {
                return findAll(rs);
            }
        } catch (SQLException ex) {
            LOGGER.error("Error finding all {}s", type.getSimpleName(), ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public T create(T t) {
        final Connection connection = ConnectionManager.getConnection();
        try (final PreparedStatement st = connection.prepareStatement(getSqlInsert(), Statement.RETURN_GENERATED_KEYS)) {
            return create(st, t);
        } catch (SQLException ex) {
            LOGGER.error("Error while creating {}=[{}]", type.getSimpleName(), t, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public void delete(T t) {
        final Connection connection = ConnectionManager.getConnection();
        try (final PreparedStatement st = connection.prepareStatement(getSqlDelete())) {
            st.setInt(1, t.getId());
            final int count = st.executeUpdate();
            if (count > 1) {
                LOGGER.error("Error while deleting {} [{}]", type.getSimpleName(), t);
                throw new DaoException();
            }
        } catch (SQLException ex) {
            LOGGER.error("Error while deleting {} with id=[{}]", type.getSimpleName(), t.getId(), ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public T update(T t) {
        final Connection connection = ConnectionManager.getConnection();
        try (final PreparedStatement st = connection.prepareStatement(getSqlUpdate())) {
            return update(st, t);
        } catch (SQLException ex) {
            LOGGER.error("Error while updating {}=[{}]", type.getSimpleName(), t, ex);
            throw new DaoException(ex);
        }
    }

    protected abstract String getSqlSelectById();

    protected abstract String getSqlSelectAll();

    protected abstract String getSqlInsert();

    protected abstract String getSqlDelete();

    protected abstract String getSqlUpdate();

    protected abstract Optional<T> findById(PreparedStatement st) throws SQLException;

    protected abstract List<T> findAll(ResultSet rs) throws SQLException;

    protected abstract T update(PreparedStatement st, T t) throws SQLException;

    protected abstract T create(PreparedStatement st, T t) throws SQLException;

    public AbstractDao(Class<T> type) {
        this.type = type;
    }

}
