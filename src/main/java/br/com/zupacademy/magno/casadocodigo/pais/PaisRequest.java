package br.com.zupacademy.magno.casadocodigo.pais;

import br.com.zupacademy.magno.casadocodigo.utils.validations.UniqueValue;

import javax.validation.constraints.NotBlank;

public class PaisRequest {

    @NotBlank
    @UniqueValue(fieldName = "nome", targetClass = Pais.class)
    private String nome;

    /**
     * só quem devem usar esse é o Jackson
     */
    @Deprecated
    public PaisRequest(){ }

    public PaisRequest(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Pais toModel() {
        return new Pais(this.nome);
    }
}
