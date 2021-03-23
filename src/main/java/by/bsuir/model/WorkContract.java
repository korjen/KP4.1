package by.bsuir.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "workContract")
public class WorkContract implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contractNumber;

    @OneToOne(
            mappedBy = "contract",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false
    )
    private Worker worker;

    @Column
    private String position;
    @Column
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String signDate;
    @Column
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String endDate;
    @Column
    private float rate;
    @Column
    private String dismissalDate;
    @Column
    private String orderNumber;

    public WorkContract() {
    }

    public WorkContract(Worker worker, String position, String signDate, String endDate, float rate, String dismissalDate, String orderNumber) {
        this.worker = worker;
        this.position = position;
        this.signDate = signDate;
        this.endDate = endDate;
        this.rate = rate;
        this.dismissalDate = dismissalDate;
        this.orderNumber = orderNumber;
    }

    public WorkContract(int contractNumber,String position, String signDate, String endDate, float rate, String dismissalDate, String orderNumber) {
        this.contractNumber=contractNumber;
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

    public void setContractNumber(int contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
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
