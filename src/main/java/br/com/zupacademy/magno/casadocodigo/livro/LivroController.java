package br.com.zupacademy.magno.casadocodigo.livro;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/livro")
//
public class LivroController {

    @PersistenceContext
    EntityManager manager;

    @PostMapping
    @Transactional
    public String criaLivro(@RequestBody @Valid LivroRequest request){
        Livro novoLivro = request.toModel(manager);
        manager.persist(novoLivro);
        return request.toString();
    }
}
