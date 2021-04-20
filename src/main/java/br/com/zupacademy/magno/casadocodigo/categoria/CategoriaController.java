package br.com.zupacademy.magno.casadocodigo.categoria;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaResponse> criaCategoria(@RequestBody @Valid CategoriaRequest request){
        Categoria novaCategoria = request.toModel();
        manager.persist(novaCategoria);
        return ResponseEntity.ok(new CategoriaResponse(novaCategoria));
    }
}
