package shift_manager_pro.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Shift {
    private Long id = null;
    private Long location_id = null;
    private Long user_id = null;
    private LocalDateTime startTime = null;
    private LocalDateTime endTime = null;
    private int duration = 0;
    private String info = null;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public Shift() {
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.user_id = user_id;
        this.location_id = location_id;
        this.info = info;

    }

    public Shift(String user_id, String startTime, String endTime, String loc_id, String duration, String info) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Long location_id) {
        this.location_id = location_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info){
        this.info = info;
    }

    @Override
    public String toString() {
        return "Shift [info=" + info + ", duration=" + duration + ", user_id=" + user_id + ", endTime="
                + endTime + ", id=" + id + ", location_id=" + location_id + ", startTime="
                + startTime + "]";
    }
}
