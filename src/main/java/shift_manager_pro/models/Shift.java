package shift_manager_pro.models;

import java.time.LocalDateTime;

public class Shift {
    private Long id = null;
    private Long location_id = null;
    private Long user_id = null;
    private LocalDateTime startTime = null;
    private LocalDateTime endTime = null;
    private int breakTime = 0;
    private String info = null;

    // Status can only be 'ACCEPTED', 'REJECTED', 'PENDING' or 'UNALLOCATED'
    private String status = "";



    public Shift() {
    }


    public Shift(Long location_id, Long user_id, LocalDateTime startTime, LocalDateTime endTime, int breakTime,
            String info, String status) {
        this.location_id = location_id;
        this.user_id = user_id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.breakTime = breakTime;
        this.info = info;
        this.status = status;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public int getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(int breakTime) {
        this.breakTime = breakTime;
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
        return "Shift [info=" + info + ", breakTime=" + breakTime + ", user_id=" + user_id + ", endTime="
                + endTime + ", id=" + id + ", location_id=" + location_id + ", startTime="
                + startTime + "]";
    }
}
