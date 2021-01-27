package com.vivek.vaccnow.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.Test;

import com.vivek.vaccnow.dto.BranchDto;
import com.vivek.vaccnow.dto.TimeSlotsDto;
import com.vivek.vaccnow.enums.AvailabilityStatus;
import com.vivek.vaccnow.models.Branch;
import com.vivek.vaccnow.models.BranchVaccineInventory;
import com.vivek.vaccnow.models.VaccinationTimeSlot;
import com.vivek.vaccnow.repository.BranchRepository;
import com.vivek.vaccnow.repository.BranchVacinationInventoryRepository;
import com.vivek.vaccnow.repository.VaccinationTimeSlotRepository;
import com.vivek.vaccnow.service.impl.AvailablityServiceImpl;
import junit.framework.Assert;

/**
 * The Class AvailabilityServiceTest.
 */
@Service
public class AvailabilityServiceTest extends AbstractBaseMockitoTest {

	/** The testee. */
	@InjectMocks
	AvailablityServiceImpl testee;

	/** The branch repository. */
	@Mock
	private BranchRepository branchRepository;

	/** The vaccination time slot repository. */
	@Mock
	private VaccinationTimeSlotRepository vaccinationTimeSlotRepository;

	/** The branch vacination inventory repository. */
	@Mock
	private BranchVacinationInventoryRepository branchVacinationInventoryRepository;

	/** The converter. */
	@Mock
	private ConversionService converter;

	/**
	 * Test get all applied vaccinations for branch.
	 */
	@Test
	public void testGetAllBranch() {
		List<Branch> branches = new ArrayList<>();
		branches.add(new Branch(1l));
		when(branchRepository.findAll(new PageRequest(0, 1))).thenReturn(new PageImpl<>(branches));
		when(converter.convert(Mockito.any(), Mockito.any())).thenReturn(new BranchDto());
		Page<BranchDto> page = testee.getAllBranch(new PageRequest(0, 1));
		assertNotNull(page);
		verify(converter, atLeast(1)).convert(Mockito.any(), Mockito.any());
	}

	/**
	 * Test get vaccines by branch.
	 */
	@Test
	public void testGetVaccinesByBranch() {
		Branch branche = new Branch(1l);
		when(branchRepository.findById(1l)).thenReturn(Optional.of(branche));
		when(converter.convert(Mockito.any(), Mockito.any())).thenReturn(new BranchDto());
		testee.getVaccinesByBranch(1l);
		verify(converter, atLeast(1)).convert(Mockito.any(), Mockito.any());
		verify(branchRepository, atLeast(1)).findById(1l);
	}

	/**
	 * Test check availability.
	 */
	@Test
	public void testCheckAvailability() {
		LocalDate date = LocalDate.now();

		List<BranchVaccineInventory> branchVaccineInventories = new ArrayList<>();
		branchVaccineInventories.add(new BranchVaccineInventory());
		branchVaccineInventories.get(0).setId(1l);
		branchVaccineInventories.get(0).setBranch(new Branch(1l));

		ReflectionTestUtils.setField(testee, "reservationWindows", 15l);

		when(vaccinationTimeSlotRepository.findByBranchVaccinesBranch(1l, LocalDateTime.of(date, LocalTime.MIDNIGHT),
				LocalDateTime.of(date, LocalTime.MAX))).thenReturn(0l);
		when(branchVacinationInventoryRepository.findByBranch(1l, LocalDateTime.of(date, LocalTime.MIDNIGHT),
				LocalDateTime.of(date, LocalTime.MAX))).thenReturn(branchVaccineInventories);
		when(vaccinationTimeSlotRepository.checkBookinsForTheSpecifiedTimeInBranch(1l,
				LocalDateTime.of(date, LocalTime.of(1, 15)),
				LocalDateTime.of(date, LocalTime.of(1, 15)).plusMinutes(15l))).thenReturn(10l);
		AvailabilityStatus availabilityStatus = testee.checkAvailability(1l, date, 1, 15);
		verify(vaccinationTimeSlotRepository, atLeast(1)).findByBranchVaccinesBranch(1l,
				LocalDateTime.of(date, LocalTime.MIDNIGHT), LocalDateTime.of(date, LocalTime.MAX));
		verify(branchVacinationInventoryRepository, atLeast(1)).findByBranch(1l,
				LocalDateTime.of(date, LocalTime.MIDNIGHT), LocalDateTime.of(date, LocalTime.MAX));
		Assert.assertEquals(AvailabilityStatus.AVAILABLE, availabilityStatus);
	}

	/**
	 * Test get available time slots.
	 */
	@Test
	public void testGetAvailableTimeSlots() {
		LocalDate date = LocalDate.now();

		List<BranchVaccineInventory> branchVaccineInventories = new ArrayList<>();
		branchVaccineInventories.add(new BranchVaccineInventory());
		branchVaccineInventories.get(0).setId(1l);
		branchVaccineInventories.get(0).setBranch(new Branch(2l));

		List<VaccinationTimeSlot> timeSlots = new ArrayList<>();
		timeSlots.add(new VaccinationTimeSlot());
		timeSlots.get(0).setId(1l);

		ReflectionTestUtils.setField(testee, "reservationWindows", 15l);

		when(vaccinationTimeSlotRepository.findByBranchVaccinesBranch(1l, LocalDateTime.of(date, LocalTime.MIDNIGHT),
				LocalDateTime.of(date, LocalTime.MAX))).thenReturn(0l);
		when(branchVacinationInventoryRepository.findByBranch(1l, LocalDateTime.of(date, LocalTime.MIDNIGHT),
				LocalDateTime.of(date, LocalTime.MAX))).thenReturn(branchVaccineInventories);
		when(vaccinationTimeSlotRepository.getAvailableTimeSlotsForDateAndBranch(1l,
				LocalDateTime.of(date, LocalTime.MIDNIGHT), LocalDateTime.of(date, LocalTime.MAX)))
						.thenReturn(timeSlots);
		when(converter.convert(Mockito.any(), Mockito.any())).thenReturn(new TimeSlotsDto());
		List<TimeSlotsDto> availabilityStatus = testee.getAvailableTimeSlots(1l, date);
		verify(vaccinationTimeSlotRepository, atLeast(1)).findByBranchVaccinesBranch(1l,
				LocalDateTime.of(date, LocalTime.MIDNIGHT), LocalDateTime.of(date, LocalTime.MAX));
		verify(branchVacinationInventoryRepository, atLeast(1)).findByBranch(1l,
				LocalDateTime.of(date, LocalTime.MIDNIGHT), LocalDateTime.of(date, LocalTime.MAX));
		assertNotNull(availabilityStatus);
	}
}
