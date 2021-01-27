package com.vivek.vaccnow.dto;

import java.time.LocalDateTime;

/**
 * The Class ConfirmedVaccinationDTO.
 */
public class ConfirmedVaccinationDTO {

	/** The user name. */
	private String userName;

	/** The branch name. */
	private String branchName;

	/** The date and time confirmed. */
	private LocalDateTime dateAndTimeConfirmed;

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
	 * Gets the branch name.
	 *
	 * @return the branch name
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * Sets the branch name.
	 *
	 * @param branchName the new branch name
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	/**
	 * Gets the date and time confirmed.
	 *
	 * @return the date and time confirmed
	 */
	public LocalDateTime getDateAndTimeConfirmed() {
		return dateAndTimeConfirmed;
	}

	/**
	 * Sets the date and time confirmed.
	 *
	 * @param dateAndTimeConfirmed the new date and time confirmed
	 */
	public void setDateAndTimeConfirmed(LocalDateTime dateAndTimeConfirmed) {
		this.dateAndTimeConfirmed = dateAndTimeConfirmed;
	}

}