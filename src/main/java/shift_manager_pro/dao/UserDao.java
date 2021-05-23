package shift_manager_pro.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import shift_manager_pro.models.Role;
import shift_manager_pro.models.Shift;
import shift_manager_pro.models.User;

public class UserDao {

  private static final String SELECT_PWD_BY_EMAIL =
    "SELECT password FROM users WHERE email = ?";
  private static final String SELECT_BY_EMAIL =
    "SELECT * FROM users WHERE email = ?";
  private static final String SELECT_BY_ID = "SELECT * FROM users WHERE id = ?";
  private static final String SELECT_ALL = "SELECT * FROM users";

  private static final String INSERT =
    "INSERT INTO users(email, name, job_id, password, role, preferred_name, home_address) VALUES(?,?,?,?,?,?,?)";
  private static final String UPDATE =
<<<<<<< HEAD
    "UPDATE shifts SET email = ?, name = ?, job_id = ?, password = ?, role = ? WHERE id = ?";
  private static String DELETE = "DELETE FROM users WHERE id=?";
=======
    "UPDATE shifts SET email = ?, name = ?, job_id = ?, password = ?, role = ?, preferred_name = ?, home_address = ? WHERE id = ?";
>>>>>>> origin/Minh
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
  if (user.getId() != null) {
    stm.setLong(1, user.getId());
  } else {
    stm.setString(1, null);
  }
  stm.setString(2, user.getEmail());
  stm.setString(3, user.getName());
  stm.setLong(4, user.getJob_id());
  stm.setString(5, user.getPassword());
  stm.setString(6, String.valueOf(user.getRole()));
  stm.setString(7, user.getPreferred_name());
  stm.setString(8, user.getHome_address());
  return stm.executeUpdate();
}
<<<<<<< HEAD
  public int delete(User user) throws SQLException {
    Connection connection = DBUtils.getConnection();
    PreparedStatement stm = connection.prepareStatement(DELETE);
    stm.setLong(1, user.getId());
    return stm.executeUpdate();
  }}
=======
}
>>>>>>> origin/Minh
