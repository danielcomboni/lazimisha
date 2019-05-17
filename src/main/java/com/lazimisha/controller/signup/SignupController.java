package com.lazimisha.controller.signup;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.lazimisha.controller.message.Message;
import com.lazimisha.entity.signup.Signup;
import com.lazimisha.service.confirmation.ConfirmationManipulation;
import com.lazimisha.service.signup.SignupService;

@Controller
public class SignupController {

	private BigDecimal result = BigDecimal.ZERO;

	@Autowired
	private SignupService signupService;

	@Autowired
	private ConfirmationManipulation confirmationManipulation;

	@PostMapping("/confirmSignup")
	@ResponseBody
	public String confirmSignup(@RequestBody Signup signup) {

		System.out.println("testing................");

		signup.setRandomNumber(confirmationManipulation.getUserFromDatabase().getRandomNumber());
		signup.setUserId(confirmationManipulation.getUserFromDatabase().getId());

		System.out.println("singup : " + signup);

		result = signupService.confirmSignup(signup);

		System.out.println("result:" + result);

		return "/confirmSignup";
	}

	@GetMapping("/confirmSignupResult")
	@ResponseBody
	public String confirmSignupResult() {
		Message msg = new Message();
		System.out.println("result: " + result);
		msg.setMessage(String.valueOf(result));
		return new Gson().toJson(msg);
	}

}
