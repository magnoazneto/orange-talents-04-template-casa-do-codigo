package br.com.zupacademy.magno.casadocodigo.autor;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AutorRequest {

    @NotBlank
    private String nome;

    @NotBlank
    @Length(max = 400)
    private String descricao;

    @NotBlank
    @Email
    private String email;

    public AutorRequest(String nome, String descricao, String email) {
        this.nome = nome;
        this.descricao = descricao;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEmail() {
        return email;
    }
}
