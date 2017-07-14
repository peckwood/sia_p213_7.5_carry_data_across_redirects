package spittr.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.result.FlashAttributeResultMatchers;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spittr.Spitter;
import spittr.data.SpitterRepository;

@Controller
@RequestMapping("/spitter")
public class SpitterController {

	private SpitterRepository spitterRepository;

	@Autowired
	public SpitterController(SpitterRepository spitterRepository) {
		this.spitterRepository = spitterRepository;
	}

	@RequestMapping(value = "/register", method = GET)
	public String showRegistrationForm() {
		return "registerForm";
	}

	@RequestMapping(value = "/register", method = POST)
	public String processRegistration(@Valid Spitter spitter, Errors errors) {
		if (errors.hasErrors()) {
			return "registerForm";
		}

		spitterRepository.save(spitter);
		return "redirect:/spitter/" + spitter.getUsername();
	}

	// =====================================================================================

	@RequestMapping(value = "/register_url", method = GET)
	public String showRegistrationForm_URL_Templates() {
		return "registerForm";
	}

	@RequestMapping(value = "/register_url", method = POST)
	public String processRegistration_URL_Templates(@Valid Spitter spitter, Errors errors,
			// 1. add model
			Model model) {
		if (errors.hasErrors()) {
			return "registerForm";
		}
		spitterRepository.save(spitter);
		model.addAttribute("username", spitter.getUsername());// 2. add
																// attribute to
																// model
		return "redirect:/spitter/{username}";// 3. use placeholder
	}

	// =====================================================================================
	@RequestMapping(value = "/register_flash", method = GET)
	public String showRegistrationForm_flash() {
		return "registerForm";
	}

	@RequestMapping(value = "/register_flash", method = POST)
	// 1. add a RedirectAttributes parameter
	public String processRegistration_flash(@Valid Spitter spitter, Errors errors, RedirectAttributes model) {
		if (errors.hasErrors()) {
			return "registerForm";
		}
		spitterRepository.save(spitter);
		model.addAttribute("username", spitter.getUsername());
		// 2. add flashAttibute (the object you wish to carry though redirect
		// request
		model.addFlashAttribute(spitter);
		return "redirect:/spitter/{username}";
	}

	@RequestMapping(value = "/{username}", method = GET)
	//3. add a Model in redirect processing method, so it will get the flash attributes
	public String showSpitterProfile(@PathVariable String username, Model model) {
		if (!model.containsAttribute("spitter")) {
			System.out.println("model doesn't have spitter (not using flash attributes)");
			model.addAttribute(spitterRepository.findByUsername(username));
		} else {
			System.out.println("model have spitter (using flash attributes)");
		}
		return "profile";
	}

}
