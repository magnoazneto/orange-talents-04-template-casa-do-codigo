package br.com.zupacademy.magno.casadocodigo.categoria;

public class CategoriaResponse {

    private Long id;
    private String nome;

    public CategoriaResponse(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
