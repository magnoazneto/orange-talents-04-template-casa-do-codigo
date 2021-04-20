package br.com.zupacademy.magno.casadocodigo.autor;

import br.com.zupacademy.magno.casadocodigo.utils.validations.Exists;
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
    @Exists(fieldName = "email", targetClass = Autor.class, shouldExist = false, message = "Email já pertence a um Autor cadastrado")
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

    public Autor toModel() {
        return new Autor(this.nome, this.descricao, this.email);
    }
}
