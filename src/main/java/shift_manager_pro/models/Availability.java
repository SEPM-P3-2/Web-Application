package shift_manager_pro.models;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Availability {
    private long id;
    private long userID;
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;

    public Availability() {}

    public Availability(long id, long userID, DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.userID = userID;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getID() { return id; }
    public void setID(long id) { this.id = id; }

    public long getUserID() { return userID; }
    public void setUserID(long userID) { this.userID = userID; }

    public DayOfWeek getDay() { return day; }
    public void setDay(DayOfWeek day) { this.day = day; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
}
