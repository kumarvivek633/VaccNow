package com.vivek.vaccnow.convertor;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.vivek.vaccnow.dto.BranchDto;
import com.vivek.vaccnow.models.Branch;
import com.vivek.vaccnow.models.BranchVaccineInventory;

/**
 * The Class BranchToBranchDtoConverter.
 */
@Component
public class BranchToBranchDtoConverter implements Converter<Branch, BranchDto> {

	/** The Vaccine to vaccine dto converter. */
	@Autowired
	VaccineToVaccineDtoConverter vaccineToVaccineDtoConverter;

	/**
	 * Convert.
	 *
	 * @param input the input
	 * @return the branch dto
	 */
	@Override
	public BranchDto convert(Branch input) {
		BranchDto branchDto = new BranchDto();
		branchDto.setBranchId(input.getId());
		branchDto.setBranchName(input.getBranchName());
		if (input.isGetVaccine() && CollectionUtils.isEmpty(input.getBranchVaccines())) {
			branchDto.setAvailableVaccines(new ArrayList<>());
			for (BranchVaccineInventory branchVaccine : input.getBranchVaccines()) {
				branchDto.getAvailableVaccines().add(vaccineToVaccineDtoConverter.convert(branchVaccine.getVaccine()));
			}
		}
		return branchDto;
	}

}
