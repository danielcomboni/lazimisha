package com.lazimisha.controller.confirmation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lazimisha.entity.signup.Signup;

@Service
public class ConfirmationJsonList {

	public ConfirmationJsonList() {

	}

	private List<Signup> signups;

	public List<Signup> getSignups() {
		return signups;
	}

	public void setSignups(List<Signup> signups) {
		this.signups = signups;
	}

}
