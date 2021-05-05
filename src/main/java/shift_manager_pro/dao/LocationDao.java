package shift_manager_pro.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class LocationDao {

  public static LocationDao INSTANCE = new LocationDao();

  private static String SELECT_ALL = "SELECT * FROM locations";
  // private static String SELECT_BY_ID = "SELECT * FROM locations WHERE id=?";

  private LocationDao() {}

  public List<String> getAll() throws SQLException {
    Connection connection = DBUtils.getConnection();
    Statement stm = connection.createStatement();
    ResultSet rs = stm.executeQuery(SELECT_ALL);
    List<String> locations = new ArrayList<>();
    while (rs.next()) {
      locations.add(rs.getString(2));
    }
    connection.close();
    return locations;
  }
}
