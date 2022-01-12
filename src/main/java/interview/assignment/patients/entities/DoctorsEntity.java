package interview.assignment.patients.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_doctors")
public class DoctorsEntity {

    @Id
    @Column(name = "DOCTOR_ID")
    private String doctorId;

    @Column(name = "DOCTOR_NAME")
    private String doctorName;

    @Column(name = "DEPARTMENT_NAME")
    private String departmentName;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @ManyToMany(mappedBy = "doctors")
    private List<PatientsEntity> patients;

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public List<PatientsEntity> getPatients() {
        return patients;
    }

    public void setPatients(List<PatientsEntity> patients) {
        this.patients = patients;
    }
}
