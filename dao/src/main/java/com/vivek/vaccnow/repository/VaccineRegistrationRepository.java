package com.vivek.vaccnow.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vivek.vaccnow.enums.Status;
import com.vivek.vaccnow.models.VaccineRegisteration;
import com.vivek.vaccnow.models.VaccineRegisterationId;

/**
 * The Interface VaccineRegistrationRepository.
 */
@Repository
public interface VaccineRegistrationRepository extends JpaRepository<VaccineRegisteration, VaccineRegisterationId> {

	/**
	 * Find by branch.
	 *
	 * @param branchId the branch id
	 * @param pageable the pageable
	 * @return the page
	 */
	@Query(value = "select vr.* from Vaccine_Registration vr inner join Vaccination_Timeslot vt on vr.time_slot_id = vt.id inner join Branch_Vaccine_Inventory bvi on vt.branch_vacc_id = bvi.id and bvi.branch_id =:branchId and vr.status='Vaccinated'", nativeQuery = true)
	public Page<VaccineRegisteration> findByBranch(@Param("branchId") Long branchId, Pageable pageable);

	/**
	 * Find by period.
	 *
	 * @param startTime the start time
	 * @param endTime the end time
	 * @param pageable the pageable
	 * @return the page
	 */
	@Query(value = "select vr.* from Vaccine_Registration vr inner join Vaccination_Timeslot vt on vr.time_slot_id = vt.id and vr.status=:status and vt.start_time >:startTime and vt.end_time <:endTime", nativeQuery = true)
	public Page<VaccineRegisteration> findByPeriod(@Param("startTime") LocalDateTime startTime,
			@Param("endTime") LocalDateTime endTime, @Param("status") String status, Pageable pageable);
}
