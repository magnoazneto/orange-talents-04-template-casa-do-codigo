package br.com.zupacademy.magno.casadocodigo.cliente;

public class ClienteResponse {

    private Long id;

    @Deprecated
    public ClienteResponse(){

    }

    public ClienteResponse(Cliente cliente) {
        this.id = cliente.getId();
    }

    public Long getId() {
        return id;
    }
}
