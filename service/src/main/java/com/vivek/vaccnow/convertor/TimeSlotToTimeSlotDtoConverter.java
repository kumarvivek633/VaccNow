package com.vivek.vaccnow.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vivek.vaccnow.dto.TimeSlotsDto;
import com.vivek.vaccnow.models.VaccinationTimeSlot;

/**
 * The Class TimeSlotToTimeSlotDtoConverter.
 */
@Component
public class TimeSlotToTimeSlotDtoConverter implements Converter<VaccinationTimeSlot, TimeSlotsDto> {

	/**
	 * Convert.
	 *
	 * @param input the input
	 * @return the time slots dto
	 */
	@Override
	public TimeSlotsDto convert(VaccinationTimeSlot input) {
		TimeSlotsDto timeSlotsDto = new TimeSlotsDto();
		timeSlotsDto.setStartDateTime(input.getStartDateTime());
		timeSlotsDto.setEndDateTime(input.getEndDateTime());
		timeSlotsDto.setVaccineName(input.getBranchVaccine().getVaccine().getVaccineName());
		return timeSlotsDto;
	}

}
