package controllers;

//import javax.enterprise.inject.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import data.DogApplicationDAO;
import entities.Appointment;
import entities.Availability;
import entities.Dog;
import entities.Sitter;
import entities.User;

@Controller
@SessionAttributes("user")
public class DogAppController {

	@Autowired
	private DogApplicationDAO dao;

	public void setDogAppDao(DogApplicationDAO dao) {
		this.dao = dao;
	}

	@ModelAttribute("newDog")
	Dog createDog() {
		return new Dog();
	}

	// still needs to be fixed
	@RequestMapping(path = "createDog.do", method = RequestMethod.POST)
	public ModelAndView createNewDog(Dog dog, @ModelAttribute("user") User user) {
		dog.setUser(user);
		if (dog.getImageUrl().equals("")) {
			dog.setImageUrl(null);
		}
		dog.setImageUrl(dog.getImageUrl());
		dao.createDog(dog);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect: profile.jsp");
		User newUser = dao.showUser(user.getId());
		mv.addObject("user", newUser);
		return mv;
	}

	@RequestMapping(path = "updateDog.do", method = RequestMethod.POST)
	public ModelAndView updateDog(Dog dog, int id) {
		ModelAndView mv = new ModelAndView("profile.jsp");
		mv.addObject("dogObject", dao.updateDog(id, dog)); // not complete
		return mv;
	}

	/// Passed Testing
	@RequestMapping(path = "deleteDog.do", method = RequestMethod.POST)
	public ModelAndView deleteDog(Dog dog, @ModelAttribute("user") User user) {
		dao.deleteDog(dog.getId());
		ModelAndView mv = new ModelAndView("profile.jsp");
		User newUser = dao.showUser(user.getId());
		mv.addObject("user", newUser);
		return mv;
	}

	// ***Passed Junit Testing***
	@RequestMapping(path = "showDog.do", method = RequestMethod.GET)
	public String show(@RequestParam("id") Integer id, Model model) {
		Dog dog = dao.showDog(id);
		model.addAttribute("dog", dog);
		return "profile.jsp";
	}

	// ****Passed Junit Testing****
	@RequestMapping(path = "showSitter.do", method = RequestMethod.GET)
	public String showSitter(@RequestParam("id") Integer id, Model model) {
		Sitter sitter = dao.showSitter(id);
		model.addAttribute("sitter", sitter);
		return "profile.jsp";
	}

	// *** Passed Junit Testing *****
	@RequestMapping(path = "showUser.do", method = RequestMethod.GET)
	public String showUser(@RequestParam("id") Integer id, Model model) {
		User user = dao.showUser(id);
		model.addAttribute("user", user);
		return "profile.jsp";

	}

	@RequestMapping(path = "deleteUser.do", method = RequestMethod.POST)
	public ModelAndView deleteUser(int id) {
		ModelAndView mv = new ModelAndView("profile.jsp");
		dao.deleteUser(id); // not complete
		return mv;
	}

	@RequestMapping(path = "createAppointment.do", method = RequestMethod.POST)
	public ModelAndView createAppointment(@ModelAttribute("newAppointment") Appointment appointment) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("appointment.jsp");
		dao.createAppointment(appointment); // not complete
		return mv;
	}

	@RequestMapping(path = "reschedule.do", method = RequestMethod.POST)
	public ModelAndView reschedule(int id, Appointment appointment) {
		ModelAndView mv = new ModelAndView("appointment.jsp");
		mv.addObject("AppointmentObject", dao.reschedule(id, appointment)); // not
																			// complete
		return mv;
	}

	@RequestMapping(path = "cancelAppointment.do", method = RequestMethod.POST)
	public ModelAndView cancelAppointment(int id) {
		ModelAndView mv = new ModelAndView("Appointment.jsp");
		dao.cancelAppointment(id); // not complete
		return mv;
	}

	@RequestMapping(path = "setRating.do", method = RequestMethod.POST)
	public ModelAndView setRating(@ModelAttribute("user") User user, @RequestParam(name = "rating") Double rating,
			@RequestParam(name = "apptId") Integer apptId) {
		ModelAndView mv = new ModelAndView();
		Appointment a = dao.showAppointment(apptId);
		a.setRating(rating);
		dao.setRatingInDB(a);
		mv.setViewName("viewSitters.jsp");
		mv.addObject("sitters", dao.indexOfSitters(user));
		mv.addObject("appointment", a);

		return mv;
	}
	@RequestMapping(path = "addBalance.do", method = RequestMethod.GET)
	public ModelAndView setRating(@ModelAttribute("user") User user) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		mv.setViewName("addBalance.jsp");
		return mv;
	}
	@RequestMapping(path = "addBalance.do", method = RequestMethod.POST)
	public ModelAndView setRating(@ModelAttribute("user") User user, 
		@RequestParam(name="balance") Double amount) {
		ModelAndView mv = new ModelAndView();
	System.out.println(amount);
	
	mv.addObject("user",dao.addBalanceToUser(user.getId(), amount));
	mv.setViewName("profile.jsp");
		return mv;
	}

	@RequestMapping(path = "updateSitterStatus.do", method = RequestMethod.POST)
	public ModelAndView updateSitterStatus(@ModelAttribute("user") User user) {
		ModelAndView mv = new ModelAndView();
		System.out.println(user.getActiveSitter());
		user = dao.updateUser(user.getId(), user);
		System.out.println(user.getActiveSitter());
		mv.addObject("user", user);
		mv.setViewName("profile.jsp");

		return mv;

	}
	
	@RequestMapping(path = "updateSitterAvailability.do", method = RequestMethod.POST)
	public ModelAndView updateSitterAvailability(@ModelAttribute("user") User user,
			@RequestParam(name = "availability") Integer availability) {
		ModelAndView mv = new ModelAndView();
		Availability a = Availability.valueOf(availability);
		System.out.println(user);
		user = dao.updateSitter(user.getSitter().getId(), a);
		mv.addObject("user", user);
		mv.setViewName("profile.jsp");

		return mv;

	}
	
	@RequestMapping(path = "updateSitterRate.do", method = RequestMethod.POST)
	public ModelAndView updateSitterRate(@ModelAttribute("user") User user,
			@RequestParam(name = "rate") Double rate) {
		ModelAndView mv = new ModelAndView();
		
		user = dao.updateSitter(user.getSitter().getId(), rate);
		mv.addObject("user", user);
		mv.setViewName("profile.jsp");
		return mv;
		
	}
	
}