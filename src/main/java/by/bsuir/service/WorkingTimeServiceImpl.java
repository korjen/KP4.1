package by.bsuir.service;

import by.bsuir.dao.interfaces.WorkingTimeDAO;
import by.bsuir.model.WorkingTime;
import by.bsuir.service.interfaces.WorkingTimeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class WorkingTimeServiceImpl implements WorkingTimeService {
    private WorkingTimeDAO workingTimeDAO;

    @Override
    public List<WorkingTime> getAll() {
        return workingTimeDAO.getAll();
    }

    @Override
    public void update(WorkingTime workingTime) {
        calcWorkingHours(workingTime);
        workingTimeDAO.update(workingTime);
    }

    @Override
    public void save(WorkingTime workingTime) {
        calcWorkingHours(workingTime);
        workingTimeDAO.save(workingTime);
    }

    @Override
    public void delete(int id) {
        WorkingTime workingTime = workingTimeDAO.findById(id);
        if(workingTime!=null) workingTimeDAO.delete(workingTime);
    }

    @Override
    public WorkingTime findById(int id) {
        return workingTimeDAO.findById(id);
    }

    @Override
    public List<WorkingTime> findByWorker(int idWorker) {
        return workingTimeDAO.findByWorker(idWorker);
    }

    public void setWorkingTimeDAO(WorkingTimeDAO workingTimeDAO) {
        this.workingTimeDAO = workingTimeDAO;
    }

    public void calcWorkingHours(WorkingTime workingTime) {
        if (workingTime.isAttendance()) {
            if (workingTime.getReason()!=null) {
                if (workingTime.getReason().equals("отпуск") || workingTime.getReason().equals("отпуск за свой счет") || workingTime.getReason().equals("больничный") || workingTime.getReason().equals("выходной/праздник")) {
                    workingTime.setAttendance(false);
                    workingTime.setWorkingHours(0);
                }
            }
            else {
                if (workingTime.getStartTime().equals(workingTime.getEndTime())) {
                    workingTime.setWorkingHours(0);
                    return;
                }
                if (workingTime.getStartTime() != null && workingTime.getEndTime() != null) {
                    SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
                    Date startTime = null;
                    Date endTime = null;
                    try {
                        startTime = format.parse(workingTime.getStartTime());
                        endTime = format.parse(workingTime.getEndTime());
                    } catch (ParseException e) {
                    }

                    float diff = endTime.getTime() - startTime.getTime();
                    float diffHours = diff / (60 * 60 * 1000);
                    DecimalFormat df = new DecimalFormat("0.00");
                    diffHours = Float.parseFloat(df.format(diffHours));
                    workingTime.setWorkingHours(diffHours);
                } else workingTime.setWorkingHours(0);
            }
        } else workingTime.setWorkingHours(0);
        if (!workingTime.isAttendance()) {
            String[] start = workingTime.getStartTime().split(" ");
            String[] end = workingTime.getEndTime().split(" ");
            workingTime.setStartTime(start[0]+" 00:00:00");
            workingTime.setEndTime(end[0]+" 00:00:00");
        }
    }

    @Override
    public List<WorkingTime> getAllForPaginationByWorker(int id, int currentPage, int numberOfRecordsPerPage) {
        return workingTimeDAO.getAllForPaginationByWorker(id,currentPage,numberOfRecordsPerPage);
    }
}
