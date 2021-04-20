package br.com.zupacademy.magno.casadocodigo.livro;

import br.com.zupacademy.magno.casadocodigo.autor.AutorResponse;
import br.com.zupacademy.magno.casadocodigo.categoria.CategoriaResponse;

import java.math.BigDecimal;


public class LivroDetalheResponse {

    private String titulo;
    private String resumo;
    private String sumario;
    private BigDecimal preco;
    private Integer numPaginas;
    private String isbn;
    private CategoriaResponse categoria;
    private AutorResponse autor;

    public LivroDetalheResponse(Livro livro) {
        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.preco = livro.getPreco();
        this.numPaginas = livro.getNumPaginas();
        this.isbn = livro.getIsbn();
        this.categoria = new CategoriaResponse(livro.getCategoria());
        this.autor = new AutorResponse(livro.getAutor());
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getNumPaginas() {
        return numPaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public CategoriaResponse getCategoria() {
        return categoria;
    }

    public AutorResponse getAutor() {
        return autor;
    }
}
