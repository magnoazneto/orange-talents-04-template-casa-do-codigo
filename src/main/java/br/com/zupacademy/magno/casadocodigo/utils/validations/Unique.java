package br.com.zupacademy.magno.casadocodigo.utils.validations;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
@Documented
public @interface Unique {
    String message() default "{unique.value.violation}";
    // aplicar validacao apenas para grupos especificos
    Class<?>[] groups() default {};
    // mandar informacao a mais para a validacao...
    Class<? extends Payload>[] payload() default {};
    // alberto nao usa essa linha de baixo
    // String serviceQualifier() default ""; -> vou deletar na próxima release (comentando só para entrar na próxima diff)

    // aqui vem basicamente o que a anotacao vai precisar para validar
    String fieldName();
    Class<?> targetClass();
}
