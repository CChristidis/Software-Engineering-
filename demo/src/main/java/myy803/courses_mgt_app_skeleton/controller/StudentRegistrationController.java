package myy803.courses_mgt_app_skeleton.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import myy803.courses_mgt_app_skeleton.entity.StudentRegistration;
import myy803.courses_mgt_app_skeleton.service.StudentRegistrationService;

@Controller
@RequestMapping("/registrations")
public class StudentRegistrationController {
	@Autowired
	private StudentRegistrationService studentRegistrationService;
	
	public StudentRegistrationController(StudentRegistrationService theStudentRegistrationService) {
		studentRegistrationService = theStudentRegistrationService;
	}
	
	@RequestMapping("/list")
	public String listStudentRegistrations(Model theModel) {
		
		// get employees from db
		List<StudentRegistration> theRegistrations = studentRegistrationService.findAll();
		theModel.addAttribute("registrations", theRegistrations);
		
		return "registrations/list-registrations";
	}
	
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		StudentRegistration theEmployee = new StudentRegistration();
		
		theModel.addAttribute("employee", theEmployee);
		
		return "registrations/registration-save-form";
	}


	@RequestMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") StudentRegistration theEmployee, Model theModel) {
		
		// save the employee
		studentRegistrationService.save(theEmployee);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/registrations/list";
	}
	
	@RequestMapping("/showFormForUpdate")
	// actual parameter "registrationId" corresponds to <a th:href="@{/registrations/showFormForUpdate(registrationId=${tempRegistration.id})}"
	// "registrationId" of template list-registrations.html.
	public String showFormForUpdate(@RequestParam("registrationId") int theId,
									Model theModel) {
		
		// get the employee from the service
		StudentRegistration theStudentRegistration = studentRegistrationService.findById(theId);
		
		// set employee as a model attribute to pre-populate the form
		theModel.addAttribute("employee", theStudentRegistration);
		
		// send over to our form
		return "registrations/registration-save-form";			
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("studentRegId") int theId) {
		// delete the registration
		studentRegistrationService.delete(theId);
		// redirect to /registrations/list
		return "redirect:/registrations/list";
	}
	
	
	
}
