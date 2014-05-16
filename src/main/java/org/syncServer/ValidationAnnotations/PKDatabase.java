package org.syncServer.ValidationAnnotations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.UUID;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.syncServer.Validators.PKDatabaseValidator;


@Documented
@Target( {METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@NotNull
@NotEmpty
@Size
@Constraint(validatedBy = {PKDatabaseValidator.class})
public @interface PKDatabase {

	String message() default "{javax.validation.constrains.Size.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload> [] payload() default{};
	
	
	public UUID id = null;
}
