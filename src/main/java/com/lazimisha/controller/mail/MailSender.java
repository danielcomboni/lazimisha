package com.lazimisha.controller.mail;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailSender {

	public static void send(String to, String subject, String body, JavaMailSender javaMailSender) {
		System.out.println("sending...");
		SimpleMailMessage message = new SimpleMailMessage();

		try {

			message.setSubject(subject);
			message.setText(body);
			message.setTo(to);
			javaMailSender.send(message);
			System.out.println("email presumably sent...");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// random number sent to the email to use for confirmation
	public static BigDecimal randomNumberForSignup() {

		int randNum = 0;

		try {
			Random rand = SecureRandom.getInstanceStrong();
			randNum = rand.nextInt(25000000);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// convert the integer to string
		String intStr = String.valueOf(randNum);

		// check the number of characters in the string
		int len = intStr.length();

		if (len < 8 || len > 8) {
			return randomNumberForSignup();
		}

		return new BigDecimal(randNum);
	}

}
