package by.bsuir.converter;

import by.bsuir.dto.WorkingTimeDTO;
import by.bsuir.model.WorkingTime;

public class WorkingTimeConverter implements TwoWayConverter<WorkingTime, WorkingTimeDTO>{
    @Override
    public WorkingTimeDTO convert(WorkingTime source) {
        if (source != null) return new WorkingTimeDTO(source.getIdTimetable(),source.getStartTime(),source.getEndTime(),source.getWorkingHours(),source.isAttendance(),source.getReason());
        else return null;
    }

    @Override
    public WorkingTime convertBack(WorkingTimeDTO target) {
        WorkingTime workingTime = new WorkingTime(target.getStartTime(),target.getEndTime(),target.getWorkingHours(),target.isAttendance(),target.getReason());
        workingTime.setIdTimetable(target.getIdTimetable());
        return workingTime;
    }
}
