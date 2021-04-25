package shift_manager_pro.dao;

import shift_manager_pro.models.Role;
import shift_manager_pro.models.User;

import java.sql.*;

public class UserDao {

    private static final String SELECT_PWD_BY_EMAIL = "SELECT password FROM users WHERE email = ?" ;
    private static final String SELECT_BY_EMAIL = "SELECT email, name, role, id FROM users WHERE email = ?" ;
    private static final String SELECT_BY_ID = "SELECT email, name, role, id FROM users WHERE id = ?";
    private static final String INSERT = "INSERT INTO users(email, name, password, role) VALUES(?,?,?,?)";
    public static UserDao INSTANCE = new UserDao();

    private UserDao(){}

    public String getUserPasswordHash(String email) throws SQLException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement stm = connection.prepareStatement(SELECT_PWD_BY_EMAIL);
        stm.setString(1, email);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return rs.getString(1);
        }
        connection.close();
        throw new SQLException("No User with email = " + email);
    }

    public User getByEmail(String email) throws SQLException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement stm = connection.prepareStatement(SELECT_BY_EMAIL);
        stm.setString(1, email);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            User user = new User();
            user.setEmail(rs.getString(1));
            user.setName(rs.getString(2));
            user.setRole(Role.valueOf(rs.getString(3)));
            user.setId(rs.getLong(4));
            return user;
        }
        connection.close();
        throw new SQLException("No User with email = " + email);
    }

    public User get(Long id) throws SQLException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement stm = connection.prepareStatement(SELECT_BY_ID);
        stm.setLong(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            User user = new User();
            user.setEmail(rs.getString(1));
            user.setName(rs.getString(2));
            user.setRole(Role.valueOf(rs.getString(3)));
            user.setId(rs.getLong(4));
            return user;
        }
        connection.close();
        throw new SQLException("No User with id = " + id);
    }
    public User create(User user) throws SQLException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement stm = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, user.getEmail());
        stm.setString(2, user.getName());
        stm.setString(3, user.getPassword());
        stm.setString(4, String.valueOf(user.getRole()));
        stm.executeUpdate();
        ResultSet generatedKeys = stm.getGeneratedKeys();
        if (generatedKeys.next()) {
            user.setId(generatedKeys.getLong(1));
        } else {
            connection.close();
            throw new SQLException("Creating user failed, no ID obtained.");
        }
        connection.close();
        return user;
    }
}
