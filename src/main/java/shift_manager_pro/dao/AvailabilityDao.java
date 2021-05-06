package shift_manager_pro.dao;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import shift_manager_pro.models.Availability;
import shift_manager_pro.models.Shift;

public class AvailabilityDao {

    private static final String SELECT_BY_USER_ID =
            "SELECT * FROM availabilities WHERE user_id = ?";
    private static final String SELECT_BY_ID =
            "SELECT * FROM availabilities WHERE id = ?";
    private static final String INSERT =
            "INSERT INTO availabilities(user_id, day, startTime, endTime) VALUES(?,?,?,?)";
    private static final String UPDATE =
            "UPDATE availabilities SET user_id = ?, day = ?, startTime = ?, endTime = ?, WHERE id = ?";

    public static AvailabilityDao INSTANCE = new AvailabilityDao();

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "HH:mm:ss"
    );

    private AvailabilityDao() {}

    public Availability getById(long id) throws SQLException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement stm = connection.prepareStatement(SELECT_BY_ID);
        stm.setLong(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            Availability a = mapAvailability(rs);
            return a;
        }
        connection.close();
        throw new SQLException("No Availability with id = " + id);
    }

    public List<Availability> getByUserID(long userID) throws SQLException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement stm = connection.prepareStatement(SELECT_BY_USER_ID);
        stm.setLong(1, userID);
        ResultSet rs = stm.executeQuery();
        List<Availability> availabilities = new ArrayList<Availability>();

        while (rs.next()) {
            availabilities.add(mapAvailability(rs));
        }
        return availabilities;
    }

    public Availability create(Availability availability) throws SQLException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement stm = connection.prepareStatement(
                INSERT,
                Statement.RETURN_GENERATED_KEYS
        );
        stm.setLong(1, availability.getUserID());
        stm.setString(2, availability.getDay().toString());
        stm.setString(3, availability.getStartTime().toString());
        stm.setString(4, availability.getEndTime().toString());
        stm.executeUpdate();
        ResultSet generatedKeys = stm.getGeneratedKeys();
        if (generatedKeys.next()) {
            availability.setID(generatedKeys.getLong(1));
        } else {
            connection.close();
            throw new SQLException("Creating availability failed, no ID obtained.");
        }
        connection.close();
        return availability;
    }

    public int update(Availability availability) throws SQLException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement stm = connection.prepareStatement(UPDATE);
        stm.setLong(1, availability.getUserID());
        stm.setString(2, availability.getDay().toString());
        stm.setString(3, availability.getStartTime().toString());
        stm.setString(4, availability.getEndTime().toString());
        stm.setLong(5, availability.getID());
        return stm.executeUpdate();
    }

    private Availability mapAvailability(ResultSet rs) throws SQLException {
        Availability availability = new Availability();
        availability.setID(rs.getLong(1));
        availability.setUserID(rs.getLong(2));
        availability.setDay(DayOfWeek.valueOf(rs.getString(3)));
        availability.setStartTime(LocalTime.parse(rs.getString(4), formatter));
        availability.setEndTime(LocalTime.parse(rs.getString(5), formatter));
        return availability;
    }
}