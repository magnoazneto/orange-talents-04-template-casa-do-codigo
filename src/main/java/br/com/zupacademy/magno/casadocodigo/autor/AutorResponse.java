package br.com.zupacademy.magno.casadocodigo.autor;

public class AutorResponse {

    private final Long id;
    private final String nome;

    public AutorResponse(Autor autor) {
        this.id = autor.getId();
        this.nome = autor.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Autor: {" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
