package br.com.zupacademy.magno.casadocodigo.livro;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @PostMapping
    @Transactional
    public String criaLivro(@RequestBody @Valid LivroRequest request){

        return request.toString();
    }
}
