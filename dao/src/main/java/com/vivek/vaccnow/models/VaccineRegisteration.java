package com.vivek.vaccnow.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vivek.vaccnow.enums.PaymentMethods;
import com.vivek.vaccnow.enums.Status;

/**
 * The Class VaccineRegisteration.
 */
@Entity
@Table(name = "Vaccine_Registration")
@IdClass(VaccineRegisterationId.class)
public class VaccineRegisteration implements Serializable {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The user id. */
	@Id
	@Column(name = "user_name")
	private String userName;

	/** The branch vacc. */
	@Id
	@Column(name = "time_slot_id")
	private Long timeSlotId;

	/** The status. */
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private Status status;

	/** The payment method. */
	@Column(name = "payment_mode")
	@Enumerated(EnumType.STRING)
	private PaymentMethods paymentMethod;

	/** The branch vaccine. */
	@ManyToOne
	@JoinColumn(name = "time_slot_id", referencedColumnName = "id", insertable = false, updatable = false)
	private VaccinationTimeSlot vaccinationTimeSlot;

	/** The user. */
	@ManyToOne
	@JoinColumn(name = "user_name", insertable = false, updatable = false)
	private User user;

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}


	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * Gets the payment method.
	 *
	 * @return the payment method
	 */
	public PaymentMethods getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * Sets the payment method.
	 *
	 * @param paymentMethod the new payment method
	 */
	public void setPaymentMethod(PaymentMethods paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the time slot id.
	 *
	 * @return the time slot id
	 */
	public Long getTimeSlotId() {
		return timeSlotId;
	}

	/**
	 * Sets the time slot id.
	 *
	 * @param timeSlotId the new time slot id
	 */
	public void setTimeSlotId(Long timeSlotId) {
		this.timeSlotId = timeSlotId;
	}

	/**
	 * Gets the vaccination time slot.
	 *
	 * @return the vaccination time slot
	 */
	public VaccinationTimeSlot getVaccinationTimeSlot() {
		return vaccinationTimeSlot;
	}

	/**
	 * Sets the vaccination time slot.
	 *
	 * @param vaccinationTimeSlot the new vaccination time slot
	 */
	public void setVaccinationTimeSlot(VaccinationTimeSlot vaccinationTimeSlot) {
		this.vaccinationTimeSlot = vaccinationTimeSlot;
	}

}
