package br.com.zupacademy.magno.casadocodigo.pais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/pais")
public class PaisController {

    @Autowired
    EntityManager manager;

    @PostMapping
    @Transactional
    public String criaPais(@RequestBody @Valid PaisRequest request){
        Pais novoPais = request.toModel();
        manager.persist(novoPais);
        return "criando novo pais... " + request.getNome();
    }
}
