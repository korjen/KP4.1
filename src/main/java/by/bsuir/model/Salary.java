package by.bsuir.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "salary")
public class Salary implements Serializable {
    private static final long serialVersionUID = 3L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSalary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idWorker")
    private Worker worker;

    @Column
    private float sum;
    @Column
    private float tax;
    @Column
    private int workingDays;
    @Column
    private int vacationDays;
    @Column
    private boolean privilege;
    @Column
    private int calculationMonth;
    @Column
    private int monthDays;
    @Column
    private String payDate;

    public Salary() {
    }

    public Salary(Worker worker, float sum, float tax, int workingDays, int vacationDays, boolean privilege, int calculationMonth, int monthDays, String payDate) {
        this.worker = worker;
        this.sum = sum;
        this.tax = tax;
        this.workingDays = workingDays;
        this.vacationDays = vacationDays;
        this.privilege = privilege;
        this.calculationMonth = calculationMonth;
        this.monthDays = monthDays;
        this.payDate = payDate;
    }

    public Salary(float sum, float tax, int workingDays, int vacationDays, boolean privilege, int calculationMonth, int monthDays, String payDate) {
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

    public void setIdSalary(int idSalary) {
        this.idSalary = idSalary;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public boolean isPrivilege() {
        return privilege;
    }

    public void setPrivilege(boolean discount) {
        this.privilege = discount;
    }

    public String getPayDate() {
        return payDate;
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
