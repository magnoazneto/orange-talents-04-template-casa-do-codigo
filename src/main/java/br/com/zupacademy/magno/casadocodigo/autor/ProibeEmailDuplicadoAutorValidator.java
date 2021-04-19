package br.com.zupacademy.magno.casadocodigo.autor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

// Essa classe mostra uma forma alternativa de se realizar uma validacao
// pela estrutura, ele pode ser mais flexivel do que apenas uma restricao de valor unico
// -- IGNORAR NA VALIDACAO DE EMAIL, APENAS PARA FINS DE REFERENCIA FUTURA --
@Component
public class ProibeEmailDuplicadoAutorValidator implements Validator {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return AutorRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()){
            return;
        }

        AutorRequest request = (AutorRequest) target;
        Optional<Autor> possivelAutor = autorRepository.findByEmail(request.getEmail());
        if(possivelAutor.isPresent()){
            errors.rejectValue("email", null, "Já existe um email cadastrado cadastrado com o mesmo valor.");
        }
    }
}
