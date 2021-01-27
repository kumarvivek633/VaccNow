package com.vivek.vaccnow.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Future;

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

import com.vivek.vaccnow.dto.BranchDto;
import com.vivek.vaccnow.dto.TimeSlotsDto;
import com.vivek.vaccnow.dto.VaccineDto;
import com.vivek.vaccnow.enums.AvailabilityStatus;
import com.vivek.vaccnow.service.AvailabilityService;

import io.swagger.annotations.ApiOperation;

/**
 * The Class BranchAvailabilityController.
 */
@RestController
@RequestMapping(path = "/api/branches")
public class AvailabilityController {

	/** The branch service. */
	@Autowired
	private AvailabilityService availabikityService;

	/**
	 * Gets the all branches.
	 *
	 * @param pageable the pageable
	 * @return the all branches
	 */
	@GetMapping
	@ApiOperation(value = "Returns Branch Data")
	public Page<BranchDto> getAllBranches(@PageableDefault(size = 50, page = 0) Pageable pageable) {
		return availabikityService.getAllBranch(pageable);
	}

	/**
	 * Gets the vaccines of branch.
	 *
	 * @param branchId the branch id
	 * @return the vaccines of branch
	 */
	@GetMapping("/{branchId}/vaccines")
	@ApiOperation(value = "Returns Vaccines of the Branch ")
	public List<VaccineDto> getVaccinesOfBranch(@PathVariable Long branchId) {
		return availabikityService.getVaccinesByBranch(branchId);
	}

	/**
	 * Gets the availability by branch.
	 *
	 * @param branchId the branch id
	 * @param date     the date
	 * @param hour     the hour
	 * @param minute   the minute
	 * @return the availability by branch
	 */
	@GetMapping("/{branchId}/availability")
	@ApiOperation(value = "Return the availability in the barnch for specific time")
	public AvailabilityStatus getAvailabilityByBranch(@PathVariable Long branchId,
			@Valid @Future @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
			@RequestParam int hour, @RequestParam int minute) {
		return availabikityService.checkAvailability(branchId, date, hour, minute);
	}

	/**
	 * Gets the availability in branch for A day.
	 *
	 * @param branchId the branch id
	 * @param date the date
	 * @return the availability in branch for A day
	 */
	@GetMapping("/{branchId}/availability/all")
	@ApiOperation(value = "Return the availability in the barnch for specific Date")
	public List<TimeSlotsDto> getAvailabilityInBranchForADay(@PathVariable Long branchId,
			@Valid @Future @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		return availabikityService.getAvailableTimeSlots(branchId, date);
	}
}
