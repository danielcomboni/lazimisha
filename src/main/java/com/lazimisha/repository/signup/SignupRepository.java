package com.lazimisha.repository.signup;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lazimisha.entity.signup.Signup;

@Repository
public interface SignupRepository extends JpaRepository<Signup, BigDecimal>{

}
