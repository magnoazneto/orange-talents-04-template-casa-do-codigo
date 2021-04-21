package br.com.zupacademy.magno.casadocodigo.estado;

import br.com.zupacademy.magno.casadocodigo.pais.Pais;
import br.com.zupacademy.magno.casadocodigo.utils.validations.ExistsValue;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EstadoRequest {

    @NotBlank
    private String nome;

    @NotNull @ExistsValue(targetClass = Pais.class, fieldName = "id")
    private Long paisId;

    public EstadoRequest(String nome, Long paisId) {
        this.nome = nome;
        this.paisId = paisId;
    }

    public String getNome() {
        return nome;
    }

    public Long getPaisId() {
        return paisId;
    }

    public Estado toModel(EntityManager manager) {
        Pais pais = manager.find(Pais.class, paisId);
        Assert.state(pais != null, "Pais não existe no sistema");
        return new Estado(this.nome, pais);
    }
}
