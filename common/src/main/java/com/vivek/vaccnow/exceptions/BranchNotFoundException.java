package com.vivek.vaccnow.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class BranchNotFoundException.
 */
@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED, reason = "Invalid Branch Id")
public class BranchNotFoundException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

}
