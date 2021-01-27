package com.vivek.vaccnow.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vivek.vaccnow.dto.VaccineDto;
import com.vivek.vaccnow.models.Vaccine;

/**
 * The Class VaccineToVaccineDtoConverter.
 */
@Component
public class VaccineToVaccineDtoConverter implements Converter<Vaccine, VaccineDto> {

	/**
	 * Convert.
	 *
	 * @param input the input
	 * @return the vaccine dto
	 */
	@Override
	public VaccineDto convert(Vaccine input) {
		VaccineDto vaccineDto = new VaccineDto();
		vaccineDto.setVaccineId(input.getId());
		vaccineDto.setVaccineName(input.getVaccineName());
		return vaccineDto;
	}

}
