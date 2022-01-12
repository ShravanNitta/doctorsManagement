package interview.assignment.patients.services;

import interview.assignment.patients.components.DoctorsService;
import interview.assignment.patients.components.PatientService;
import interview.assignment.patients.model.Doctor;
import interview.assignment.patients.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ServiceController {

private static final Logger logger = LoggerFactory.getLogger(ServiceController.class);

    @Autowired
    private DoctorsService doctorsService;

    @Autowired
    private PatientService patientService;

    @RequestMapping("/")
    public ModelAndView homePage(Model model) {
        model.addAttribute("listDoctors", doctorsService.getDoctorsList().getBody());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/addDoctorForm")
    public ModelAndView addDoctorForm(Model model) {
        Doctor doctor = new Doctor();
        model.addAttribute("doctor", doctor);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add_doctor");
        return modelAndView;
    }

    @PostMapping("/addDoctor")
    public ModelAndView addDoctor(@ModelAttribute("doctor") Doctor doctor){
        logger.info("Add Doctor Request Received for doctor id :: "+(null != doctor && null != doctor.getDoctorId() ? doctor.getDoctorId() : null));
        doctorsService.addDoctor(doctor);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @GetMapping("/viewPatients/{doctorId}")
    public ModelAndView viewPatients(@PathVariable(value = "doctorId") String doctorId, Model model) {
        model.addAttribute("patientsList", doctorsService.getPatientsList(doctorId).getBody());
        model.addAttribute("doctorId", doctorId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("view_patients");
        return modelAndView;
    }

    @RequestMapping("/addPatientForm/{doctorId}")
    public ModelAndView addPatientForm(@PathVariable(value = "doctorId") String doctorId,Model model) {
        Patient patient = new Patient();
        model.addAttribute("patient", patient);
        model.addAttribute("doctorId", doctorId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add_patient");
        return modelAndView;
    }

    @PostMapping("/addPatient/{doctorId}")
    public ModelAndView addPatient(@PathVariable(value = "doctorId") String doctorId, @ModelAttribute("patient") Patient patient){
        logger.info("Add Patient Request Received for patient id :: "+patient.getPatientId());
        patientService.addPatient(doctorId,patient);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/viewPatients/"+doctorId);
        return modelAndView;
    }

    @RequestMapping("/updatePatientForm/{patientId}/{doctorId}")
    public ModelAndView updatePatientForm(@PathVariable(value = "patientId") String patientId,@PathVariable(value = "doctorId") String doctorId,Model model) {
        Patient patient = (Patient) patientService.getPatientDetails(patientId).getBody();
        System.out.println("Patient Id Sending to form :: "+patient.getPatientId());
        model.addAttribute("patient", patient);
        model.addAttribute("doctorId", doctorId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("update_patient");
        return modelAndView;
    }

    @PostMapping("/updatePatient/{doctorId}")
    public ModelAndView updatePatient(@PathVariable(value = "doctorId") String doctorId, @ModelAttribute("patient") Patient patient){
        logger.info("Update Patient Request Received for patient id :: "+patient.getPatientId());
        patientService.updatePatient(doctorId,patient);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/viewPatients/"+doctorId);
        return modelAndView;
    }

    @GetMapping("/deletePatient/{patientId}/{doctorId}")
    public ModelAndView deletePatient(@PathVariable(value = "patientId") String patientId,@PathVariable(value = "doctorId") String doctorId){
        logger.info("Delete Patient Request Received for doctor id :: "+doctorId +" and patientId ::"+patientId);
        patientService.deletePatient(patientId,doctorId);
        System.out.println("Deleted Successfully");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/viewPatients/"+doctorId);
        return modelAndView;
    }
}
