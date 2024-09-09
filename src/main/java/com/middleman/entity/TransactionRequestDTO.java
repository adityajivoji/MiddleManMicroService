package com.middleman.entity;

import java.math.BigDecimal;

// Online
public class TransactionRequestDTO {
    private Long cardNumber;
    private String cvv;
    private String expiryDate;
    private BigDecimal amount;
    private String merchantName;
    private String merchantCity;
    private String merchantState;
    private String merchantZip;
	public Long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getMerchantCity() {
		return merchantCity;
	}
	public void setMerchantCity(String merchantCity) {
		this.merchantCity = merchantCity;
	}
	public String getMerchantState() {
		return merchantState;
	}
	public void setMerchantState(String merchantState) {
		this.merchantState = merchantState;
	}
	public String getMerchantZip() {
		return merchantZip;
	}
	public void setMerchantZip(String merchantZip) {
		this.merchantZip = merchantZip;
	}

    // Getters and Setters
}
