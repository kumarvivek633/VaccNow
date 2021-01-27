package com.vivek.vaccnow.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * The Class BranchDto.
 */
@ApiModel(description = "An individual branch")
public class BranchDto {

	/** The branch id. */
	@ApiModelProperty("Branch Id")
	private Long branchId;

	/** The branch name. */
	@ApiModelProperty("Branch Id")
	private String branchName;
	
	/** The available vaccines. */
	@ApiModelProperty("All the Vaccines in this Branch")
	private List<VaccineDto> availableVaccines;

	/**
	 * Gets the branch id.
	 *
	 * @return the branch id
	 */
	public Long getBranchId() {
		return branchId;
	}

	/**
	 * Sets the branch id.
	 *
	 * @param branchId the new branch id
	 */
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
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
	 * Gets the available vaccines.
	 *
	 * @return the available vaccines
	 */
	public List<VaccineDto> getAvailableVaccines() {
		return availableVaccines;
	}

	/**
	 * Sets the available vaccines.
	 *
	 * @param availableVaccines the new available vaccines
	 */
	public void setAvailableVaccines(List<VaccineDto> availableVaccines) {
		this.availableVaccines = availableVaccines;
	}

}
