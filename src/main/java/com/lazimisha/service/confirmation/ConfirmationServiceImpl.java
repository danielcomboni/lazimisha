package com.lazimisha.service.confirmation;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lazimisha.entity.signup.Signup;
import com.lazimisha.model.User;
import com.lazimisha.repository.UserRepository;
import com.lazimisha.repository.confirmation.ConfirmationRepository;

@Service
public class ConfirmationServiceImpl implements ConfirmationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ConfirmationRepository confirmationRepository;

	@Override
	@Transactional
	public int confirmSignup(Signup signup) {
		confirmationRepository.save(signup);
		confirmationRepository.flush();
		BigDecimal id = signup.getId();
		return id.intValue();
	}

	@Override
	public User isUserAlreadyPresent(String email) {
		return userRepository.findByEmail(email);
	}

}
