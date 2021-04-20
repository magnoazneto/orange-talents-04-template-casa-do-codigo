package br.com.zupacademy.magno.casadocodigo.livro;

import br.com.zupacademy.magno.casadocodigo.autor.Autor;
import br.com.zupacademy.magno.casadocodigo.categoria.Categoria;
import br.com.zupacademy.magno.casadocodigo.utils.validations.ExistsValue;
import br.com.zupacademy.magno.casadocodigo.utils.validations.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

// CI total: 2 pts
public class LivroRequest {

    @NotBlank
    @UniqueValue(fieldName = "titulo", targetClass = Livro.class, message = "titulo deve ser único")
    private String titulo;

    @NotBlank @Length(max = 500)
    private String resumo;

    private String sumario;

    @NotNull @Min(20)
    private BigDecimal preco;

    @NotNull @Min(100)
    private Integer numPaginas;

    @NotBlank
    private String isbn;

    @Future @JsonFormat(pattern = "yyyy-MM-d", shape = JsonFormat.Shape.STRING)
    private LocalDate dataPublicacao;

    @NotNull
    @ExistsValue(fieldName = "id", targetClass = Categoria.class, message = "Categoria deve existir")
    private Long categoriaId;

    @NotNull
    @ExistsValue(fieldName = "id", targetClass = Autor.class, message = "Autor deve existir")
    private Long autorId;

    public LivroRequest(String titulo,
                        String resumo,
                        String sumario,
                        BigDecimal preco,
                        Integer numPaginas,
                        String isbn,
                        LocalDate dataPublicacao,
                        Long categoriaId,
                        Long autorId) {
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

    // CI: 2
    public Livro toModel(EntityManager manager){
        Categoria categoria = manager.find(Categoria.class, categoriaId); // 1
        Autor autor = manager.find(Autor.class, autorId); // 1

        Assert.state(autor!=null, "Autor(a) não encontrado(a) para o id " + autorId);
        Assert.state(categoria!=null, "Categoria não encontrado para o id " + categoriaId);

        return new Livro(this.titulo,
                this.resumo,
                this.sumario,
                this.preco,
                this.numPaginas,
                this.isbn,
                this.dataPublicacao,
                categoria,
                autor
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

    public Long getCategoriaId() {
        return categoriaId;
    }

    public Long getAutorId() {
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
