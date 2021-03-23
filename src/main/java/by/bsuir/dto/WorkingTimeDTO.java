package by.bsuir.dto;

import java.io.Serializable;

public class WorkingTimeDTO implements Serializable {
    private int idTimetable;
    private String startTime;
    private String endTime;
    private float workingHours;
    private boolean attendance;
    private String reason;

    public WorkingTimeDTO() {
    }

    public WorkingTimeDTO(int idTimetable, String startTime, String endTime, float workingHours, boolean attendance, String reason) {
        this.idTimetable = idTimetable;
        this.startTime = startTime;
        this.endTime = endTime;
        this.workingHours = workingHours;
        this.attendance = attendance;
        this.reason = reason;
    }

    public int getIdTimetable() {
        return idTimetable;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public float getWorkingHours() {
        return workingHours;
    }

    public boolean isAttendance() {
        return attendance;
    }

    public void setIdTimetable(int idTimetable) {
        this.idTimetable = idTimetable;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setWorkingHours(float workingHours) {
        this.workingHours = workingHours;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
