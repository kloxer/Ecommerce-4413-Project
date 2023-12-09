package model;

import java.io.Serializable;

public class UserPaymentMethod implements Serializable {

	private static final long serialVersionUID = 1L;
	private int UPM_ID;
    private int user_id;
    private int CVV;
    private String cardProvider;
    private String cardNumber;
    private int exp_year;
    private int exp_month;

    public UserPaymentMethod() {
    }

    public int getUPM_ID() {
        return UPM_ID;
    }

    public void setUPM_ID(int UPM_ID) {
        this.UPM_ID = UPM_ID;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }

    public String getCardProvider() {
        return cardProvider;
    }

    public void setCardProvider(String cardProvider) {
        this.cardProvider = cardProvider;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getExp_year() {
        return exp_year;
    }

    public void setExp_year(int exp_year) {
        this.exp_year = exp_year;
    }

    public int getExp_month() {
        return exp_month;
    }

    public void setExp_month(int exp_month) {
        this.exp_month = exp_month;
    }
}
