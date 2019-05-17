package com.lazimisha.service.user;

import java.math.BigDecimal;
import java.util.List;

import com.lazimisha.model.User;

public interface UserService {

	public BigDecimal saveUser(User user);
	
	public BigDecimal returnKey();
	
	public User isUserAlreadyPresent(String email);


	public List<User> findAllUsers();
	
}
