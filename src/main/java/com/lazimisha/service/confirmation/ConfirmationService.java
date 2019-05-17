package com.lazimisha.service.confirmation;

import com.lazimisha.entity.signup.Signup;
import com.lazimisha.model.User;

public interface ConfirmationService {

	public int confirmSignup(Signup signup);

	public User isUserAlreadyPresent(String email);

}
