package dao;

import entity.RoleUser;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public  class UserDao extends AbstractDao<User> {


    public UserDao(Class<User> type) {
        super(type);
    }

    @Override
    protected Optional<User> findById(PreparedStatement st)  {
       try( final var resultSet = st.executeQuery();) {
           if (resultSet.next()) {
               return Optional.of(User.builder()
                       .id(resultSet.getInt("id"))
                       .firstName(resultSet.getString("first_name"))
                       .lastName(resultSet.getString("last_name"))
                       .emailLogin(resultSet.getString("email_login"))
                       .password(resultSet.getString("password"))
                       .roleUser(RoleUser.valueOf(resultSet.getString("role_user")))
                       .phoneUser(resultSet.getString("phone_user"))
                       .build());
           }
       }catch (SQLException e){
           throw new RuntimeException(e); //TODO CREATE CUSTOM EXCEPTION
       }
        return Optional.empty();
    }

    @Override
    protected String getSqlSelectById() {
        // TODO FIX SQL, CREATE CONSTANT;
        String sql = """
                SELECT *
                FROM rent_car.users
                WHERE id = ?
                """;
        return sql;
    }

    @Override
    protected String getSqlSelectAll() {
        return null;
    }

    @Override
    protected String getSqlInsert() {
        return null;
    }

    @Override
    protected String getSqlDelete() {
        return null;
    }

    @Override
    protected String getSqlUpdate() {
        return null;
    }

    @Override
    protected List<User> findAll(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    protected User update(PreparedStatement st, User user) throws SQLException {
        return null;
    }

    @Override
    protected User create(PreparedStatement st, User user) throws SQLException {
        return null;
    }
}
