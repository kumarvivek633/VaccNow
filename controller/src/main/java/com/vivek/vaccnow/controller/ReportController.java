package com.vivek.vaccnow.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Past;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vivek.vaccnow.dto.AppliedVaccinationDTO;
import com.vivek.vaccnow.dto.ConfirmedVaccinationDTO;
import com.vivek.vaccnow.service.ReportService;

import io.swagger.annotations.ApiOperation;

/**
 * The Class ReportController.
 */
@RestController
@RequestMapping(path = "/api/vaccinations")
public class ReportController {

	/** The report service. */
	@Autowired
	private ReportService reportService;

	/**
	 * Gets the all applied vaccinations.
	 *
	 * @param pageable the pageable
	 * @param branchId the branch id
	 * @return the all applied vaccinations
	 */
	@GetMapping("{branchId}/applied")
	@ApiOperation(value = "Make Appointment")
	public Page<AppliedVaccinationDTO> getAllAppliedVaccinations(
			@PageableDefault(size = 50, page = 0) Pageable pageable, @PathVariable Long branchId) {
		return reportService.getAllAppliedVaccinationsForBranch(branchId, pageable);
	}

	/**
	 * Gets the applied vaccine for specific period.
	 *
	 * @param pageable  the pageable
	 * @param startDate the start date
	 * @param endDate   the end date
	 * @return the applied vaccine for specific period
	 */
	@GetMapping("/applied")
	public Page<AppliedVaccinationDTO> getAppliedVaccineForSpecificPeriod(
			@PageableDefault(size = 50, page = 0) Pageable pageable,
			@Valid @Past @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

		return reportService.getAppliedVaccineForSpecificPeriod(startDate, endDate, pageable);
	}

	/**
	 * Gets the appointments for specific period.
	 *
	 * @param pageable  the pageable
	 * @param startDate the start date
	 * @param endDate   the end date
	 * @return the appointments for specific period
	 */
	@GetMapping("/confirmed")
	public Page<ConfirmedVaccinationDTO> getAppointmentsForSpecificPeriod(
			@PageableDefault(size = 50, page = 0) Pageable pageable,
			@Valid @Future @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

		return reportService.getAppointmentsForSpecificPeriod(startDate, endDate, pageable);
	}

}
