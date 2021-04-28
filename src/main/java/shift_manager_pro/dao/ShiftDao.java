package shift_manager_pro.dao;

import shift_manager_pro.models.Role;
import shift_manager_pro.models.Shift;
import shift_manager_pro.models.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ShiftDao {
    private static final String SELECT_BY_EMP_ID = "SELECT id, location_id, emp_id, startTime, endTime, duration, description FROM shifts WHERE emp_id = ?" ;
    private static final String SELECT_BY_ID = "SELECT id, location_id, emp_id, startTime, endTime, duration, description FROM shifts WHERE id = ?";
    private static final String INSERT = "INSERT INTO shifts(location_id, emp_id, startTime, endTime, duration, description) VALUES(?,?,?,?,?,?)";

    public static ShiftDao INSTANCE = new ShiftDao();

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private ShiftDao(){}

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


    public Shift getByEmpId(long emp_id) throws SQLException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement stm = connection.prepareStatement(SELECT_BY_EMP_ID);
        stm.setLong(1, emp_id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            Shift s = mapShift(rs);
            return s;
        }
        connection.close();
        throw new SQLException("No Shift with emp_id = " + emp_id);
    }

    public Shift create(Shift shift) throws SQLException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement stm = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        stm.setLong(1, shift.getLocation_id());
        stm.setLong(2, shift.getEmp_id());
        stm.setString(3, shift.getStartTime().toString());
        stm.setString(4, shift.getEndTime().toString());
        stm.setInt(5, shift.getDuration());
        stm.setString(6, shift.getDescription());
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
        // location_id, job_id, emp_id, startTime, endTime, duration, description
        Shift shift = new Shift();
        shift.setId(rs.getLong(1));
        shift.setLocation_id(rs.getLong(2));
        shift.setJob_id(rs.getLong(3));
        shift.setStartTime(LocalDateTime.parse(rs.getString(4), formatter));
        shift.setEndTime(LocalDateTime.parse(rs.getString(5), formatter));
        shift.setDuration(rs.getInt(6));
        shift.setDescription(rs.getString(7));
        return shift;
    }
}
