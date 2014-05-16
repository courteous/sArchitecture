package org.syncServer.Validators;

import java.util.UUID;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;
import org.syncServer.ValidationAnnotations.PKDatabase;

@Component
public class PKDatabaseValidator implements ConstraintValidator<PKDatabase, java.util.UUID> {

	private UUID id;

	@Override
	public void initialize(PKDatabase constraintAnnotation) {
		this.id = PKDatabase.id;
		
	}

	@Override
	public boolean isValid(UUID value, ConstraintValidatorContext context) {
		if(value == null ){
			//check here that the id supplied is really the id of the user in question
			return false;
		}
		
		return true;
	}


	
	
	
	
	
}
