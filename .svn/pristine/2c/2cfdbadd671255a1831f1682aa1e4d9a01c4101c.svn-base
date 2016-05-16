package gappp.web.validator;

import gappp.model.User;
import gappp.model.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RegisterValidator implements Validator{
	
	@Autowired
    private UserDao userDao;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors error) {
		User user = (User) target;
		if(!StringUtils.hasText(user.getFirstName()))
			error.rejectValue("firstName", "error.firstName.empty");
		if(!StringUtils.hasText(user.getLastName()))
			error.rejectValue("lastName", "error.lastName.empty");
		if(!StringUtils.hasText(user.geteMail()))
			error.rejectValue("eMail", "error.eMail.empty");
		if(!StringUtils.hasText(user.getPassword()))
			error.rejectValue("password", "error.password.empty");
		if(StringUtils.hasText(user.geteMail()))
		{
			if(userDao.getUser(user.geteMail().toLowerCase()))
				error.rejectValue("eMail", "error.eMail.duplicate");
		}
	}

}
