package br.com.zupacademy.magno.casadocodigo.cliente;

import br.com.zupacademy.magno.casadocodigo.pais.Pais;
import br.com.zupacademy.magno.casadocodigo.utils.validations.CPForCNPJ;
import br.com.zupacademy.magno.casadocodigo.utils.validations.ExistsValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ClienteRequest {

    @NotBlank @Email
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
    @CPForCNPJ
    // @UniqueValue
    private String documento;
    @NotBlank
    private String endereco;
    @NotBlank
    private String complemento;
    @NotBlank
    private String cidade;
    @NotNull @ExistsValue(targetClass = Pais.class, fieldName = "id", message = "O pais com Id informado não existe no sistema")
    private Long paisId;
    // validacao do Spring aqui ou uma nova personalizada
    private Long estadoId;
    @NotBlank
    private String telefone;
    @NotBlank
    private String cep;

    public ClienteRequest(String email,
                          String nome,
                          String sobrenome,
                          String documento,
                          String endereco,
                          String complemento,
                          String cidade,
                          Long paisId,
                          Long estadoId,
                          String telefone,
                          String cep) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.paisId = paisId;
        this.estadoId = estadoId;
        this.telefone = telefone;
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public Long getPaisId() {
        return paisId;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    @Override
    public String toString() {
        return "ClienteRequest{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", documento='" + documento + '\'' +
                ", endereco='" + endereco + '\'' +
                ", complemento='" + complemento + '\'' +
                ", cidade='" + cidade + '\'' +
                ", paisId=" + paisId +
                ", estadoId=" + estadoId +
                ", telefone='" + telefone + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }
}
