package com.lazimisha.entity.signup;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sign_up")
public class Signup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private BigDecimal id;

	@Column(name = "date_server", insertable = false, updatable = false)
	// @Temporal(TemporalType.TIMESTAMP)
	private Date dateServer;

	@Basic(optional = false)
	@Column(name = "date_user")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dateUser;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "random_number")
	private BigDecimal randomNumber;

	@Column(name = "confirmed")
	private boolean confirmed;

	@Column(name = "user_id")
	private BigDecimal userId;

	public Signup() {

	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Date getDateServer() {
		return dateServer;
	}

	public void setDateServer(Date dateServer) {
		this.dateServer = dateServer;
	}

	public java.util.Date getDateUser() {
		return dateUser;
	}

	public void setDateUser(java.util.Date dateUser) {
		this.dateUser = dateUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getRandomNumber() {
		return randomNumber;
	}

	public void setRandomNumber(BigDecimal randomNumber) {
		this.randomNumber = randomNumber;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public BigDecimal getUserId() {
		return userId;
	}

	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Signup [id=" + id + ", dateServer=" + dateServer + ", dateUser=" + dateUser + ", email=" + email
				+ ", password=" + password + ", randomNumber=" + randomNumber + ", confirmed=" + confirmed + ", userId="
				+ userId + "]";
	}

}
