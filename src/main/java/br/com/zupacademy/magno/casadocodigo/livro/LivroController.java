package br.com.zupacademy.magno.casadocodigo.livro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @PersistenceContext
    EntityManager manager;

    @Autowired
    LivroRepository livroRepo;

    @GetMapping("/{id}")
    public ResponseEntity<LivroDetalheResponse> detalhaLivro(@PathVariable Long id){
        Optional<Livro> possivelLivro = livroRepo.findById(id);

        return possivelLivro.map(livro -> {
            return ResponseEntity.ok(new LivroDetalheResponse(livro));
        }).orElseGet(() ->  ResponseEntity.notFound().build());

    }

    @GetMapping
    public Page<LivroResponse> listaLivros(Pageable paginacao) {
        Page<Livro> livros = livroRepo.findAll(paginacao);
        return LivroResponse.converter(livros);
    }

    @PostMapping
    @Transactional
    public String criaLivro(@RequestBody @Valid LivroRequest request){
        Livro novoLivro = request.toModel(manager);
        manager.persist(novoLivro);
        return request.toString();
    }
}
