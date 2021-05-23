package shift_manager_pro.dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import shift_manager_pro.models.Shift;

public class ShiftDao {

  private static final String SELECT_BY_USER_ID =
    "SELECT * FROM shifts WHERE user_id = ?";
  private static final String SELECT_BY_ID =
    "SELECT * FROM shifts WHERE id = ?";
  private static final String INSERT =
    "INSERT INTO shifts(location_id, user_id, startTime, endTime, breakTime, info, status) VALUES(?,?,?,?,?,?,?)";
  private static final String INSERT_UNALLOCATED =
    "INSERT INTO shifts(location_id, startTime, endTime, breakTime, info, status) VALUES(?,?,?,?,?,?)";
  private static final String SELECT_FROM_NOW =
    "SELECT * FROM shifts WHERE startTime >= CURRENT_TIMESTAMP";
  private static final String UPDATE =
    "UPDATE shifts SET location_id = ?, user_id = ?, startTime = ?, endTime = ?, breakTime = ?, info = ?, status = ? WHERE id = ?";
  private static String DELETE = "DELETE FROM shifts WHERE id=?";

  public static ShiftDao INSTANCE = new ShiftDao();

  public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
    "yyyy-MM-dd HH:mm:ss"
  );

  private ShiftDao() {}

  public Shift getById(long id) throws SQLException {
    Connection connection = DBUtils.getConnection();
    PreparedStatement stm = connection.prepareStatement(SELECT_BY_ID);
    stm.setLong(1, id);
    ResultSet rs = stm.executeQuery();
    if (rs.next()) {
      Shift s = mapShift(rs);
      return s;
    }
    connection.close();
    throw new SQLException("No Shift with id = " + id);
  }

  public List<Shift> getFromNow() throws SQLException {
    Connection connection = DBUtils.getConnection();
    PreparedStatement stm = connection.prepareStatement(SELECT_FROM_NOW);
    ResultSet rs = stm.executeQuery();
    List<Shift> shifts = new ArrayList<>();
    while (rs.next()) {
      shifts.add(mapShift(rs));
    }
    return shifts;
  }

  public List<Shift> getByUserId(long user_id) throws SQLException {
    Connection connection = DBUtils.getConnection();
    PreparedStatement stm = connection.prepareStatement(SELECT_BY_USER_ID);
    stm.setLong(1, user_id);
    ResultSet rs = stm.executeQuery();
    List<Shift> shifts = new ArrayList<Shift>();

    while (rs.next()) {
      shifts.add(mapShift(rs));
    }
    return shifts;
  }

  public int delete(Shift shift) throws SQLException {
    Connection connection = DBUtils.getConnection();
    PreparedStatement stm = connection.prepareStatement(DELETE);
    stm.setLong(1, shift.getId());
    return stm.executeUpdate();
  }

  public Shift create(Shift shift) throws SQLException {
    Connection connection = DBUtils.getConnection();
    PreparedStatement stm = connection.prepareStatement(
      INSERT,
      Statement.RETURN_GENERATED_KEYS
    );
    stm.setLong(1, shift.getLocation_id());
    stm.setLong(2, shift.getUser_id());
    stm.setString(3, shift.getStartTime().toString());
    stm.setString(4, shift.getEndTime().toString());
    stm.setInt(5, shift.getBreakTime());
    stm.setString(6, shift.getInfo());
    stm.setString(7, shift.getStatus());
    stm.executeUpdate();
    ResultSet generatedKeys = stm.getGeneratedKeys();
    if (generatedKeys.next()) {
      shift.setId(generatedKeys.getLong(1));
    } else {
      connection.close();
      throw new SQLException("Creating shift failed, no ID obtained.");
    }
    connection.close();
    return shift;
  }

  public Shift createUnallocated(Shift shift) throws SQLException {
    Connection connection = DBUtils.getConnection();
    PreparedStatement stm = connection.prepareStatement(
      INSERT_UNALLOCATED,
      Statement.RETURN_GENERATED_KEYS
    );
    stm.setLong(1, shift.getLocation_id());
    stm.setString(2, shift.getStartTime().toString());
    stm.setString(3, shift.getEndTime().toString());
    stm.setInt(4, shift.getBreakTime());
    stm.setString(5, shift.getInfo());
    stm.setString(6, shift.getStatus());
    stm.executeUpdate();
    ResultSet generatedKeys = stm.getGeneratedKeys();
    if (generatedKeys.next()) {
      shift.setId(generatedKeys.getLong(1));
    } else {
      connection.close();
      throw new SQLException("Creating shift failed, no ID obtained.");
    }
    connection.close();
    return shift;
  }

  private Shift mapShift(ResultSet rs) throws SQLException {
    // location_id, job_id, user_id, startTime, endTime, breakTime, description
    Shift shift = new Shift();
    shift.setId(rs.getLong(1));
    shift.setLocation_id(rs.getLong(2));
    shift.setUser_id(rs.getLong(3));
    shift.setStartTime(LocalDateTime.parse(rs.getString(4), formatter));
    shift.setEndTime(LocalDateTime.parse(rs.getString(5), formatter));
    shift.setBreakTime(rs.getInt(6));
    shift.setInfo(rs.getString(7));
    shift.setStatus(rs.getString(8));
    return shift;
  }

  public int updateShift(Shift shift) throws SQLException {
    Connection connection = DBUtils.getConnection();
    PreparedStatement stm = connection.prepareStatement(UPDATE);
    if (shift.getUser_id() != null) {
      stm.setLong(2, shift.getUser_id());
    } else {
      stm.setString(2, null);
    }
    stm.setLong(1, shift.getLocation_id());
    stm.setString(3, String.valueOf(shift.getStartTime()));
    stm.setString(4, String.valueOf(shift.getEndTime()));
    stm.setInt(5, shift.getBreakTime());
    stm.setString(6, shift.getInfo());
    stm.setString(7, shift.getStatus());
    stm.setLong(8, shift.getId());
    return stm.executeUpdate();
  }
}
