package shift_manager_pro.dao;

import shift_manager_pro.models.Role;
import shift_manager_pro.models.Shift;
import shift_manager_pro.models.User;

import java.sql.*;
public class ShiftDao {

    // private static final String SELECT_PWD_BY_EMAIL = "SELECT password FROM users WHERE email = ?" ;
    // private static final String SELECT_BY_EMAIL = "SELECT email, name, role, id FROM users WHERE email = ?" ;
    private static final String SELECT_BY_ID = "SELECT * FROM shifts WHERE id = ?";
    public static ShiftDao INSTANCE = new ShiftDao();

    private ShiftDao() {}{}

    public Shift get(Long id) throws SQLException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement stm = connection.prepareStatement(SELECT_BY_ID);
        stm.setLong(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            Shift shift = new Shift();
            shift.setId(rs.getLong(1));

            return shift;
        }
        connection.close();
        throw new SQLException("No User with id = " + id);
    }

    private Shift mapShift(ResultSet rs) throws SQLException {
        Timestamp startTime = rs.getTimestamp(5);
        Timestamp endTime = rs.getTimestamp(6);
        Shift shift = new Shift(rs.getLong(2), rs.getLong(3), rs.getLong(4), startTime.toLocalDateTime(),endTime.toLocalDateTime(),rs.getInt(7));
        session.setSession_id(rs.getLong(1));
        session.setStatus(rs.getString(6));
        return session;
    }

}
