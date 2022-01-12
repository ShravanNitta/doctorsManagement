package interview.assignment.patients.services;

import interview.assignment.patients.components.DoctorsService;
import interview.assignment.patients.components.PatientService;
import interview.assignment.patients.model.Doctor;
import interview.assignment.patients.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class DPRestController {

private static final Logger logger = LoggerFactory.getLogger(DPRestController.class);

    @Autowired
    private DoctorsService doctorsService;

    @Autowired
    private PatientService patientService;

    @PostMapping(path = "/addDoctor",produces = "application/json",consumes = "application/json")
    public ResponseEntity<Object> addDoctor(@RequestBody Doctor doctor){
        return doctorsService.addDoctor(doctor);
    }

    @GetMapping(path = "/listPatients",produces = "application/json",consumes = "application/json")
    public ResponseEntity<List<Patient>> patientsList(@RequestParam String doctorId){
        return doctorsService.getPatientsList(doctorId);
    }

    @PostMapping(path = "/addPatient",produces = "application/json",consumes = "application/json")
    public ResponseEntity<Object> addPatient(@RequestParam String doctorId, @RequestBody Patient patient){
        return patientService.addPatient(doctorId,patient);
    }

    @PostMapping(path = "/updatePatient",produces = "application/json",consumes = "application/json")
    public ResponseEntity<Object> updatePatient(@RequestParam String doctorId, @RequestBody Patient patient){
        return patientService.updatePatient(doctorId,patient);
    }

    @DeleteMapping(path = "/deletePatient")
    public ResponseEntity<Object> deletePatient(@RequestParam String patientId,@RequestParam String doctorId){
        return patientService.deletePatient(patientId,doctorId);
    }
}
