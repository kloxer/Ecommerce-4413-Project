package model;

import java.io.Serializable;

public class UserPaymentMethod implements Serializable{

	private static final long serialVersionUID = 1L;
	private int upmID;
	private int userID;
	private String paymentTypeID;
	private String cardProvider;
	private int accountNumber;
	private String expiryDate;
	
	public UserPaymentMethod() {
		
	}

	public int getUpmID() {
		return upmID;
	}

	public void setUpmID(int upmID) {
		this.upmID = upmID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getPaymentTypeID() {
		return paymentTypeID;
	}

	public void setPaymentTypeID(String paymentTypeID) {
		this.paymentTypeID = paymentTypeID;
	}

	public String getCardProvider() {
		return cardProvider;
	}

	public void setCardProvider(String cardProvider) {
		this.cardProvider = cardProvider;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}


	
}