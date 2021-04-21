package br.com.zupacademy.magno.casadocodigo.cliente;

import br.com.zupacademy.magno.casadocodigo.estado.EstadoRequest;
import br.com.zupacademy.magno.casadocodigo.pais.Pais;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

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

        // recuperando pais informado (que já passou por validacao @ExistsValue)
        // mas mesmo assim existem Asserts para programacao defensiva
        ClienteRequest request = (ClienteRequest) target; // 1 pela classe ClienteRequest
        Query paisInformadoQuery = manager.createQuery("select p from Pais p where p.id = :pPaisId");
        Assert.notNull(request.getPaisId(), "Id do país não deveria ser nulo"); // 1 ponto pela condicao do assert?
        paisInformadoQuery.setParameter("pPaisId", request.getPaisId());
        List<?> paisInformadoResult = paisInformadoQuery.getResultList();
        Assert.state(paisInformadoResult.size() == 1, "Deveria existir 1, e apenas 1, país para o Id informado"); // 1
        Pais paisInformado = (Pais) paisInformadoResult.get(0); // 1 pela classe Pais

        // verificando se o pais tem cadastro de estados
        Query query = manager.createQuery("select e from Estado e where e.pais = :pPais");
        // select e from Estado e join e.pais p where p.id =:pPaisId
        query.setParameter("pPais", paisInformado);
        List<?> estadosEncontrados = query.getResultList();
        if(!estadosEncontrados.isEmpty() && request.getEstadoId() == null){ // 2 - um para cada condicao
            errors.rejectValue("estadoId", null, "O Estado é obrigatório quando o País informado tem Estados existentes");
        }

    }
}
