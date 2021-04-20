package br.com.zupacademy.magno.casadocodigo.livro;

import org.springframework.data.domain.Page;

public class LivroResponse {

    private Long id;
    private String titulo;

    public LivroResponse(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
    }

    public static Page<LivroResponse> converter(Page<Livro> livros) {
        return livros.map(LivroResponse::new);
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}
