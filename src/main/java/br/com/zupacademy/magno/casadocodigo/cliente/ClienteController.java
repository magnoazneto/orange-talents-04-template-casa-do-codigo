package br.com.zupacademy.magno.casadocodigo.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ExigeEstadoEmPaisComListaDeEstadosValidator exigeEstadoEmPaisComListaDeEstadosValidator;

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(exigeEstadoEmPaisComListaDeEstadosValidator);
    }


    @PostMapping
    @Transactional
    public String criaCliente(@RequestBody @Valid ClienteRequest request){
        return request.toString();
    }
}
