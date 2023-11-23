package br.com.instrumental_rental.exceptions;

public class EndDateNotAfterStartDateException extends Exception {

    private String code;

    private String message;

    public EndDateNotAfterStartDateException(String code, String message) {
        super(message);
        this.code = code;
    }
    public String getCode(){ return code; }
}
