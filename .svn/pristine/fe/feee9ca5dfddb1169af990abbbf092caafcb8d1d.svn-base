package gappp.web.validator;

import gappp.model.User;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class LoginValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors error) {
		User user = (User) target;
		if(!StringUtils.hasText(user.geteMail()))
			error.rejectValue("eMail", "error.eMail.empty");
		if(!StringUtils.hasText(user.getPassword()))
			error.rejectValue("password", "error.password.empty");
	}

}
