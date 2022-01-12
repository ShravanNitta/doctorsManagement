package interview.assignment.patients.model;

import java.io.Serializable;

public class Doctor implements Serializable {
    private String doctorId;
    private String doctorName;
    private String departmentName;
    private String mobileNumber;

    public Doctor(){}

    public Doctor(String doctorId, String doctorName, String departmentName, String mobileNumber) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.departmentName = departmentName;
        this.mobileNumber = mobileNumber;
    }

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
}
