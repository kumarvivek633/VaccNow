package com.vivek.vaccnow.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vivek.vaccnow.dto.AppliedVaccinationDTO;
import com.vivek.vaccnow.dto.ConfirmedVaccinationDTO;

/**
 * The Interface ReportService.
 */
public interface ReportService {

	/**
	 * Gets the all applied vaccinations for branch.
	 *
	 * @param branchId the branch id
	 * @param pageable the pageable
	 * @return the all applied vaccinations for branch
	 */
	public Page<AppliedVaccinationDTO> getAllAppliedVaccinationsForBranch(Long branchId, Pageable pageable);

	/**
	 * Gets the applied vaccine for specific period.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param pageable the pageable
	 * @return the applied vaccine for specific period
	 */
	public Page<AppliedVaccinationDTO> getAppliedVaccineForSpecificPeriod(LocalDate startDate,
			LocalDate endDate, Pageable pageable);

	/**
	 * Gets the appointments for specific period.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param pageable the pageable
	 * @return the appointments for specific period
	 */
	public Page<ConfirmedVaccinationDTO> getAppointmentsForSpecificPeriod(LocalDate startDate,
			LocalDate endDate, Pageable pageable);
}
