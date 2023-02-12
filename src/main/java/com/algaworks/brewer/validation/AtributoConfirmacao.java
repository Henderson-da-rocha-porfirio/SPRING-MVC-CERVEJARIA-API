package com.algaworks.brewer.validation;

import com.algaworks.brewer.validation.validator.*;

import javax.validation.*;
import javax.validation.constraints.*;
import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { AtributoConfirmacaoValidator.class })
public @interface AtributoConfirmacao {

	@OverridesAttribute(constraint = Pattern.class, name = "message")
	String message() default "Atributos não conferem";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	String atributo();
	
	String atributoConfirmacao();
	
}
