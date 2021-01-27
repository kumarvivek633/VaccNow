package com.vivek.vaccnow.models;

import java.io.Serializable;

/**
 * The Class VaccineReservedId.
 */
public class VaccineRegisterationId implements Serializable {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The user name. */
	private String userName;

	/** The time slot id. */
	private Long timeSlotId;

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

}
