package com.vivek.vaccnow.dto;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.vivek.vaccnow.enums.PaymentMethods;

/**
 * The Class ScheduleAppointmentDto.
 */
public class ScheduleAppointmentDto {

	/** The user name. */
	@NotNull
	private String userName;

	/** The branch id. */
	@NotNull
	private long branchId;

	/** The date. */
	@NotNull
	@Future
	private Date date;

	/** The hour. */
	@NotNull
	@Min(value = 0)
	@Max(value = 23)
	private int hour;

	/** The minute. */
	@NotNull
	@Min(value = 0)
	@Max(value = 45)
	private int minute;

	/** The payment method. */
	@NotNull
	private PaymentMethods paymentMethod;

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
	 * Gets the branch id.
	 *
	 * @return the branch id
	 */
	public long getBranchId() {
		return branchId;
	}

	/**
	 * Sets the branch id.
	 *
	 * @param branchId the new branch id
	 */
	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the hour.
	 *
	 * @return the hour
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * Sets the hour.
	 *
	 * @param hour the new hour
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}

	/**
	 * Gets the minute.
	 *
	 * @return the minute
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * Sets the minute.
	 *
	 * @param minute the new minute
	 */
	public void setMinute(int minute) {
		this.minute = minute;
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

}
