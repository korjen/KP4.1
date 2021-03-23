package by.bsuir.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "passport")
public class Passport implements Serializable {
    private static final long serialVersionUID = 4L;
    @Id
    private String passportSeriesNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idWorker")
    private Worker worker;

    @Column
    private String surname;
    @Column
    private String name;
    @Column
    private String patronymic;
    @Column
    private String gender;
    @Column
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String birthDate;
    @Column
    private String address;

    public Passport() {
    }

    public Passport(String passportSeriesNumber, String surname, String name, String patronymic, String gender, String birthDate, String address) {
        this.passportSeriesNumber = passportSeriesNumber;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.gender = gender;
        this.birthDate = birthDate;
        this.address = address;
    }

    public Passport(String passportSeriesNumber, Worker worker, String surname, String name, String patronymic, String gender, String birthDate, String address) {
        this.passportSeriesNumber = passportSeriesNumber;
        this.worker = worker;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.gender = gender;
        this.birthDate = birthDate;
        this.address = address;
    }

    public String getPassportSeriesNumber() {
        return passportSeriesNumber;
    }

    public void setPassportSeriesNumber(String passportSeriesNumber) {
        this.passportSeriesNumber = passportSeriesNumber;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
