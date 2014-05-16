package org.syncServer.ValidationAnnotations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.syncServer.Validators.UserPasswordValidator;


@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = UserPasswordValidator.class)
public @interface UserPassword {

    String message() default "{Invalid password, valid password : one Upper case, one special char, one number, total length between 8 and 30 chars}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}