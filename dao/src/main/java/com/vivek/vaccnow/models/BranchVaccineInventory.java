package com.vivek.vaccnow.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The Class BranchVaccine.
 */
@Entity
@Table(name = "Branch_Vaccine_Inventory")
public class BranchVaccineInventory implements Serializable {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The user id. */
	@Id
	@Column(name = "id")
	private Long id;

	/** The branch name. */
	@Column(name = "vaccine_in_stock")
	private int vaccineInStock;

	/** The vaccine. */
	@ManyToOne
	@JoinColumn(name = "vacc_id")
	private Vaccine vaccine;

	/** The branch. */
	@ManyToOne
	@JoinColumn(name = "branch_id")
	private Branch branch;

	/** The registerations. */
	@OneToMany(mappedBy = "branchVaccine", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<VaccinationTimeSlot> timeSlots;

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
	 * Gets the vaccine in stock.
	 *
	 * @return the vaccine in stock
	 */
	public int getVaccineInStock() {
		return vaccineInStock;
	}

	/**
	 * Sets the vaccine in stock.
	 *
	 * @param vaccineInStock the new vaccine in stock
	 */
	public void setVaccineInStock(int vaccineInStock) {
		this.vaccineInStock = vaccineInStock;
	}

	/**
	 * Gets the vaccine.
	 *
	 * @return the vaccine
	 */
	public Vaccine getVaccine() {
		return vaccine;
	}

	/**
	 * Sets the vaccine.
	 *
	 * @param vaccine the new vaccine
	 */
	public void setVaccine(Vaccine vaccine) {
		this.vaccine = vaccine;
	}

	/**
	 * Gets the branch.
	 *
	 * @return the branch
	 */
	public Branch getBranch() {
		return branch;
	}

	/**
	 * Sets the branch.
	 *
	 * @param branch the new branch
	 */
	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	/**
	 * Gets the time slots.
	 *
	 * @return the time slots
	 */
	public Set<VaccinationTimeSlot> getTimeSlots() {
		return timeSlots;
	}

	/**
	 * Sets the time slots.
	 *
	 * @param timeSlots the new time slots
	 */
	public void setTimeSlots(Set<VaccinationTimeSlot> timeSlots) {
		this.timeSlots = timeSlots;
	}

}
