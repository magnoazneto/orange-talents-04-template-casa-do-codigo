package br.com.zupacademy.magno.casadocodigo.utils.errors;

public class ErrorDetails {

    private String reason;
    private String error;

    public ErrorDetails(String reason, String error) {
        this.reason = reason;
        this.error = error;
    }

    public String getReason() {
        return reason;
    }

    public String getError() {
        return error;
    }
}