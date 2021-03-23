package by.bsuir.converter;

import by.bsuir.dto.WorkingTimeDTO;
import by.bsuir.model.WorkingTime;

import java.util.ArrayList;
import java.util.List;

public class WorkingTimeListConverter implements ListTwoWayConverter<WorkingTime, WorkingTimeDTO>{
    private WorkingTimeConverter workingTimeConverter;

    @Override
    public List<WorkingTimeDTO> convertList(List<WorkingTime> source) {
        List<WorkingTimeDTO> workingTimeDTOs = new ArrayList<>();
        for (WorkingTime workingTime : source) {
            workingTimeDTOs.add(workingTimeConverter.convert(workingTime));
        }
        return workingTimeDTOs;
    }

    @Override
    public List<WorkingTime> convertBackList(List<WorkingTimeDTO> target) {
        List<WorkingTime> timeTable = new ArrayList<>();
        for(WorkingTimeDTO workingTimeDTO : target) {
            timeTable.add(workingTimeConverter.convertBack(workingTimeDTO));
        }
        return timeTable;
    }

    public void setWorkingTimeConverter(WorkingTimeConverter workingTimeConverter) {
        this.workingTimeConverter = workingTimeConverter;
    }
}
