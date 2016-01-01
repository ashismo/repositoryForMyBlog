package com.org.coop.admin.validator;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.org.coop.canonical.beans.UserProfile;

@Service
public class UserValidator implements Validator {
	public boolean supports(Class<?> paramClass) {
		return UserProfile.class.equals(paramClass);
	}

	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "otp", "otp.blank");
	}
}
