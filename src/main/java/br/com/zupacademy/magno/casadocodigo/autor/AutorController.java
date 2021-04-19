package br.com.zupacademy.magno.casadocodigo.autor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @PersistenceContext
    EntityManager manager;

    @Autowired
    private ProibeEmailDuplicadoAutorValidator proibeEmailDuplicadoAutorValidator;

    /**
     * codigo executado no primeiro request
     * nota: em tempo de execucao, isso ainda vai ser executado depois da @Unique annotation desse projeto
     * @param binder
     */
    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(proibeEmailDuplicadoAutorValidator); // essa linha pode ser deletada sem prejuizos desde que o @Unique faça esse papel.
    }


    @PostMapping
    @Transactional
    public ResponseEntity<AutorResponse> criaAutor(@RequestBody @Valid AutorRequest request){
        Autor novoAutor = request.toModel();
        manager.persist(novoAutor);
        return ResponseEntity.ok(new AutorResponse(novoAutor));
    }
}
