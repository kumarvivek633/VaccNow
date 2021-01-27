package com.vivek.vaccnow.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vivek.vaccnow.dto.AppliedVaccinationDTO;
import com.vivek.vaccnow.models.VaccineRegisteration;

/**
 * The Class VaccineRegistrationtoAppliedVaccineDtoConverter.
 */
@Component
public class VaccineRegistrationtoAppliedVaccineDtoConverter
		implements Converter<VaccineRegisteration, AppliedVaccinationDTO> {

	/**
	 * Convert.
	 *
	 * @param input the input
	 * @return the applied vaccination DTO
	 */
	@Override
	public AppliedVaccinationDTO convert(VaccineRegisteration input) {
		AppliedVaccinationDTO appliedVaccinationDTO = new AppliedVaccinationDTO();
		appliedVaccinationDTO
				.setBranchName(input.getVaccinationTimeSlot().getBranchVaccine().getBranch().getBranchName());
		appliedVaccinationDTO.setDateAndTimeConfirmed(input.getVaccinationTimeSlot().getStartDateTime());
		appliedVaccinationDTO.setUserName(input.getUserName());
		appliedVaccinationDTO
				.setVaccineName(input.getVaccinationTimeSlot().getBranchVaccine().getVaccine().getVaccineName());
		return appliedVaccinationDTO;
	}

}
