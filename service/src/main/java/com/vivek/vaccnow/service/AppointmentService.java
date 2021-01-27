package com.vivek.vaccnow.service;

import com.vivek.vaccnow.dto.ScheduleAppointmentDto;

public interface AppointmentService {

	/**
	 * Schedule appintment.
	 *
	 * @param appointmentDto the appointment dto
	 */
	void scheduleAppintment(ScheduleAppointmentDto appointmentDto);
}
