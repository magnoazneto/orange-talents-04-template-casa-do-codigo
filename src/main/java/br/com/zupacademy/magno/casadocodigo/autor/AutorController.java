package br.com.zupacademy.magno.casadocodigo.autor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @PersistenceContext
    EntityManager manager;

    @PostMapping
    @Transactional
    public String criaAutor(@RequestBody @Valid AutorRequest request){
        Autor novoAutor = request.toModel();
        manager.persist(novoAutor);
        return "criando novo autor";
    }
}
