package com.vivek.vaccnow.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vivek.vaccnow.models.Branch;
import com.vivek.vaccnow.models.VaccinationTimeSlot;

/**
 * The Interface VaccinationTimeSlotRepository.
 */
@Repository
public interface VaccinationTimeSlotRepository extends JpaRepository<VaccinationTimeSlot, Long> {
	
	/**
	 * Check bookins for the specified time in branch.
	 *
	 * @param branchIds the branch ids
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the long
	 */
	@Query(value = "select count(1) from Vaccination_Timeslot v inner join Branch_Vaccine_Inventory bvi on v.branch_vacc_id = bvi.id and bvi.branch_id =:branchId and v.start_time =:startTime and v.end_time =:endTime and v.capacity > 0", nativeQuery = true)
	long checkBookinsForTheSpecifiedTimeInBranch(@Param("branchId") Long branchId,
			@Param("startTime") LocalDateTime startDate, @Param("endTime") LocalDateTime endDate);
	
	@Query(value = "select v.* from Vaccination_Timeslot v inner join Branch_Vaccine_Inventory bvi on v.branch_vacc_id = bvi.id and bvi.branch_id =:branchId and v.start_time >:startTime and v.end_time <:endTime and v.capacity > 0", nativeQuery = true)
	List<VaccinationTimeSlot> getAvailableTimeSlotsForDateAndBranch(@Param("branchId") Long branchId,
			@Param("startTime") LocalDateTime startDate, @Param("endTime") LocalDateTime endDate);
	
	@Query(value = "select count(1) from Vaccination_Timeslot v inner join Branch_Vaccine_Inventory bvi on v.branch_vacc_id = bvi.id and bvi.branch_id =:branchId and v.start_time >:startTime and v.end_time <:endTime", nativeQuery = true)
	long findByBranchVaccinesBranch(@Param("branchId") Long branchId,
			@Param("startTime") LocalDateTime startDate, @Param("endTime") LocalDateTime endDate);
}
