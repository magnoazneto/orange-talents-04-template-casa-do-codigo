package br.com.zupacademy.magno.casadocodigo.estado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/estado")
public class EstadoController {

    @PersistenceContext
    EntityManager manager;

    @Autowired
    ProibeEstadoDuplicadoMesmoPaisValidator proibeEmailDuplicadoAutorValidator;

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(proibeEmailDuplicadoAutorValidator);
    }

    @PostMapping
    @Transactional
    public String criaEstado(@RequestBody @Valid EstadoRequest request){
        Estado novoEstado = request.toModel(manager);
        manager.persist(novoEstado);
        return "novo estado criado: " + request.getNome();

    }
}
