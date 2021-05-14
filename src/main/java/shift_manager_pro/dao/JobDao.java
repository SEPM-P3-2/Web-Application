package shift_manager_pro.dao;

import shift_manager_pro.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class JobDao {

  public static JobDao INSTANCE = new JobDao();

  private static String SELECT_ALL = "SELECT * FROM jobs";
  // private static String SELECT_BY_ID = "SELECT * FROM locations WHERE id=?";
  private static final String SELECT_BY_ID =
          "SELECT * FROM jobs WHERE id = ?";

  private JobDao() {}

  public List<Job> getAll() throws SQLException {
    Connection connection = DBUtils.getConnection();
    PreparedStatement stm = connection.prepareStatement(SELECT_ALL);
    ResultSet rs = stm.executeQuery();
    List<Job> jobs = new ArrayList<>();
    while (rs.next()) {
      jobs.add(mapJob(rs));
    }
    return jobs;
  }
  public Job get(Long id) throws SQLException {
    Connection connection = DBUtils.getConnection();
    PreparedStatement stm = connection.prepareStatement(SELECT_BY_ID);
    stm.setLong(1, id);
    ResultSet rs = stm.executeQuery();
    if (rs.next()) {
     Job job = mapJob(rs);
      return job;
    }
    connection.close();
    throw new SQLException("No location with id = " + id);
  }
  private Job mapJob(ResultSet rs) throws SQLException {
    Job job = new Job();
    job.setName(rs.getString(2));
    job.setId(rs.getLong(1));
    return job;
  }


}
