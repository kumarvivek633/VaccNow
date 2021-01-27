package com.vivek.vaccnow.dto;

/**
 * The Class AppliedVaccinationDTO.
 */
public class AppliedVaccinationDTO extends ConfirmedVaccinationDTO {

	/** The vaccine name. */
	private String vaccineName;

	/**
	 * Gets the vaccine name.
	 *
	 * @return the vaccine name
	 */
	public String getVaccineName() {
		return vaccineName;
	}

	/**
	 * Sets the vaccine name.
	 *
	 * @param vaccineName the new vaccine name
	 */
	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

}
