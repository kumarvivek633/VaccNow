package com.vivek.vaccnow.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vivek.vaccnow.dto.ConfirmedVaccinationDTO;
import com.vivek.vaccnow.models.VaccineRegisteration;

/**
 * The Class VaccineRegistrationtoConfirmedVaccineDtoConverter.
 */
@Component
public class VaccineRegistrationtoConfirmedVaccineDtoConverter
		implements Converter<VaccineRegisteration, ConfirmedVaccinationDTO> {

	/**
	 * Convert.
	 *
	 * @param input the input
	 * @return the confirmed vaccination DTO
	 */
	@Override
	public ConfirmedVaccinationDTO convert(VaccineRegisteration input) {
		ConfirmedVaccinationDTO confirmedVaccinationDTO = new ConfirmedVaccinationDTO();
		confirmedVaccinationDTO
				.setBranchName(input.getVaccinationTimeSlot().getBranchVaccine().getBranch().getBranchName());
		confirmedVaccinationDTO.setDateAndTimeConfirmed(input.getVaccinationTimeSlot().getStartDateTime());
		confirmedVaccinationDTO.setUserName(input.getUserName());
		return confirmedVaccinationDTO;
	}

}
