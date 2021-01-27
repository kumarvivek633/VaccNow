package com.vivek.vaccnow.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vivek.vaccnow.models.BranchVaccineInventory;

@Repository
public interface BranchVacinationInventoryRepository extends JpaRepository<BranchVaccineInventory, Long> {
	
	@Query(value = "select bvi.* from  Branch_Vaccine_Inventory bvi left join  Vaccination_Timeslot v on v.branch_vacc_id = bvi.id  and v.start_time >:startTime and v.end_time <:endTime where bvi.branch_id =:branchId", nativeQuery =  true)
	public List<BranchVaccineInventory> findByBranch(@Param("branchId") Long branchId,
			@Param("startTime") LocalDateTime startDate, @Param("endTime") LocalDateTime endDate);
}
