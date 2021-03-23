package by.bsuir.utility;

public class Deduction {
    private float taxPercent;
    private float pensionInsurancePercent;
    private int month;
    private int workingDays;

    public Deduction() {
        taxPercent=DeductionStatic.getTaxPercent()*100;
        pensionInsurancePercent=DeductionStatic.getPensionInsurancePercent()*100;
    }

    public Deduction(float taxPercent, float pensionInsurancePercent, float socialInsurancePercent, int month, int workingDays) {
        this.taxPercent = taxPercent;
        this.pensionInsurancePercent = pensionInsurancePercent;
        this.month = month;
        this.workingDays = workingDays;
    }

    public float getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(float taxPercent) {
        this.taxPercent = taxPercent;
    }

    public float getPensionInsurancePercent() {
        return pensionInsurancePercent;
    }

    public void setPensionInsurancePercent(float pensionInsurancePercent) {
        this.pensionInsurancePercent = pensionInsurancePercent;
    }

    public int getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
