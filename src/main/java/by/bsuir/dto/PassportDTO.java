package by.bsuir.dto;

import java.io.Serializable;

public class PassportDTO implements Serializable {
    private String passportSeriesNumber;
    private String surname;
    private String name;
    private String patronymic;
    private String gender;
    private String birthDate;
    private String address;

    public PassportDTO() {
    }

    public PassportDTO(String passportSeriesNumber, String surname, String name, String patronymic, String gender, String birthDate, String address) {
        this.passportSeriesNumber = passportSeriesNumber;
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

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setPassportSeriesNumber(String passportSeriesNumber) {
        this.passportSeriesNumber = passportSeriesNumber;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
