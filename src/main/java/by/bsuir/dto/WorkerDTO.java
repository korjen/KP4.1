package by.bsuir.dto;

import java.io.Serializable;

public class WorkerDTO implements Serializable {
    private int idWorker;
    private WorkContractDTO contract;
    private PassportDTO passport;
    private UserDTO user;
    private float experience;
    private int numberOfDependants;
    private boolean soloParent;
    private boolean active;
    private String accountNumber;

    public WorkerDTO() {
    }

    public WorkerDTO(int idWorker, WorkContractDTO contract, PassportDTO passport, UserDTO user, float experience, int numberOfDependants, boolean soloParent, boolean active, String accountNumber) {
        this.idWorker = idWorker;
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

    public WorkContractDTO getContract() {
        return contract;
    }

    public PassportDTO getPassport() {
        return passport;
    }

    public UserDTO getUser() {
        return user;
    }

    public float getExperience() {
        return experience;
    }

    public int getNumberOfDependants() {
        return numberOfDependants;
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

    public void setIdWorker(int idWorker) {
        this.idWorker = idWorker;
    }

    public void setContract(WorkContractDTO contract) {
        this.contract = contract;
    }

    public void setPassport(PassportDTO passport) {
        this.passport = passport;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public void setExperience(float experience) {
        this.experience = experience;
    }

    public void setNumberOfDependants(int numberOfDependants) {
        this.numberOfDependants = numberOfDependants;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
