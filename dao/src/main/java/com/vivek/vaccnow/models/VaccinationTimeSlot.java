package com.vivek.vaccnow.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The Class BranchVaccine.
 */
@Entity
@Table(name = "Vaccination_Timeslot")
public class VaccinationTimeSlot implements Serializable {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The user id. */
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "seqVaccTimeSlotIdId", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seqVaccTimeSlotIdId", sequenceName = "SEQ_Vaccination_Timeslot_ID", allocationSize = 1)
	private Long id;

	/** The start date time. */
	@Column(name = "start_time")
	private LocalDateTime startDateTime;

	/** The end date time. */
	@Column(name = "end_time")
	private LocalDateTime endDateTime;

	/** The branch name. */
	@Column(name = "capacity")
	private int capacity;

	/** The branch vaccine. */
	@ManyToOne
	@JoinColumn(name = "branch_vacc_id", referencedColumnName = "id")
	private BranchVaccineInventory branchVaccine;

	/** The registerations. */
	@OneToMany(mappedBy = "vaccinationTimeSlot", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<VaccineRegisteration> registerations;

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
	 * Gets the capacity.
	 *
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Sets the capacity.
	 *
	 * @param capacity the new capacity
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * Gets the branch vaccine.
	 *
	 * @return the branch vaccine
	 */
	public BranchVaccineInventory getBranchVaccine() {
		return branchVaccine;
	}

	/**
	 * Sets the branch vaccine.
	 *
	 * @param branchVaccine the new branch vaccine
	 */
	public void setBranchVaccine(BranchVaccineInventory branchVaccine) {
		this.branchVaccine = branchVaccine;
	}

	/**
	 * Gets the registerations.
	 *
	 * @return the registerations
	 */
	public Set<VaccineRegisteration> getRegisterations() {
		return registerations;
	}

	/**
	 * Sets the registerations.
	 *
	 * @param registerations the new registerations
	 */
	public void setRegisterations(Set<VaccineRegisteration> registerations) {
		this.registerations = registerations;
	}

}
