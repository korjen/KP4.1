package by.bsuir.converter;

import by.bsuir.dto.SalaryDTO;
import by.bsuir.model.Salary;

public class SalaryConverter implements TwoWayConverter<Salary, SalaryDTO> {

    @Override
    public SalaryDTO convert(Salary salary) {
        if (salary != null) return new SalaryDTO(salary.getIdSalary(), salary.getSum(),salary.getTax(),salary.getWorkingDays(),salary.getVacationDays(),salary.isPrivilege(),salary.getCalculationMonth(),salary.getMonthDays(),salary.getPayDate());
        else return null;
    }

    @Override
    public Salary convertBack(SalaryDTO target) {
        Salary salary = new Salary(target.getSum(),target.getTax(),target.getWorkingDays(),target.getVacationDays(),target.isPrivilege(),target.getCalculationMonth(),target.getMonthDays(),target.getPayDate());
        salary.setIdSalary(target.getIdSalary());
        return salary;
    }
}
