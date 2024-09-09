package com.middleman.entity;

import java.math.BigDecimal;
import java.sql.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
@Entity
@Table(name = "cards", uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"user_id", "card_index"})
	}, indexes = {
	    @Index(name = "idx_user_id_card_index", columnList = "user_id, card_index")
	})
	public class Card {

	    @Id
	    @Column(name = "card_number")
	    private Long cardNumber;

	    @ManyToOne
	    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
	    private User user;

	    @Column(name = "card_index")
	    private Integer cardIndex;

    @Column(name = "card_brand")
    private String cardBrand;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "expires")
    private Date expires;

    @Column(name = "cvv")
    private String cvv;

    @Column(name = "has_chip")
    private Boolean hasChip;

    @Column(name = "cards_issued")
    private Integer cardsIssued;

    @Column(name = "credit_limit")
    private BigDecimal creditLimit;

    @Column(name = "current_balance")
    private BigDecimal currentBalance;

    @Column(name = "acct_open_date")
    private Date acctOpenDate;

    @Column(name = "year_pin_last_changed")
    private Integer yearPinLastChanged;

    @Column(name = "card_on_dark_web")
    private Boolean cardOnDarkWeb;

	public Long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getCardIndex() {
		return cardIndex;
	}

	public void setCardIndex(Integer cardIndex) {
		this.cardIndex = cardIndex;
	}

	public String getCardBrand() {
		return cardBrand;
	}

	public void setCardBrand(String cardBrand) {
		this.cardBrand = cardBrand;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Date getExpires() {
		return expires;
	}

	public void setExpires(Date expires) {
		this.expires = expires;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public Boolean getHasChip() {
		return hasChip;
	}

	public void setHasChip(Boolean hasChip) {
		this.hasChip = hasChip;
	}

	public Integer getCardsIssued() {
		return cardsIssued;
	}

	public void setCardsIssued(Integer cardsIssued) {
		this.cardsIssued = cardsIssued;
	}

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	public Date getAcctOpenDate() {
		return acctOpenDate;
	}

	public void setAcctOpenDate(Date acctOpenDate) {
		this.acctOpenDate = acctOpenDate;
	}

	public Integer getYearPinLastChanged() {
		return yearPinLastChanged;
	}

	public void setYearPinLastChanged(Integer yearPinLastChanged) {
		this.yearPinLastChanged = yearPinLastChanged;
	}

	public Boolean getCardOnDarkWeb() {
		return cardOnDarkWeb;
	}

	public void setCardOnDarkWeb(Boolean cardOnDarkWeb) {
		this.cardOnDarkWeb = cardOnDarkWeb;
	}

   
    
}
