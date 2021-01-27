package com.vivek.vaccnow.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.vivek.vaccnow.dto.ScheduleAppointmentDto;
import com.vivek.vaccnow.enums.AvailabilityStatus;
import com.vivek.vaccnow.enums.Status;
import com.vivek.vaccnow.models.VaccinationTimeSlot;
import com.vivek.vaccnow.models.VaccineRegisteration;
import com.vivek.vaccnow.repository.VaccinationTimeSlotRepository;
import com.vivek.vaccnow.service.AppointmentService;
import com.vivek.vaccnow.service.AvailabilityService;

/**
 * The Class AppointmentServiceImp.
 */
@Service
public class AppointmentServiceImp implements AppointmentService {

	/** The availability service. */
	@Autowired
	AvailabilityService availabilityService;

	/** The vaccination time slot repository. */
	@Autowired
	private VaccinationTimeSlotRepository vaccinationTimeSlotRepository;

	/**
	 * Schedule appintment.
	 *
	 * @param appointmentDto the appointment dto
	 */
	@Override
	@Transactional
	public void scheduleAppintment(ScheduleAppointmentDto appointmentDto) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(appointmentDto.getDate());
		LocalDate appointmentDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE));
		AvailabilityStatus availabilityStatus = availabilityService.checkAvailability(appointmentDto.getBranchId(),
				appointmentDate, appointmentDto.getHour(), appointmentDto.getMinute());
		if (availabilityStatus.equals(AvailabilityStatus.AVAILABLE)) {
			List<VaccinationTimeSlot> vaccinationTimeSlots = vaccinationTimeSlotRepository
					.getAvailableTimeSlotsForDateAndBranch(appointmentDto.getBranchId(),
							LocalDateTime.of(appointmentDate,
									LocalTime.of(appointmentDto.getHour(), appointmentDto.getMinute())),
							LocalDateTime.of(appointmentDate, LocalTime
									.of(appointmentDto.getHour(), appointmentDto.getMinute()).plusMinutes(15)));
			if (!CollectionUtils.isEmpty(vaccinationTimeSlots)) {
				VaccinationTimeSlot timeSlot = vaccinationTimeSlots.get(0);
				VaccineRegisteration vaccineRegisteration = new VaccineRegisteration();
				vaccineRegisteration.setPaymentMethod(appointmentDto.getPaymentMethod());
				vaccineRegisteration.setStatus(Status.Registered);
				vaccineRegisteration.setTimeSlotId(timeSlot.getId());
				vaccineRegisteration.setUserName(appointmentDto.getUserName());
				timeSlot.getRegisterations().add(vaccineRegisteration);
				timeSlot.setCapacity(timeSlot.getCapacity() - 1);
				timeSlot.getBranchVaccine().setVaccineInStock(timeSlot.getBranchVaccine().getVaccineInStock() - 1);
				vaccinationTimeSlotRepository.save(timeSlot);
			}
		}
	}

}
