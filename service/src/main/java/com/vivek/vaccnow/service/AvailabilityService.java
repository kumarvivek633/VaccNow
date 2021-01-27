package com.vivek.vaccnow.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vivek.vaccnow.dto.BranchDto;
import com.vivek.vaccnow.dto.TimeSlotsDto;
import com.vivek.vaccnow.dto.VaccineDto;
import com.vivek.vaccnow.enums.AvailabilityStatus;

/**
 * The Interface AvailabilityService.
 */
public interface AvailabilityService {

	/**
	 * Gets the all branch.
	 *
	 * @param pageable the pageable
	 * @return the all branch
	 */
	public Page<BranchDto> getAllBranch(Pageable pageable);

	/**
	 * Gets the vaccines by branch.
	 *
	 * @param branchId the branch id
	 * @return the vaccines by branch
	 */
	public List<VaccineDto> getVaccinesByBranch(Long branchId);
	
	/**
	 * Check availability.
	 *
	 * @param branchId the branch id
	 * @param date the date
	 * @param hour the hour
	 * @param min the min
	 * @return the availability status
	 */
	public AvailabilityStatus checkAvailability(Long branchId, LocalDate date, int hour, int min);
	
	/**
	 * Gets the available time slots.
	 *
	 * @param branchId the branch id
	 * @param date the date
	 * @return the available time slots
	 */
	public List<TimeSlotsDto> getAvailableTimeSlots(Long branchId, LocalDate date);
}
