package interview.assignment.patients.components;

import interview.assignment.patients.entities.DoctorsEntity;
import interview.assignment.patients.entities.PatientsEntity;
import interview.assignment.patients.model.Patient;
import interview.assignment.patients.repositories.DoctorsRepository;
import interview.assignment.patients.repositories.PatientsRepository;
import interview.assignment.patients.utilities.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class PatientService {

    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private PatientsRepository patientsRepository;

    @Autowired
    private DoctorsRepository doctorsRepository;

    public ResponseEntity<Object> addPatient(String doctorId,Patient patient) {

        if(patient == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request Body");
        }

        if(null == doctorId){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Doctor Id should not be empty");
        }

        if(!doctorsRepository.existsById(doctorId)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor Id not found");
        }

        if(null == patient.getPatientId()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Patient Id should not be empty");
        }

        if(null == patient.getBirthdate()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Birthdate should not be empty");
        }

        if(null == patient.getGender()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Gender should not be empty");
        }

        if(null == patient.getMobileNumber()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mobile number should not be empty");
        }

        if(null == patient.getNationality()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nationality should not be empty");
        }

        if(null == patient.getIdNumber()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id number should not be empty");
        }

        Timestamp birthDate = Util.convertUITimeToTimestamp(patient.getBirthdate());

        if(null == birthDate){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Birthdate");
        }

        PatientsEntity patientsEntity = new PatientsEntity();
        patientsEntity.setPatientId(patient.getPatientId());
        patientsEntity.setName(patient.getName());
        patientsEntity.setGender(patient.getGender());
        patientsEntity.setBirthdate(birthDate);
        patientsEntity.setMobileNumber(patient.getMobileNumber());
        patientsEntity.setNationality(patient.getNationality());
        patientsEntity.setIdNumber(patient.getIdNumber());
        Optional<DoctorsEntity> doctorsEntityOptional = doctorsRepository.findById(doctorId);

        DoctorsEntity doctorsEntity = doctorsEntityOptional.get();
        System.out.println("DoctorId :: "+doctorsEntity.getDoctorId());
        patientsEntity.setDoctors(Arrays.asList(doctorsEntity));

        doctorsEntity.setPatients(Arrays.asList(patientsEntity));
        patientsEntity = patientsRepository.save(patientsEntity);
        if (null == patientsEntity) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save patient record");
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    public ResponseEntity<Object> updatePatient(String doctorId,Patient patient) {
        if(patient == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing Patient Data");
        }

        if(null == doctorId){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing Doctor Id");
        }

        if(null ==  patient.getPatientId()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing Patient Id");
        }

        if(!doctorsRepository.existsById(doctorId)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
        }

        if(!patientsRepository.existsById(patient.getPatientId())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");
        }

        Optional<PatientsEntity> patientsEntityOptional = patientsRepository.findById(patient.getPatientId());

        PatientsEntity patientsEntity = patientsEntityOptional.get();

        patientsEntity.setName(null != patient.getName() ? patient.getName() : patientsEntity.getName());
        patientsEntity.setGender(null != patient.getGender() ? patient.getGender() : patientsEntity.getGender());
        patientsEntity.setMobileNumber(null != patient.getMobileNumber() ? patient.getMobileNumber() : patientsEntity.getMobileNumber());
        patientsEntity.setNationality(null != patient.getNationality() ? patient.getNationality() : patientsEntity.getNationality());
        patientsEntity.setIdNumber(null != patient.getIdNumber() ? patient.getIdNumber() : patientsEntity.getIdNumber());
        patientsEntity = patientsRepository.save(patientsEntity);
        if (null == patientsEntity) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update patient record");
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    public ResponseEntity<Object> deletePatient(String patientId,String doctorId){
        Optional<DoctorsEntity> doctorsEntityOptional = doctorsRepository.findById(doctorId);
        DoctorsEntity doctorsEntity = doctorsEntityOptional.get();
        List<PatientsEntity> patientsEntityList = doctorsEntity.getPatients();
        patientsEntityList.removeIf(obj -> obj.getPatientId().equalsIgnoreCase(patientId));

        doctorsEntity.setPatients(patientsEntityList);

        doctorsRepository.save(doctorsEntity);
        patientsRepository.deleteById(patientId);
        logger.info("Patient Deleted Successfully !!");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Object> getPatientDetails(String patientId) {

        Optional<PatientsEntity> patientsEntityOptional = patientsRepository.findById(patientId);
        if(null != patientsEntityOptional && patientsEntityOptional.isPresent()){
            PatientsEntity patientsEntity = patientsEntityOptional.get();
            Patient patient = new Patient();
            patient.setPatientId(patientsEntity.getPatientId());
            patient.setName(patientsEntity.getName());
            patient.setGender(patientsEntity.getGender());
            patient.setBirthdate(patientsEntity.getBirthdate().toString());
            patient.setMobileNumber(patientsEntity.getMobileNumber());
            patient.setNationality(patientsEntity.getNationality());
            patient.setIdNumber(patientsEntity.getIdNumber());
            return new ResponseEntity<>(patient, HttpStatus.OK);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient Not Found");
        }
    }
}
