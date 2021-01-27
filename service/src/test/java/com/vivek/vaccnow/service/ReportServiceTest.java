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

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.testng.annotations.Test;

import com.vivek.vaccnow.dto.AppliedVaccinationDTO;
import com.vivek.vaccnow.dto.ConfirmedVaccinationDTO;
import com.vivek.vaccnow.enums.Status;
import com.vivek.vaccnow.models.VaccineRegisteration;
import com.vivek.vaccnow.repository.VaccineRegistrationRepository;
import com.vivek.vaccnow.service.impl.ReportServiceImpl;

/**
 * The Class ReportServiceTest.
 */
@Service
public class ReportServiceTest extends AbstractBaseMockitoTest {

	/** The testee. */
	@InjectMocks
	ReportServiceImpl testee;

	/** The vaccine registration repository. */
	@Mock
	VaccineRegistrationRepository vaccineRegistrationRepository;

	/** The converter. */
	@Mock
	private ConversionService converter;

	/**
	 * Test get all applied vaccinations for branch.
	 */
	@Test
	public void testGetAllAppliedVaccinationsForBranch() {

		List<VaccineRegisteration> vaccineRegisterations = new ArrayList<>();
		vaccineRegisterations.add(new VaccineRegisteration());
		vaccineRegisterations.get(0).setTimeSlotId(1l);

		when(vaccineRegistrationRepository.findByBranch(Mockito.anyLong(), Mockito.any()))
				.thenReturn(new PageImpl<>(vaccineRegisterations));
		when(converter.convert(Mockito.any(), Mockito.any())).thenReturn(new AppliedVaccinationDTO());
		Page<AppliedVaccinationDTO> page = testee.getAllAppliedVaccinationsForBranch(Mockito.anyLong(), Mockito.any());
		assertNotNull(page);
		verify(vaccineRegistrationRepository, atLeast(1)).findByBranch(Mockito.anyLong(), Mockito.any());
	}

	/**
	 * Test get applied vaccine for specific period.
	 */
	@Test
	public void testGetAppliedVaccineForSpecificPeriod() {

		List<VaccineRegisteration> vaccineRegisterations = new ArrayList<>();
		vaccineRegisterations.add(new VaccineRegisteration());
		vaccineRegisterations.get(0).setTimeSlotId(1l);
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = startDate.plusDays(1l);
		when(vaccineRegistrationRepository.findByPeriod(LocalDateTime.of(startDate, LocalTime.MIDNIGHT),
				LocalDateTime.of(endDate, LocalTime.MAX), Status.Vaccinated.name(), new PageRequest(0, 10)))
						.thenReturn(new PageImpl<>(vaccineRegisterations));
		when(converter.convert(Mockito.any(), Mockito.any())).thenReturn(new AppliedVaccinationDTO());
		Page<AppliedVaccinationDTO> page = testee.getAppliedVaccineForSpecificPeriod(startDate, endDate,
				new PageRequest(0, 10));
		assertNotNull(page);
		verify(vaccineRegistrationRepository, atLeast(1)).findByPeriod(LocalDateTime.of(startDate, LocalTime.MIDNIGHT),
				LocalDateTime.of(endDate, LocalTime.MAX), Status.Vaccinated.name(), new PageRequest(0, 10));
	}

	/**
	 * Test get appointments for specific period.
	 */
	@Test
	public void testGetAppointmentsForSpecificPeriod() {

		List<VaccineRegisteration> vaccineRegisterations = new ArrayList<>();
		vaccineRegisterations.add(new VaccineRegisteration());
		vaccineRegisterations.get(0).setTimeSlotId(1l);
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = startDate.plusDays(1l);
		when(vaccineRegistrationRepository.findByPeriod(LocalDateTime.of(startDate, LocalTime.MIDNIGHT),
				LocalDateTime.of(endDate, LocalTime.MAX), Status.Registered.name(), new PageRequest(0, 10)))
						.thenReturn(new PageImpl<>(vaccineRegisterations));
		when(converter.convert(Mockito.any(), Mockito.any())).thenReturn(new ConfirmedVaccinationDTO());
		Page<ConfirmedVaccinationDTO> page = testee.getAppointmentsForSpecificPeriod(startDate, endDate,
				new PageRequest(0, 10));
		assertNotNull(page);
		verify(vaccineRegistrationRepository, atLeast(1)).findByPeriod(LocalDateTime.of(startDate, LocalTime.MIDNIGHT),
				LocalDateTime.of(endDate, LocalTime.MAX), Status.Registered.name(), new PageRequest(0, 10));
	}
}
