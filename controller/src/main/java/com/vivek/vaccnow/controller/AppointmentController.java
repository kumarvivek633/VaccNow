package com.vivek.vaccnow.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vivek.vaccnow.dto.ScheduleAppointmentDto;
import com.vivek.vaccnow.service.AppointmentService;
import io.swagger.annotations.ApiOperation;

/**
 * The Class BranchAvailabilityController.
 */
@RestController
@RequestMapping(path = "/api/vaccinations")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@PostMapping("/appointments")
	@ApiOperation(value = "Make Appointment")
	public void makeAppointment(@Valid @RequestBody ScheduleAppointmentDto scheduleAppointmentDto) {
		appointmentService.scheduleAppintment(scheduleAppointmentDto);
	}

}
