package com.vivek.vaccnow.service;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.stereotype.Service;
import org.testng.annotations.Test;

import com.vivek.vaccnow.dto.ScheduleAppointmentDto;
import com.vivek.vaccnow.enums.AvailabilityStatus;
import com.vivek.vaccnow.enums.PaymentMethods;
import com.vivek.vaccnow.models.BranchVaccineInventory;
import com.vivek.vaccnow.models.VaccinationTimeSlot;
import com.vivek.vaccnow.repository.VaccinationTimeSlotRepository;
import com.vivek.vaccnow.service.AvailabilityService;
import com.vivek.vaccnow.service.impl.AppointmentServiceImp;

/**
 * The Class AppointmentServiceTest.
 */
@Service
public class AppointmentServiceTest extends AbstractBaseMockitoTest {

	/** The testee. */
	@InjectMocks
	AppointmentServiceImp testee;

	/** The availability service. */
	@Mock
	AvailabilityService availabilityService;

	/** The vaccination time slot repository. */
	@Mock
	private VaccinationTimeSlotRepository vaccinationTimeSlotRepository;

	/**
	 * Test schedule appintment.
	 */
	@Test
	public void testScheduleAppintment() {
		ScheduleAppointmentDto appointmentDto = new ScheduleAppointmentDto();
		appointmentDto.setBranchId(1);
		appointmentDto.setDate(new Date());
		appointmentDto.setHour(1);
		appointmentDto.setMinute(15);
		appointmentDto.setPaymentMethod(PaymentMethods.Card);
		appointmentDto.setUserName("vkumar");

		VaccinationTimeSlot vaccinationTimeSlot = new VaccinationTimeSlot();
		vaccinationTimeSlot.setId(1l);
		vaccinationTimeSlot.setBranchVaccine(new BranchVaccineInventory());
		vaccinationTimeSlot.getBranchVaccine().setVaccineInStock(10);
		vaccinationTimeSlot.setRegisterations(new HashSet<>());
		List<VaccinationTimeSlot> vaccinationTimeSlots = new ArrayList<>();
		vaccinationTimeSlots.add(vaccinationTimeSlot);

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(appointmentDto.getDate());
		LocalDate appointmentDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
				calendar.get(Calendar.DATE));

		when(availabilityService.checkAvailability(1l, appointmentDate, 1, 15))
				.thenReturn(AvailabilityStatus.AVAILABLE);
		when(vaccinationTimeSlotRepository.getAvailableTimeSlotsForDateAndBranch(Mockito.anyLong(), Mockito.any(),
				Mockito.any())).thenReturn(vaccinationTimeSlots);
		testee.scheduleAppintment(appointmentDto);
		verify(availabilityService, atLeast(1)).checkAvailability(1l, appointmentDate, 1, 15);
	}
}
