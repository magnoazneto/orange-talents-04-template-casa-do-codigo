package br.com.zupacademy.magno.casadocodigo.estado;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class ProibeEstadoDuplicadoMesmoPaisValidator implements Validator {

    @PersistenceContext
    EntityManager manager;

    @Override
    public boolean supports(Class<?> clazz) {
        return EstadoRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()){
            return;
        }

        EstadoRequest request = (EstadoRequest) target;
        Query query = manager.createQuery("select e from Estado e join e.pais p where p.id = :pPaisId and e.nome = :pNomeEstado");
        query.setParameter("pPaisId", request.getPaisId());
        query.setParameter("pNomeEstado", request.getNome());
        List<?> list = query.getResultList();

        if (!list.isEmpty()){
            errors.rejectValue("paisId", null, "Já existe um Estado com o mesmo nome no país informado.");
        }
    }
}
