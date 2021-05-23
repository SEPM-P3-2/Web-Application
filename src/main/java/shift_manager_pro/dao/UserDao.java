package shift_manager_pro.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import shift_manager_pro.models.Role;
import shift_manager_pro.models.User;

public class UserDao {

  private static final String SELECT_PWD_BY_EMAIL =
    "SELECT password FROM users WHERE email = ?";
  private static final String SELECT_BY_EMAIL =
    "SELECT * FROM users WHERE email = ?";
  private static final String SELECT_BY_ID = "SELECT * FROM users WHERE id = ?";

  private static final String SELECT_BY_ROLE = "SELECT * FROM users WHERE role = ?";

  private static final String SELECT_ALL = "SELECT * FROM users";

  private static final String INSERT =
    "INSERT INTO users(email, name, job_id, password, role, preferred_name, home_address, standard_working_hour, phone_number, current_working_hour) VALUES(?,?,?,?,?,?,?,?,?,?)";
  private static final String UPDATE =
    "UPDATE users SET email = ?, name = ?, job_id = ?, password = ?, role = ?, preferred_name = ?, home_address = ?, standard_working_hour = ?, phone_number = ?, current_working_hour =? WHERE id = ?";
  private static String DELETE = "DELETE FROM users WHERE id=?";
  private static final String UPDATE_WORKING_HOUR =
    "UPDATE users SET current_working_hour = ? WHERE id=?";

    


  public static UserDao INSTANCE = new UserDao();

  private UserDao() {}

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
      user.setEmail(rs.getString(2));
      user.setName(rs.getString(3));
      user.setJob_id(rs.getLong(4));
      user.setRole(Role.valueOf(rs.getString(6)));
      user.setPreferred_name(rs.getString(7));
      user.setHome_address(rs.getString(8));
      user.setStandard_working_hour(rs.getInt(9));
      user.setPhone_number(rs.getString(10));
      user.setCurrent_working_hour(rs.getDouble(11));
      user.setId(rs.getLong(1));
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
      User user = mapUser(rs);
      return user;
    }
    connection.close();
    throw new SQLException("No User with id = " + id);
  }

  public List<User> getAll() throws SQLException {
    Connection connection = DBUtils.getConnection();
    Statement stm = connection.createStatement();
    ResultSet rs = stm.executeQuery(SELECT_ALL);
    List<User> users = new ArrayList<>();
    while (rs.next()) {
      users.add(mapUser(rs));
    }
    return users;
  }

  public List<User> getManagers() throws SQLException {
    Connection connection = DBUtils.getConnection();
    PreparedStatement stm = connection.prepareStatement(SELECT_BY_ROLE);
    stm.setString(1, "MANAGER");
    ResultSet rs = stm.executeQuery();
    List<User> users = new ArrayList<>();
    while (rs.next()) {
      users.add(mapUser(rs));
    }
    return users;
  }

  public User create(User user) throws SQLException {
    Connection connection = DBUtils.getConnection();
    PreparedStatement stm = connection.prepareStatement(
      INSERT,
      Statement.RETURN_GENERATED_KEYS
    );
    stm.setString(1, user.getEmail());
    stm.setString(2, user.getName());
    stm.setLong(3, user.getJob_id());
    stm.setString(4, user.getPassword());
    stm.setString(5, String.valueOf(user.getRole()));
    stm.setString(6, user.getPreferred_name());
    stm.setString(7, user.getHome_address());
    stm.setInt(8, user.getStandard_working_hour());
    stm.setString(9,user.getPhone_number());
    stm.setDouble(10, user.getCurrent_working_hour());
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

  private User mapUser(ResultSet rs) throws SQLException {
    User user = new User();
    user.setCurrent_working_hour(rs.getDouble(11));
    user.setPhone_number(rs.getString(10));
    user.setStandard_working_hour(rs.getInt(9));
    user.setPreferred_name(rs.getString(7));
    user.setHome_address(rs.getString(8));
    user.setRole(Role.valueOf(rs.getString(6)));
    user.setJob_id(rs.getLong(4));
    user.setName(rs.getString(3));
    user.setEmail(rs.getString(2));
    user.setId(rs.getLong(1));

    return user;
  }

public int updateUser(User user) throws SQLException {
  Connection connection = DBUtils.getConnection();
  PreparedStatement stm = connection.prepareStatement(UPDATE);
  stm.setString(1, user.getEmail());
  stm.setString(2, user.getName());
  stm.setLong(3, user.getJob_id());
  stm.setString(4, user.getPassword());
  stm.setString(5, String.valueOf(user.getRole()));
  stm.setString(6, user.getPreferred_name());
  stm.setString(7, user.getHome_address());
  stm.setInt(8, user.getStandard_working_hour());
  stm.setString(9, user.getPhone_number());
  stm.setDouble(10, user.getCurrent_working_hour());
  stm.setLong(11,user.getId());
  return stm.executeUpdate();
}
public int updateWorkingHour(User user) throws SQLException{
  Connection connection = DBUtils.getConnection();
  PreparedStatement stm = connection.prepareStatement(UPDATE_WORKING_HOUR);
  stm.setDouble(1, user.getCurrent_working_hour());
  stm.setLong(2,user.getId());
  return stm.executeUpdate();
}
  public int delete(User user) throws SQLException {
    Connection connection = DBUtils.getConnection();
    PreparedStatement stm = connection.prepareStatement(DELETE);
    stm.setLong(1, user.getId());
    return stm.executeUpdate();
  }

}

