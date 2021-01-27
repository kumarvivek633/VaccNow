package com.vivek.vaccnow.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.vivek.vaccnow.dto.BranchDto;
import com.vivek.vaccnow.dto.TimeSlotsDto;
import com.vivek.vaccnow.dto.VaccineDto;
import com.vivek.vaccnow.enums.AvailabilityStatus;
import com.vivek.vaccnow.exceptions.BranchNotFoundException;
import com.vivek.vaccnow.models.Branch;
import com.vivek.vaccnow.models.BranchVaccineInventory;
import com.vivek.vaccnow.models.VaccinationTimeSlot;
import com.vivek.vaccnow.repository.BranchRepository;
import com.vivek.vaccnow.repository.BranchVacinationInventoryRepository;
import com.vivek.vaccnow.repository.VaccinationTimeSlotRepository;
import com.vivek.vaccnow.service.AvailabilityService;

/**
 * The Class AvailablityServiceImpl.
 */
@Service
public class AvailablityServiceImpl implements AvailabilityService {

	/** The branch repository. */
	@Autowired
	private BranchRepository branchRepository;

	/** The vaccination time slot repository. */
	@Autowired
	private VaccinationTimeSlotRepository vaccinationTimeSlotRepository;

	@Autowired
	private BranchVacinationInventoryRepository branchVacinationInventoryRepository;

	/** The converter. */
	@Autowired
	private ConversionService converter;

	/** The reservation windows. */
	@Value("${vaccination.booking.window}")
	private Long reservationWindows;

	/**
	 * Gets the all branch.
	 *
	 * @param pageable the pageable
	 * @return the all branch
	 */
	public Page<BranchDto> getAllBranch(Pageable pageable) {
		Page<Branch> branches = branchRepository.findAll(pageable);
		List<BranchDto> branchDtos = new ArrayList<>();
		if (branches.hasContent()) {
			List<Branch> branchList = branches.getContent();
			for (Branch branch : branchList) {
				branchDtos.add(converter.convert(branch, BranchDto.class));
			}
		}
		return new PageImpl<>(branchDtos);
	}

	/**
	 * Gets the vaccines by branch.
	 *
	 * @param branchId the branch id
	 * @return the vaccines by branch
	 */
	@Override
	public List<VaccineDto> getVaccinesByBranch(Long branchId) {
		Branch branch = branchRepository.findById(branchId).orElseThrow(() -> new BranchNotFoundException());
		BranchDto branchDto = converter.convert(branch, BranchDto.class);
		return branchDto.getAvailableVaccines();
	}

	/**
	 * Check availability.
	 *
	 * @param branchId the branch id
	 * @param date     the date
	 * @param hour     the hour
	 * @param min      the min
	 * @return the availability status
	 */
	@Override
	@Transactional
	public AvailabilityStatus checkAvailability(Long branchId, LocalDate date, int hour, int min) {
		AvailabilityStatus status = AvailabilityStatus.UN_AVAILABLE;
		LocalDateTime startDate = LocalDateTime.of(date, LocalTime.of(hour, min));
		initialiseTimeSlotDataIfNotPresent(branchId, date);
		long noOfTimeSlotsAvailable = vaccinationTimeSlotRepository.checkBookinsForTheSpecifiedTimeInBranch(branchId,
				startDate, startDate.plusMinutes(reservationWindows));
		if (noOfTimeSlotsAvailable > 0) {
			status = AvailabilityStatus.AVAILABLE;
		}
		return status;
	}

	/**
	 * Initialise time slot data if not present.
	 *
	 * @param branchId the branch id
	 * @param date     the date
	 */
	private void initialiseTimeSlotDataIfNotPresent(Long branchId, LocalDate date) {
		Long availaleCount = vaccinationTimeSlotRepository.findByBranchVaccinesBranch(branchId, LocalDateTime.of(date, LocalTime.MIDNIGHT), LocalDateTime.of(date, LocalTime.MAX));
		if (availaleCount <= 0) {
			List<BranchVaccineInventory> branchVaccineInventories = branchVacinationInventoryRepository
					.findByBranch(branchId, LocalDateTime.of(date, LocalTime.MIDNIGHT), LocalDateTime.of(date, LocalTime.MAX));
			for (BranchVaccineInventory branchVaccineInventory : branchVaccineInventories) {
				if (branchVaccineInventory.getBranch().getId().equals(branchId)) {
					Set<VaccinationTimeSlot> timeSlots = new HashSet<VaccinationTimeSlot>();
					for (int hours = 0; hours < 24; hours++) {
						for (int minute = 0; minute < 59; minute = minute + 15) {
							VaccinationTimeSlot timeSlot = new VaccinationTimeSlot();
							timeSlot.setCapacity(10);
							timeSlot.setStartDateTime(LocalDateTime.of(date, LocalTime.of(hours, minute)));
							timeSlot.setEndDateTime(
									LocalDateTime.of(date, LocalTime.of(hours, minute).plusMinutes(15)));
							timeSlot.setBranchVaccine(branchVaccineInventory);
							timeSlots.add(timeSlot);
						}
					}
					branchVaccineInventory.setTimeSlots(timeSlots);
					branchVacinationInventoryRepository.save(branchVaccineInventory);
				}
			}
		}
	}

	/**
	 * Gets the available time slots.
	 *
	 * @param branchId the branch id
	 * @param date     the date
	 * @return the available time slots
	 */
	@Override
	@Transactional
	public List<TimeSlotsDto> getAvailableTimeSlots(Long branchId, LocalDate date) {
		initialiseTimeSlotDataIfNotPresent(branchId, date);
		List<VaccinationTimeSlot> vaccinationTimeSlots = vaccinationTimeSlotRepository
				.getAvailableTimeSlotsForDateAndBranch(branchId, LocalDateTime.of(date, LocalTime.MIDNIGHT),
						LocalDateTime.of(date, LocalTime.MAX));
		List<TimeSlotsDto> availableSlots = new ArrayList<>();
		if (!CollectionUtils.isEmpty(vaccinationTimeSlots)) {
			for (VaccinationTimeSlot vaccinationTimeSlot : vaccinationTimeSlots) {
				availableSlots.add(converter.convert(vaccinationTimeSlot, TimeSlotsDto.class));
			}
		}
		return availableSlots;
	}
}
