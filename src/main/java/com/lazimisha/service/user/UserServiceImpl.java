package com.lazimisha.service.user;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lazimisha.model.Role;
import com.lazimisha.model.User;
import com.lazimisha.repository.RoleRepository;
import com.lazimisha.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;

	private BigDecimal returnKey;

	public BigDecimal getReturnKey() {
		return returnKey;
	}

	public void setReturnKey(BigDecimal returnKey) {
		this.returnKey = returnKey;
	}

	@Override
	public BigDecimal saveUser(User user) {

		user.setPassword(encoder.encode(user.getPassword()));
		user.setStatus("VERIFIED");
		Role userRole = roleRepository.findByRole("SITE_USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

		userRepository.save(user);

		userRepository.flush();
		setReturnKey(user.getId());
		return user.getId();

	}

	@Override
	public BigDecimal returnKey() {
		return getReturnKey();
	}

	@Override
	public User isUserAlreadyPresent(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

}
