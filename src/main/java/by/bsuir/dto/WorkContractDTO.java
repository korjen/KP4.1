package by.bsuir.dto;

import java.io.Serializable;

public class WorkContractDTO implements Serializable {
    private int contractNumber;
    private String position;
    private String signDate;
    private String endDate;
    private float rate;
    private String dismissalDate;
    private String orderNumber;

    public WorkContractDTO() {
    }

    public WorkContractDTO(int contractNumber, String position, String signDate, String endDate, float rate, String dismissalDate, String orderNumber) {
        this.contractNumber = contractNumber;
        this.position = position;
        this.signDate = signDate;
        this.endDate = endDate;
        this.rate = rate;
        this.dismissalDate = dismissalDate;
        this.orderNumber = orderNumber;
    }

    public int getContractNumber() {
        return contractNumber;
    }

    public String getPosition() {
        return position;
    }

    public String getSignDate() {
        return signDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public float getRate() {
        return rate;
    }

    public void setContractNumber(int contractNumber) {
        this.contractNumber = contractNumber;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getDismissalDate() {
        return dismissalDate;
    }

    public void setDismissalDate(String dismissalDate) {
        this.dismissalDate = dismissalDate;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
