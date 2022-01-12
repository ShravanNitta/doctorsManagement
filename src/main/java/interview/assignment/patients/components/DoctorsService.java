package interview.assignment.patients.components;

import interview.assignment.patients.entities.DoctorsEntity;
import interview.assignment.patients.entities.PatientsEntity;
import interview.assignment.patients.model.Doctor;
import interview.assignment.patients.model.Patient;
import interview.assignment.patients.repositories.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DoctorsService {

    @Autowired
    private DoctorsRepository doctorsRepository;

    public ResponseEntity<List<Doctor>> getDoctorsList() {
        List<Doctor> doctors = new ArrayList<>();
        try {
            List<DoctorsEntity> doctorsEntityList = doctorsRepository.findAll();
            if (null == doctorsEntityList || (null !=doctorsEntityList && doctorsEntityList.isEmpty())) {
                return new ResponseEntity<>(doctors, HttpStatus.NOT_FOUND);
            }
            doctors = doctorsEntityList
                    .stream()
                    .map(entity -> new Doctor(entity.getDoctorId(), entity.getDoctorName(), entity.getDepartmentName(), entity.getMobileNumber()))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(doctors, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(doctors, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Patient>> getPatientsList(String doctorId) {
        List<Patient> patients = new ArrayList<>();
        try {
            Optional<DoctorsEntity> doctorsEntityOptional = doctorsRepository.findById(doctorId);
            if (null == doctorsEntityOptional || (null !=doctorsEntityOptional && !doctorsEntityOptional.isPresent())) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor Id not found");
                return new ResponseEntity<>(patients, HttpStatus.NOT_FOUND);
            }
            DoctorsEntity doctorsEntity = doctorsEntityOptional.get();
            List<PatientsEntity> patientsEntityList = doctorsEntity.getPatients();
            patients = patientsEntityList
                    .stream()
                    .map(entity -> new Patient(entity.getPatientId(), entity.getName(), entity.getBirthdate().toString(), entity.getGender(), entity.getMobileNumber(), entity.getNationality(), entity.getIdNumber()))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(patients, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            return new ResponseEntity<>(patients, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> addDoctor(Doctor doctor) {

        if(doctor == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request Body");
        }

        if(null == doctor.getDoctorId()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("DoctorId should not be empty");
        }

        if(null == doctor.getDoctorName()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Doctor Name should not be empty");
        }

        if(null == doctor.getDepartmentName()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Department should not be empty");
        }

        if(null == doctor.getDepartmentName()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mobile number should not be empty");
        }

        if(doctorsRepository.existsById(doctor.getDoctorId()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("DoctorId already Exist");

        try {
            DoctorsEntity doctorsEntity = new DoctorsEntity();
            doctorsEntity.setDoctorId(doctor.getDoctorId());
            doctorsEntity.setDoctorName(doctor.getDoctorName());
            doctorsEntity.setDepartmentName(doctor.getDepartmentName());
            doctorsEntity.setMobileNumber(doctor.getMobileNumber());
            DoctorsEntity doctorsEntity1 = doctorsRepository.save(doctorsEntity);
            if(null != doctorsEntity1){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save doctor record");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
