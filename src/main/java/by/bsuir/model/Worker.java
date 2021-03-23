package by.bsuir.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "worker")
public class Worker implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idWorker;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contractNumber")
    private WorkContract contract;

    @OneToMany(
            mappedBy = "worker",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @OrderBy("payDate DESC")
    private List<Salary> salary = new ArrayList<>();

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "workerPrivilege",
            joinColumns = { @JoinColumn(name = "idWorker") },
            inverseJoinColumns = { @JoinColumn(name = "idPrivilege") }
    )
    private List<Privilege> privileges =  new ArrayList<>();

    @OneToOne(
            mappedBy = "worker",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Passport passport;

    @OneToOne(
            mappedBy = "worker",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private User user;

    @OneToMany(
            mappedBy = "worker",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @OrderBy("startTime DESC")
    private List<WorkingTime> workingTime = new ArrayList<>();

    @Column
    private float experience;
    @Column
    private int numberOfDependants;
    @Column
    private boolean soloParent;
    @Column
    private boolean active;
    @Column
    private String accountNumber;

    public Worker() {
    }

    public Worker(WorkContract contract, List<Salary> salary, List<Privilege> privileges, Passport passport, User user, List<WorkingTime> workingTime, float experience, int numberOfDependants, boolean soloParent, boolean active, String accountNumber) {
        this.contract = contract;
        this.salary = salary;
        this.privileges = privileges;
        this.passport = passport;
        this.user = user;
        this.workingTime = workingTime;
        this.experience = experience;
        this.numberOfDependants = numberOfDependants;
        this.soloParent = soloParent;
        this.active = active;
        this.accountNumber = accountNumber;
    }

    public Worker(WorkContract contract, Passport passport, User user, float experience, int numberOfDependants, boolean soloParent, boolean active, String accountNumber) {
        this.contract = contract;
        this.passport = passport;
        this.user = user;
        this.experience = experience;
        this.numberOfDependants = numberOfDependants;
        this.soloParent = soloParent;
        this.active = active;
        this.accountNumber = accountNumber;
    }

    public int getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(int idWorker) {
        this.idWorker = idWorker;
    }

    public WorkContract getContract() {
        return contract;
    }

    public void setContract(WorkContract contract) {
        this.contract = contract;
    }

    public float getExperience() {
        return experience;
    }

    public void setExperience(float experience) {
        this.experience = experience;
    }

    public boolean isSoloParent() {
        return soloParent;
    }

    public void setSoloParent(boolean soloParent) {
        this.soloParent = soloParent;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Salary> getSalary() {
        return salary;
    }

    public void setSalary(List<Salary> salary) {
        this.salary = salary;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<WorkingTime> getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(List<WorkingTime> workingTime) {
        this.workingTime = workingTime;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }

    public int getNumberOfDependants() {
        return numberOfDependants;
    }

    public void setNumberOfDependants(int numberOfUnderageChildren) {
        this.numberOfDependants = numberOfUnderageChildren;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
