package br.com.zupacademy.magno.casadocodigo.livro;

import br.com.zupacademy.magno.casadocodigo.utils.validations.Exists;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LivroRequest {

    @NotBlank
    @Exists(fieldName = "titulo", targetClass = Livro.class)
    private String titulo;

    @NotBlank @Length(max = 500)
    private String resumo;

    private String sumario;

    @NotNull @Min(20)
    private BigDecimal preco;

    @NotNull @Min(100)
    private Integer numPaginas;

    @NotBlank
    @Exists(fieldName = "isbn", targetClass = Livro.class)
    private String isbn;

    @Future @JsonFormat(pattern = "yyyy-MM-d", shape = JsonFormat.Shape.STRING)
    private LocalDate dataPublicacao;

    @NotNull
    private Integer categoriaId;

    @NotNull
    private Integer autorId;

    public LivroRequest(String titulo,
                        String resumo,
                        String sumario,
                        BigDecimal preco,
                        Integer numPaginas,
                        String isbn,
                        LocalDate dataPublicacao,
                        Integer categoriaId,
                        Integer autorId) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numPaginas = numPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoriaId = categoriaId;
        this.autorId = autorId;
    }

    public Livro toModel(EntityManager manager){

        return new Livro(this.titulo,
                this.resumo,
                this.sumario,
                this.preco,
                this.numPaginas,
                this.isbn,
                this.dataPublicacao,
                this.categoriaId,
                this.autorId
                );
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

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public Integer getAutorId() {
        return autorId;
    }

    @Override
    public String toString() {
        return "LivroRequest{" +
                "titulo='" + titulo + '\'' +
                ", resumo='" + resumo + '\'' +
                ", sumario='" + sumario + '\'' +
                ", preco=" + preco +
                ", numPaginas=" + numPaginas +
                ", isbn='" + isbn + '\'' +
                ", dataPublicacao=" + dataPublicacao +
                ", categoriaId=" + categoriaId +
                ", autorId=" + autorId +
                '}';
    }
}
