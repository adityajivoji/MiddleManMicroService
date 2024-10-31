package com.middleman.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "person_name")
    private String personName;

    @Column(name = "current_age")
    private Integer currentAge;

    @Column(name = "retirement_age")
    private Integer retirementAge;

    @Column(name = "birth_year")
    private Integer birthYear;

    @Column(name = "birth_month")
    private Integer birthMonth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    @Column(name = "apartment")
    private String apartment;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "per_capita_income")
    private BigDecimal perCapitaIncome;

    @Column(name = "yearly_income")
    private BigDecimal yearlyIncome;

    @Column(name = "total_debt")
    private BigDecimal totalDebt;

    @Column(name = "fico_score")
    private Integer ficoScore;

    @Column(name = "num_credit_cards")
    private Integer numCreditCards;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Integer getCurrentAge() {
		return currentAge;
	}

	public void setCurrentAge(Integer currentAge) {
		this.currentAge = currentAge;
	}

	public Integer getRetirementAge() {
		return retirementAge;
	}

	public void setRetirementAge(Integer retirementAge) {
		this.retirementAge = retirementAge;
	}

	public Integer getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
	}

	public Integer getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(Integer birthMonth) {
		this.birthMonth = birthMonth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public BigDecimal getPerCapitaIncome() {
		return perCapitaIncome;
	}

	public void setPerCapitaIncome(BigDecimal perCapitaIncome) {
		this.perCapitaIncome = perCapitaIncome;
	}

	public BigDecimal getYearlyIncome() {
		return yearlyIncome;
	}

	public void setYearlyIncome(BigDecimal yearlyIncome) {
		this.yearlyIncome = yearlyIncome;
	}

	public BigDecimal getTotalDebt() {
		return totalDebt;
	}

	public void setTotalDebt(BigDecimal totalDebt) {
		this.totalDebt = totalDebt;
	}

	public Integer getFicoScore() {
		return ficoScore;
	}

	public void setFicoScore(Integer ficoScore) {
		this.ficoScore = ficoScore;
	}

	public Integer getNumCreditCards() {
		return numCreditCards;
	}

	public void setNumCreditCards(Integer numCreditCards) {
		this.numCreditCards = numCreditCards;
	}

    // Getters and Setters will be created manually
}
