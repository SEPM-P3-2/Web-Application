package shift_manager_pro.models;

import java.time.LocalDateTime;


public class Shift {
    private Long id = null;
    private Long location_id = null;
    private Long job_id = null;
    private Long emp_id = null;
    private LocalDateTime startTime = null;
    private int duration = 0;
    private LocalDateTime endTime = null;
    private String description = null;

    public Shift() {

    }

    public Shift(Long id, LocalDateTime startTime, LocalDateTime endTime, Long location_id, Long emp_id, int duration) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.emp_id = emp_id;
        this.location_id = location_id;

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

    public Long getJob_id() {
        return job_id;
    }

    public void setJob_id(Long job_id) {
        this.job_id = job_id;
    }

    public Long getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Long emp_id) {
        this.emp_id = emp_id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Shift [description=" + description + ", duration=" + duration + ", emp_id=" + emp_id + ", endTime="
                + endTime + ", id=" + id + ", job_id=" + job_id + ", location_id=" + location_id + ", startTime="
                + startTime + "]";
    }
}
