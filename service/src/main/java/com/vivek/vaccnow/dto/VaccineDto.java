package com.vivek.vaccnow.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * The Class VaccineDto.
 */
@ApiModel(description = "An individual Vaccine")
public class VaccineDto {

	/** The vaccine id. */
	@ApiModelProperty("Vaccine Id")
	private Long vaccineId;

	/** The vaccine name. */
	@ApiModelProperty("Vaccine Name")
	private String vaccineName;

	/**
	 * Gets the vaccine id.
	 *
	 * @return the vaccine id
	 */
	public Long getVaccineId() {
		return vaccineId;
	}

	/**
	 * Sets the vaccine id.
	 *
	 * @param vaccineId the new vaccine id
	 */
	public void setVaccineId(Long vaccineId) {
		this.vaccineId = vaccineId;
	}

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
