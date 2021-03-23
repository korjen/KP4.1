package by.bsuir.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "workingTime")
public class WorkingTime implements Serializable {
    private static final long serialVersionUID = 6L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTimetable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idWorker")
    private Worker worker;

    @Column
    private String startTime;
    @Column
    private String endTime;
    @Column
    private float workingHours;
    @Column
    private boolean attendance;
    @Column
    private String reason;

    public WorkingTime() {
    }

    public WorkingTime(Worker worker, String startTime, String endTime, float workingHours, boolean attendance, String reason) {
        this.worker = worker;
        this.startTime = startTime;
        this.endTime = endTime;
        this.workingHours = workingHours;
        this.attendance = attendance;
        this.reason = reason;
    }

    public WorkingTime(String startTime, String endTime, float workingHours, boolean attendance, String reason) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.workingHours = workingHours;
        this.attendance = attendance;
        this.reason = reason;
    }

    public int getIdTimetable() {
        return idTimetable;
    }

    public void setIdTimetable(int idTimetable) {
        this.idTimetable = idTimetable;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public float getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(float workingHours) {
        this.workingHours = workingHours;
    }

    public boolean isAttendance() {
        return attendance;
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
