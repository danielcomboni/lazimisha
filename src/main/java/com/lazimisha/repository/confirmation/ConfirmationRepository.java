package com.lazimisha.repository.confirmation;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lazimisha.entity.signup.Signup;

public interface ConfirmationRepository extends JpaRepository<Signup, BigDecimal>{

	
	
}
