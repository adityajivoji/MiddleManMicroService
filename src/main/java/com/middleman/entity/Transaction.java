package com.middleman.entity;

import java.math.BigDecimal;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @Column(name = "transaction_id")
    private Long transactionId;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
        @JoinColumn(name = "card_index", referencedColumnName = "card_index")
    })
    private Card card;
    

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	@Column(name = "transaction_year")
    private Integer transactionYear;

    @Column(name = "transaction_month")
    private Integer transactionMonth;

    @Column(name = "transaction_day")
    private Integer transactionDay;

    @Column(name = "transaction_time")
    private Time transactionTime;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "use_chip")
    private Boolean useChip;

    @Column(name = "merchant_name")
    private String merchantName;

    @Column(name = "merchant_city")
    private String merchantCity;

    @Column(name = "merchant_state")
    private String merchantState;

    @Column(name = "merchant_zip")
    private String merchantZip;

    @Column(name = "mcc")
    private String mcc;  // Merchant Category Code

    @Column(name = "errors")
    private String errors;

    @Column(name = "is_fraud")
    private Boolean isFraud;
    
    @Column(name = "status")
    private String status;
    
    public Transaction() {
    	

        transactionYear= null;

        transactionMonth= null;

        transactionDay= null;

        transactionTime= null;

        amount= null;

        useChip= null;

        merchantName= null;

        merchantCity= null;

        merchantState= null;

        merchantZip= null;

        mcc= null; 

        errors= null;

        isFraud= false;
    }

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getTransactionYear() {
		return transactionYear;
	}

	public void setTransactionYear(Integer transactionYear) {
		this.transactionYear = transactionYear;
	}

	public Integer getTransactionMonth() {
		return transactionMonth;
	}

	public void setTransactionMonth(Integer transactionMonth) {
		this.transactionMonth = transactionMonth;
	}

	public Integer getTransactionDay() {
		return transactionDay;
	}

	public void setTransactionDay(Integer transactionDay) {
		this.transactionDay = transactionDay;
	}

	public Time getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Time transactionTime) {
		this.transactionTime = transactionTime;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Boolean getUseChip() {
		return useChip;
	}

	public void setUseChip(Boolean useChip) {
		this.useChip = useChip;
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

	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

	public Boolean getIsFraud() {
		return isFraud;
	}

	public void setIsFraud(Boolean isFraud) {
		this.isFraud = isFraud;
	}

    // Getters and Setters will be created manually
    
}
