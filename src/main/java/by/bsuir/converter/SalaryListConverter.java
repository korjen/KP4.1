package by.bsuir.converter;

import by.bsuir.dto.SalaryDTO;
import by.bsuir.model.Salary;

import java.util.ArrayList;
import java.util.List;

public class SalaryListConverter implements ListTwoWayConverter<Salary, SalaryDTO> {
    private SalaryConverter salaryConverter;

    @Override
    public List<SalaryDTO> convertList(List<Salary> source) {
        List<SalaryDTO> salaryDTOs = new ArrayList<>();
        for (Salary salary : source) {
            salaryDTOs.add(salaryConverter.convert(salary));
        }
        return salaryDTOs;
    }

    @Override
    public List<Salary> convertBackList(List<SalaryDTO> target) {
        List<Salary> salaries = new ArrayList<>();
        for(SalaryDTO salaryDTO : target) {
            salaries.add(salaryConverter.convertBack(salaryDTO));
        }
        return salaries;
    }

    public void setSalaryConverter(SalaryConverter salaryConverter) {
        this.salaryConverter = salaryConverter;
    }
}
