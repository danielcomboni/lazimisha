package com.lazimisha.service.signup;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lazimisha.entity.signup.Signup;
import com.lazimisha.repository.signup.SignupRepository;

@Service
public class SignupServiceImpl implements SignupService {

	@Autowired
	private SignupRepository signupRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	@Transactional
	public BigDecimal confirmSignup(Signup signup) {

		String passwordHashed = encoder.encode(signup.getPassword());

		signup.setPassword(passwordHashed);

		Date date = new Date(System.currentTimeMillis());

		signup.setDateUser(date);

		signup.setConfirmed(true);

		signupRepository.save(signup);
		signupRepository.flush();

		return signup.getId();
	}

}
