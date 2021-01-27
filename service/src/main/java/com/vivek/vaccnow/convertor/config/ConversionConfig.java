package com.vivek.vaccnow.convertor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

import com.vivek.vaccnow.convertor.BranchToBranchDtoConverter;
import com.vivek.vaccnow.convertor.TimeSlotToTimeSlotDtoConverter;
import com.vivek.vaccnow.convertor.VaccineRegistrationtoAppliedVaccineDtoConverter;
import com.vivek.vaccnow.convertor.VaccineRegistrationtoConfirmedVaccineDtoConverter;
import com.vivek.vaccnow.convertor.VaccineToVaccineDtoConverter;

/**
 * The Class ConversionConfig.
 */
@Configuration
public class ConversionConfig {

	/** The branch to branch dto converter. */
	@Autowired
	private BranchToBranchDtoConverter branchToBranchDtoConverter;

	/** The vaccine to vaccine dto converter. */
	@Autowired
	private VaccineToVaccineDtoConverter vaccineToVaccineDtoConverter;

	/** The time slot to time slot dto converter. */
	@Autowired
	private TimeSlotToTimeSlotDtoConverter timeSlotToTimeSlotDtoConverter;

	/** The vaccine registrationto applied vaccine dto converter. */
	@Autowired
	private VaccineRegistrationtoAppliedVaccineDtoConverter vaccineRegistrationtoAppliedVaccineDtoConverter;

	/** The vaccine registrationto confirmed vaccine dto converter. */
	@Autowired
	private VaccineRegistrationtoConfirmedVaccineDtoConverter vaccineRegistrationtoConfirmedVaccineDtoConverter;

	/**
	 * Converter.
	 *
	 * @return the conversion service
	 */
	@Bean()
	public ConversionService converter() {
		final DefaultConversionService converterService = new DefaultConversionService();
		converterService.addConverter(branchToBranchDtoConverter);
		converterService.addConverter(vaccineToVaccineDtoConverter);
		converterService.addConverter(timeSlotToTimeSlotDtoConverter);
		converterService.addConverter(vaccineRegistrationtoAppliedVaccineDtoConverter);
		converterService.addConverter(vaccineRegistrationtoConfirmedVaccineDtoConverter);
		return converterService;
	}

}
