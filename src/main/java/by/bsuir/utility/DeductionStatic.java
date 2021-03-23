package by.bsuir.utility;

public class DeductionStatic {
    private static float taxPercent=0.13f;
    private static float pensionInsurancePercent=0.01f;
    private static int month;
    private static int workingDays;

    public DeductionStatic() {
    }

    public static float getTaxPercent() {
        return taxPercent;
    }

    public static void setTaxPercent(float taxPercent) {
        DeductionStatic.taxPercent = taxPercent;
    }

    public static float getPensionInsurancePercent() {
        return pensionInsurancePercent;
    }

    public static void setPensionInsurancePercent(float pensionInsurancePercent) {
        DeductionStatic.pensionInsurancePercent = pensionInsurancePercent;
    }

    public static int getWorkingDays() {
        return workingDays;
    }

    public static void setWorkingDays(int workingDays) {
        DeductionStatic.workingDays = workingDays;
    }

    public static int getMonth() {
        return month;
    }

    public static void setMonth(int month) {
        DeductionStatic.month = month;
    }
}
