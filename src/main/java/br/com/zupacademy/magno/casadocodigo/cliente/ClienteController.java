package br.com.zupacademy.magno.casadocodigo.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ExigeEstadoEmPaisComListaDeEstadosValidator exigeEstadoEmPaisComListaDeEstadosValidator;

    @PersistenceContext
    EntityManager manager;

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(exigeEstadoEmPaisComListaDeEstadosValidator);
    }


    @PostMapping
    @Transactional
    public ResponseEntity<ClienteResponse> criaCliente(@RequestBody @Valid ClienteRequest request){
        Cliente novoCliente = request.toModel(manager);
        manager.persist(novoCliente);
        return ResponseEntity.ok(new ClienteResponse(novoCliente));
    }
}
