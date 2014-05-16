package org.syncServer.Validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;
import org.syncServer.ValidationAnnotations.UserPassword;


@Component
public class UserPasswordValidator implements ConstraintValidator<UserPassword, String> {

    private String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@!&#$%]).{8,30})";


    public void initialize(UserPassword pwd) {
	// do nothing
    }


    public boolean isValid(String str, ConstraintValidatorContext ctx) {
	return validate(str);
    }


    public boolean validate(final String password) {
	Matcher matcher;
	Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
	matcher = pattern.matcher(password);
	return matcher.matches();
    }

}
