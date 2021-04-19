package br.com.zupacademy.magno.casadocodigo.autor;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Length(max = 400)
    private String descricao;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private LocalDateTime instanteCriacao;

    /**
     * @deprecated Esse construtor apenas satisfaz a exigencia da JPA
     */
    @Deprecated
    public Autor(){  }

    public Autor(@NotBlank String nome,
                 @NotBlank
                 @Length(max = 400) String descricao,
                 @NotBlank @Email String email) {
        this.nome = nome;
        this.descricao = descricao;
        this.email = email;
        this.instanteCriacao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
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

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }
}
