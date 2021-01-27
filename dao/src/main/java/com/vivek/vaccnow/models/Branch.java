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
import javax.persistence.Transient;

/**
 * The Class User.
 */
@Entity
@Table(name = "Branch")
public class Branch implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The user id. */
	@Id
	@Column(name = "id")
	private Long id;

	/** The branch name. */
	@Column(name = "branch_name")
	private String branchName;

	/** The branch vaccines. */
	@OneToMany(mappedBy = "branch", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<BranchVaccineInventory> branchVaccines;

	/** The get vaccine. */
	@Transient
	private boolean getVaccine = false;

	/**
	 * Instantiates a new branch.
	 */
	public Branch() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new branch.
	 *
	 * @param id the id
	 */
	public Branch(Long id) {
		super();
		this.id = id;
	}

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

	/**
	 * Checks if is gets the vaccine.
	 *
	 * @return true, if is gets the vaccine
	 */
	public boolean isGetVaccine() {
		return getVaccine;
	}

	/**
	 * Sets the gets the vaccine.
	 *
	 * @param getVaccine the new gets the vaccine
	 */
	public void setGetVaccine(boolean getVaccine) {
		this.getVaccine = getVaccine;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Branch other = (Branch) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
