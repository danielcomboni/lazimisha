/***************************************************************************
 * @author Daniel Comboni
 * 
 *         this class manipulates the user sign up confirmation details by doing the
 *         following check if email is signed up already
 ***************************************************************************
 *         check if email is valid
 * 
 *         check if email sign up has already been confirmed (true)
 * 
 *         check password match
 * 
 *         match the random number
 * 
 *         confirm/save
 * 
 *         login (after confirmation, straight away login the user in)
 **************************************************************************
 */

package com.lazimisha.service.confirmation;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.lazimisha.controller.EmailValidator;
import com.lazimisha.entity.signup.Signup;
import com.lazimisha.model.User;

@Service
public class ConfirmationManipulation {

	// make out of it getter/setter
	private Signup signupDatabase;

	private Signup signupPassed;

	private User userDB;

	public Signup getSignupDatabase() {
		return signupDatabase;
	}

	public void setSignupDatabase(Signup signupDatabase) {
		this.signupDatabase = signupDatabase;
	}

	public Signup getSignupPassed() {
		return signupPassed;
	}

	public void setSignupPassed(Signup signupPassed) {
		this.signupPassed = signupPassed;
	}

	public User getUserDB() {
		return userDB;
	}

	public void setUserDB(User userDB) {
		this.userDB = userDB;
	}

	private User userFromDatabase;

	// check if email is valid

	public User getUserFromDatabase() {
		return userFromDatabase;
	}

	public void setUserFromDatabase(User userFromDatabase) {
		this.userFromDatabase = userFromDatabase;
	}

	/**
	 * @param email
	 * @return boolean
	 */
	public boolean checkEmailValidity(String email) {

		EmailValidator emailValidator = new EmailValidator();

		if (!emailValidator.validate(email)) {
			return false;
		} else {
			return true;
		}

	}

	// check if email is signed up already

	// check if email sign up has already been confirmed (true)
	public boolean issignConfirmedAlready(Signup signup) {

		if (signup.isConfirmed()) {
			return true;
		} else {
			return false;
		}

	}

	// check password match
	public boolean isPasswordMatching(String plainPassword, User userFromDB) {
		if (BCrypt.checkpw(plainPassword, userFromDB.getPassword())) {
			return true;
		} else {
			return false;
		}
	}

	// match the random number
	public boolean isRandomNumberMatching(int randomNumberPassed, int randomNumberFromDB) {

		if (String.valueOf(randomNumberPassed).equalsIgnoreCase(String.valueOf(randomNumberFromDB))) {
			return true;
		} else {
			return false;
		}

	}

	// confirm/save

	// login (after confirmation, straight away login the user in)

}
