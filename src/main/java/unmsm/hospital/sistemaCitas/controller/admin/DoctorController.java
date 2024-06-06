package unmsm.hospital.sistemaCitas.controller.admin;

import unmsm.hospital.sistemaCitas.entity.Doctor;
import unmsm.hospital.sistemaCitas.entity.DoctorDirectory;
import unmsm.hospital.sistemaCitas.entity.Specialty;
import unmsm.hospital.sistemaCitas.service.UserService;
import unmsm.hospital.sistemaCitas.service.DoctorDirService;
import unmsm.hospital.sistemaCitas.service.DoctorService;
import unmsm.hospital.sistemaCitas.service.SpecialtyService;
import unmsm.hospital.sistemaCitas.dto.DoctorDto;

import java.util.List;
import java.util.Iterator;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class DoctorController {

    private final UserService userService;
    private final SpecialtyService specialtyService;
    private final DoctorService doctorService;
	private final DoctorDirService doctorDirService;

    public DoctorController(UserService userService,
			    SpecialtyService specialtyService,
				DoctorDirService doctorDirService,
			    DoctorService doctorService)
    {
	this.userService = userService;
	this.specialtyService = specialtyService;
	this.doctorDirService = doctorDirService;
	this.doctorService = doctorService;
    }

    @GetMapping("/admin/doctor")
    public String showAddDoctorForm(Model model) {
	List<Specialty> specialties = specialtyService.listSpecialties();
	model.addAttribute("doctor", new DoctorDto());
	model.addAttribute("specialties", specialties);
	return "admin/doctor";
    }

    @PostMapping("/admin/doctor")
    public String saveDoctor(@Valid @ModelAttribute("doctor") DoctorDto doctorDto,
			     BindingResult result,
			     Model model) {
	if (result.hasErrors()) {
	    List<Specialty> specialties = specialtyService.listSpecialties();
	    model.addAttribute("doctor", doctorDto);
	    model.addAttribute("specialties", specialties);
	    return "admin/doctor";
	}
	doctorService.saveDoctor(doctorDto);
	return "redirect:/admin/doctor?success";
    }

    @GetMapping("/admin/doctor/specialty")
    public String showDoctorSpecialtyForm(Model model){
	List<Doctor> doctors = doctorService.listDoctors();
	List<Specialty> specialties = specialtyService.listSpecialties();
	model.addAttribute("doctors", doctors);
	model.addAttribute("specialties", specialties);
	model.addAttribute("doctor", new DoctorDto());
	return "admin/doctor/specialty";
    }

    @PostMapping("/admin/doctor/specialty")
    public String saveDoctorSpecialties(@ModelAttribute("doctor") Long doctor_id,
					@ModelAttribute("specialty") Long specialty_id,
					@ModelAttribute("doctordto") DoctorDto doctorDto,
					BindingResult result,
					Model model){
	
	// TODO Necesito revisar esto denuevo. Al menos funciona...
        Doctor updatedDoctor  = doctorService.findDoctorById(doctor_id);
	List<Specialty> oldSpecialties = updatedDoctor.getSpecialties();
	Iterator<Specialty> oldSpecialtiesIt = oldSpecialties.iterator();
	boolean specialtyFound = false;

	while (oldSpecialtiesIt.hasNext() && !specialtyFound)
	    {
		if (oldSpecialtiesIt.next().getId() == specialty_id) {
		    specialtyFound = true;
		    result.rejectValue("specialty", null,
				       "El doctor ya tiene asignada esa especialidad");
		}
	    }
	
        if (result.hasErrors()) {
	    // List<Doctor> doctors = doctorService.listDoctors();
	    // List<Specialty> specialties = specialtyService.listSpecialties();
	    // model.addAttribute("doctors", doctors);
	    // model.addAttribute("specialties", specialties);
	    // model.addAttribute("doctor", new DoctorDto());
            return "redirect:/admin/doctor/specialty?error";
        }
	
	doctorService.updateDoctorSpecialties(doctor_id, specialty_id);
	return "redirect:/admin/doctor/specialty?success";
    }

    @GetMapping("/list/doctors")
    public String doctors(Model model){
        List<Doctor> doctors = doctorService.listDoctors();
        model.addAttribute("doctors", doctors);
        return "list/doctors";
    }

	@GetMapping("/doctors/{id}")
	public String viewDoctor(@PathVariable Long id, Model model) {
		Doctor doctor = doctorService.findDoctorById(id);
		DoctorDirectory doctorDirectory = doctorDirService.findDoctorDirById(id);
		if (doctor == null) {
			return "error-view";
		}
		System.out.println(doctor.getNames());
		model.addAttribute("doctor", doctor);
		model.addAttribute("directory", doctorDirectory);
		return "doctor-view";
	}
	

}
