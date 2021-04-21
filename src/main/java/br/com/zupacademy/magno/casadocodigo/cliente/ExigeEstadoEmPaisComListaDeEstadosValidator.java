package br.com.zupacademy.magno.casadocodigo.cliente;

import br.com.zupacademy.magno.casadocodigo.estado.Estado;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Component
// CI total: 7 pts
public class ExigeEstadoEmPaisComListaDeEstadosValidator implements Validator {

    @PersistenceContext
    EntityManager manager;

    @Override
    public boolean supports(Class<?> clazz) {
        return ClienteRequest.class.isAssignableFrom(clazz);
    }

    @Override // CI: 7 pts
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()){ // 1 pt pelo branch
            return;
        }

        ClienteRequest request = (ClienteRequest) target; // 1 pela classe ClienteRequest

        // verificando se o pais tem cadastro de estados, e se tiver, se o estado existe dentro daquele pais
        Query query = manager.createQuery("select e from Estado e join e.pais p where p.id =:pPaisId");
        Assert.notNull(request.getPaisId(), "O Id do País não deveria ser nulo"); // 1 ponto pela condicional, embora seja simples (teria que ver com o time)
        query.setParameter("pPaisId", request.getPaisId());
        List<?> estadosEncontrados = query.getResultList();

        if(!estadosEncontrados.isEmpty() && request.getEstadoId() == null){ // 2 - um para cada condicao
            errors.rejectValue("estadoId", null, "O Estado é obrigatório quando o País informado tem Estados existentes");
            return;
        }

        // +1 pela classe de Estado
        List<Long> estadosIds = estadosEncontrados.stream().map(estado -> ((Estado) estado).getId()).collect(Collectors.toList());

        if(!estadosIds.contains(request.getEstadoId())){ // 1 pela condicao
            errors.rejectValue("estadoId", null, "O Estado informado não existe no País informado");
        }
    }
}
