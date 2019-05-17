package com.lazimisha.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lazimisha.controller.mail.MailSender;
import com.lazimisha.model.User;
import com.lazimisha.service.user.UserService;

@Controller
public class AuthenticationController {

	@Autowired
	private UserService userService;

	@Autowired
	private JavaMailSender javaMailSender;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView comingSoon() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/signup/comingSoon"); // resources/template/login.html
		return modelAndView;
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login"); // resources/template/login.html
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register"); // resources/template/register.html
		return modelAndView;
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home"); // resources/template/home.html
		return modelAndView;
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminHome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin"); // resources/template/admin.html
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();

		String emailOfUser = user.getEmail();

		user.setId(BigDecimal.ZERO);

		BigDecimal returnKey = BigDecimal.ZERO;

		// Check for the validations
		EmailValidator emailValidator = new EmailValidator();

		if (!emailValidator.validate(emailOfUser)) {
			modelAndView.addObject("successMessage", "invalid email");
			modelMap.addAttribute("bindingResult", bindingResult);
		}

		if (bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage", "Please correct the errors in form!");
			modelMap.addAttribute("bindingResult", bindingResult);
		}

		if (userService.isUserAlreadyPresent(emailOfUser) != null) {
			modelAndView.addObject("successMessage",
					"this email has been used already\nlogin instead or check your email for confirmation");
			modelMap.addAttribute("bindingResult", bindingResult);
		}

		else {

			BigDecimal randomNumber = MailSender.randomNumberForSignup();
			user.setRandomNumber(randomNumber);
			returnKey = userService.saveUser(user);

			System.out.println("users: " + user);

			if (returnKey.intValue() > 0) {

				modelAndView.addObject("successMessage", "User is signed up successfully!");

				MailSender.send(emailOfUser, "Lazimisha", String.valueOf(randomNumber)
						+ "\nuse the code number above to confirm your sign up."
						+ "\nplease click the link below to confirm your sign up\nhttp://localhost:8080/confirmation",
						javaMailSender);
			} else {
				modelAndView.addObject("successMessage", "not successful. please try again!");
			}

		}

		// System.out.println("user: " +
		// userService.isUserAlreadyPresent(user.getEmail().toString()));

		// else if (userService.isUserAlreadyPresent(user)) {
		// modelAndView.addObject("successMessage", "user already exists!");
		// }
		// we will save the user if, no binding errors
		// else {
		// userService.saveUser(user);
		// modelAndView.addObject("successMessage", "User is signed up successfully!");
		// }

		modelAndView.addObject("user", new User());
		modelAndView.setViewName("register");
		return modelAndView;
	}

}