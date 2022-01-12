package interview.assignment.patients.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "tbl_patients")
public class PatientsEntity {

    @Id
    @Column(name = "PATIENT_ID")
    private String patientId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BIRTHDATE")
    private Timestamp birthdate;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Column(name = "NATIONALITY")
    private String nationality;

    @Column(name = "ID_NUMBER")
    private String idNumber;


    @ManyToMany()
    @JoinTable(
            name = "tbl_doctor_patients",
            joinColumns = {@JoinColumn(name = "PATIENT_ID", referencedColumnName = "PATIENT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "DOCTOR_ID", referencedColumnName = "DOCTOR_ID")})
    private List<DoctorsEntity> doctors;

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

    public Timestamp getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Timestamp birthdate) {
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

    public List<DoctorsEntity> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<DoctorsEntity> doctors) {
        this.doctors = doctors;
    }
}
