package by.bsuir.dto;

import by.bsuir.model.WorkingTime;

import java.util.List;

public class WorkerTimeTableDTO {
    List<WorkingTime> timeTable;

    public WorkerTimeTableDTO() {
    }

    public List<WorkingTime> getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(List<WorkingTime> timeTable) {
        this.timeTable = timeTable;
    }
}
