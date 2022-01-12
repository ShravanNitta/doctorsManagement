package interview.assignment.patients.model;

import java.io.Serializable;

public class Patient implements Serializable {
    private String patientId;
    private String name;
    private String birthdate;
    private String gender;
    private String mobileNumber;
    private String nationality;
    private String idNumber;

    public Patient(){}
    public Patient(String patientId, String name, String birthdate, String gender, String mobileNumber, String nationality, String idNumber) {
        this.patientId = patientId;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.nationality = nationality;
        this.idNumber = idNumber;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
}
