package com.vivek.vaccnow.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The Class User.
 */
@Entity
@Table(name = "Vaccines")
public class Vaccine implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The user id. */
	@Id
	@Column(name = "id")
	private Long id;

	/** The user first name. */
	@Column(name = "vaccine_name")
	private String vaccineName;

	/** The branch vaccines. */
	@OneToMany(mappedBy = "vaccine", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<BranchVaccineInventory> branchVaccines;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
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

	/**
	 * Gets the branch vaccines.
	 *
	 * @return the branch vaccines
	 */
	public Set<BranchVaccineInventory> getBranchVaccines() {
		return branchVaccines;
	}

	/**
	 * Sets the branch vaccines.
	 *
	 * @param branchVaccines the new branch vaccines
	 */
	public void setBranchVaccines(Set<BranchVaccineInventory> branchVaccines) {
		this.branchVaccines = branchVaccines;
	}

}
