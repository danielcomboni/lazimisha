package com.lazimisha.controller.redirect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

	/**
	 * redirects to login
	 * 
	 * 
	 * @return /login
	 */
	@GetMapping("/toLogin")
	public String toLogin() {
		return "redirect:/login";
	}

	/**
	 * redirects to register or sign up
	 * 
	 * 
	 * @return /register
	 */
	@GetMapping("/toSignup")
	public String toSignup() {
		return "redirect:/register";
	}

}
