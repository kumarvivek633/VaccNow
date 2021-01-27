package com.vivek.vaccnow.dto;

import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * The Class BranchDto.
 */
@ApiModel(description = "An individual TimeSlot")
public class TimeSlotsDto {

	/** The start date time. */
	@ApiModelProperty("Start Date")
	private LocalDateTime startDateTime;

	/** The end date time. */
	@ApiModelProperty("End Date")
	private LocalDateTime endDateTime;

	/** The vaccine name. */
	private String vaccineName;

	/**
	 * Gets the start date time.
	 *
	 * @return the start date time
	 */
	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	/**
	 * Sets the start date time.
	 *
	 * @param startDateTime the new start date time
	 */
	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	/**
	 * Gets the end date time.
	 *
	 * @return the end date time
	 */
	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}

	/**
	 * Sets the end date time.
	 *
	 * @param endDateTime the new end date time
	 */
	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
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
