package br.com.zupacademy.magno.casadocodigo.autor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @PostMapping
    @Transactional
    public String criaAutor(@RequestBody @Valid AutorRequest request){
        return "criando novo autor";
    }
}
