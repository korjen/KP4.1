package by.bsuir.dto;

import java.io.Serializable;

public class SalaryDTO implements Serializable {
    private int idSalary;
    private float sum;
    private float tax;
    private int workingDays;
    private int vacationDays;
    private boolean privilege;
    private int calculationMonth;
    private int monthDays;
    private String payDate;

    public SalaryDTO() {
    }

    public SalaryDTO(int idSalary, float sum, float tax, int workingDays, int vacationDays, boolean privilege, int calculationMonth, int monthDays, String payDate) {
        this.idSalary = idSalary;
        this.sum = sum;
        this.tax = tax;
        this.workingDays = workingDays;
        this.vacationDays = vacationDays;
        this.privilege = privilege;
        this.calculationMonth = calculationMonth;
        this.monthDays = monthDays;
        this.payDate = payDate;
    }

    public int getIdSalary() {
        return idSalary;
    }

    public float getSum() {
        return sum;
    }

    public float getTax() {
        return tax;
    }

    public boolean isPrivilege() {
        return privilege;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setIdSalary(int idSalary) {
        this.idSalary = idSalary;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public void setPrivilege(boolean privilege) {
        this.privilege = privilege;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public int getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }

    public int getCalculationMonth() {
        return calculationMonth;
    }

    public void setCalculationMonth(int calculationMonth) {
        this.calculationMonth = calculationMonth;
    }

    public int getMonthDays() {
        return monthDays;
    }

    public void setMonthDays(int monthDays) {
        this.monthDays = monthDays;
    }
}
