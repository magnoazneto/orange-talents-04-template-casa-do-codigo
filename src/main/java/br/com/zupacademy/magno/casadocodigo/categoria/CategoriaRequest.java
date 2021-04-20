package br.com.zupacademy.magno.casadocodigo.categoria;

import br.com.zupacademy.magno.casadocodigo.utils.validations.Unique;

import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

    @NotBlank
    @Unique(fieldName = "nome", targetClass = Categoria.class, message = "Já existe uma Categoria cadastrada com esse nome")
    private String nome;

    @Deprecated
    public CategoriaRequest(){

    }

    public CategoriaRequest(@NotBlank String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Categoria toModel() {
        return new Categoria(this.nome);
    }
}
