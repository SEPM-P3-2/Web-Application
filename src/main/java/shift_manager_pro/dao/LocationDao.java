package shift_manager_pro.dao;

import shift_manager_pro.models.Location;
import shift_manager_pro.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class LocationDao {

  public static LocationDao INSTANCE = new LocationDao();

  private static String SELECT_ALL = "SELECT * FROM locations";
  // private static String SELECT_BY_ID = "SELECT * FROM locations WHERE id=?";
  private static final String SELECT_BY_ID =
          "SELECT * FROM locations WHERE loc_id = ?";

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
  public Location get(Long id) throws SQLException {
    Connection connection = DBUtils.getConnection();
    PreparedStatement stm = connection.prepareStatement(SELECT_BY_ID);
    stm.setLong(1, id);
    ResultSet rs = stm.executeQuery();
    if (rs.next()) {
     Location location = mapLocation(rs);
      return location;
    }
    connection.close();
    throw new SQLException("No location with id = " + id);
  }
  private Location mapLocation(ResultSet rs) throws SQLException {
    Location location = new Location();
    location.setTelephone(rs.getInt(4));
    location.setName(rs.getString(3));
    location.setAddress(rs.getString(2));
    location.setLoc_id(rs.getLong(1));
    return location;
  }


}
