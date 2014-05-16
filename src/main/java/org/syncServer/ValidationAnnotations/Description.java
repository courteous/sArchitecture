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

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Target( {METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
@NotEmpty
@Size(message = "The size of the description should be between {min} and {max} caracters", 
				min = 1, max = 500, 
				payload = Severity.Error.class
				)
public @interface Description {

	String message() default "canot not empty and size should not exede {max}";
	Class<?>[] groups() default {};
	Class<? extends Payload> [] payload() default{};
}
