package com.vivek.vaccnow.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vivek.vaccnow.dto.AppliedVaccinationDTO;
import com.vivek.vaccnow.dto.ConfirmedVaccinationDTO;
import com.vivek.vaccnow.enums.Status;
import com.vivek.vaccnow.models.VaccineRegisteration;
import com.vivek.vaccnow.repository.VaccineRegistrationRepository;
import com.vivek.vaccnow.service.ReportService;

/**
 * The Class ReportServiceImpl.
 */
@Service
public class ReportServiceImpl implements ReportService {

	/** The vaccine registration repository. */
	@Autowired
	VaccineRegistrationRepository vaccineRegistrationRepository;

	/** The converter. */
	@Autowired
	private ConversionService converter;

	/**
	 * Gets the all applied vaccinations for branch.
	 *
	 * @param branchId the branch id
	 * @param pageable the pageable
	 * @return the all applied vaccinations for branch
	 */
	@Override
	public Page<AppliedVaccinationDTO> getAllAppliedVaccinationsForBranch(Long branchId, Pageable pageable) {
		Page<VaccineRegisteration> appliedVaccines = vaccineRegistrationRepository.findByBranch(branchId, pageable);
		List<AppliedVaccinationDTO> appliedVaccinesDto = new ArrayList<>();
		if (appliedVaccines.hasContent()) {
			List<VaccineRegisteration> appliedVaccList = appliedVaccines.getContent();
			for (VaccineRegisteration vaccRegi : appliedVaccList) {
				appliedVaccinesDto.add(converter.convert(vaccRegi, AppliedVaccinationDTO.class));
			}
		}
		return new PageImpl<>(appliedVaccinesDto);
	}

	/**
	 * Gets the applied vaccine for specific period.
	 *
	 * @param startDate the start date
	 * @param endDate   the end date
	 * @param pageable  the pageable
	 * @return the applied vaccine for specific period
	 */
	@Override
	public Page<AppliedVaccinationDTO> getAppliedVaccineForSpecificPeriod(LocalDate startDate, LocalDate endDate,
			Pageable pageable) {
		Page<VaccineRegisteration> appliedVaccines = vaccineRegistrationRepository.findByPeriod(
				LocalDateTime.of(startDate, LocalTime.MIDNIGHT), LocalDateTime.of(endDate, LocalTime.MAX),
				Status.Vaccinated.name(), pageable);
		List<AppliedVaccinationDTO> appliedVaccinesDto = new ArrayList<>();
		if (appliedVaccines.hasContent()) {
			List<VaccineRegisteration> appliedVaccList = appliedVaccines.getContent();
			for (VaccineRegisteration vaccRegi : appliedVaccList) {
				appliedVaccinesDto.add(converter.convert(vaccRegi, AppliedVaccinationDTO.class));
			}
		}
		return new PageImpl<>(appliedVaccinesDto);
	}

	/**
	 * Gets the appointments for specific period.
	 *
	 * @param startDate the start date
	 * @param endDate   the end date
	 * @param pageable  the pageable
	 * @return the appointments for specific period
	 */
	@Override
	public Page<ConfirmedVaccinationDTO> getAppointmentsForSpecificPeriod(LocalDate startDate, LocalDate endDate,
			Pageable pageable) {
		Page<VaccineRegisteration> appliedVaccines = vaccineRegistrationRepository.findByPeriod(
				LocalDateTime.of(startDate, LocalTime.MIDNIGHT), LocalDateTime.of(endDate, LocalTime.MAX),
				Status.Registered.name(), pageable);
		List<ConfirmedVaccinationDTO> confirmedVaccinesDto = new ArrayList<>();
		if (appliedVaccines.hasContent()) {
			List<VaccineRegisteration> confirmedVaccList = appliedVaccines.getContent();
			for (VaccineRegisteration vaccRegi : confirmedVaccList) {
				confirmedVaccinesDto.add(converter.convert(vaccRegi, ConfirmedVaccinationDTO.class));
			}
		}
		return new PageImpl<>(confirmedVaccinesDto);
	}

}
