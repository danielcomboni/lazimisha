package com.lazimisha.controller.confirmation;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.lazimisha.controller.message.Message;
import com.lazimisha.entity.signup.Signup;
import com.lazimisha.model.User;
import com.lazimisha.service.confirmation.ConfirmationManipulation;
import com.lazimisha.service.confirmation.ConfirmationService;

@Controller
@RequestMapping("/confirmation")
public class ConfirmationController {

	@Autowired
	private ConfirmationManipulation confirmationManipulation;

	@Autowired
	private ConfirmationService confirmationService;

	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public ModelAndView confirm(Signup signup) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/confirm/confirm");
		return modelAndView;
	}

	// get email from client to check its validity
	@RequestMapping(value = { "/checkEmail" }, method = RequestMethod.POST)
	@ResponseBody
	public String postEmailForValidification(@RequestBody Signup signup) {

		confirmationManipulation.setSignupPassed(signup);

		return "/confirmation/checkEmail";

	}

	@RequestMapping(value = { "/checkEmailResult" }, method = RequestMethod.GET)
	@ResponseBody
	public String checkEmailResult() {

		String email = confirmationManipulation.getSignupPassed().getEmail();

		Message msg = new Message();

		if (!confirmationManipulation.checkEmailValidity(email)) {

			msg.setMessage("invalid email");

		} else {

			msg.setMessage("valid email");

		}

		return new Gson().toJson(msg);

	}

	@GetMapping("/isEmailSignedAlready")
	@ResponseBody
	public String isEmailSignedAlready() {

		String emailPassed = confirmationManipulation.getSignupPassed().getEmail();
		User user = confirmationService.isUserAlreadyPresent(emailPassed);
		confirmationManipulation.setUserFromDatabase(user);

		Message msg = new Message();
		if (user != null) {
			msg.setMessage("user exists");
		} else {
			msg.setMessage("user exists not");
		}

		return new Gson().toJson(msg);

	}

	@GetMapping("/isPasswordMatching")
	@ResponseBody
	public String isPasswordMatching() {

		boolean matched = confirmationManipulation.isPasswordMatching(
				confirmationManipulation.getSignupPassed().getPassword(),
				confirmationManipulation.getUserFromDatabase());

		Message msg = new Message();
		if (matched) {
			msg.setMessage("password matched");
		} else {
			msg.setMessage("password not matched");
		}

		return new Gson().toJson(msg);

	}

	@GetMapping("/isRandomNumberMatching")
	@ResponseBody
	public String isRandomNumberMatching() {

		User user = confirmationManipulation.getUserFromDatabase();
		BigDecimal randomNumberOriginal = user.getRandomNumber();

		BigDecimal randomNumberPassed = confirmationManipulation.getSignupPassed().getRandomNumber();

		Message msg = new Message();
		if (String.valueOf(randomNumberOriginal).equalsIgnoreCase(String.valueOf(randomNumberPassed.intValue()))) {

			msg.setMessage("number matched");

		} else {
			msg.setMessage("number not matched");
		}

		return new Gson().toJson(msg);

	}

}
