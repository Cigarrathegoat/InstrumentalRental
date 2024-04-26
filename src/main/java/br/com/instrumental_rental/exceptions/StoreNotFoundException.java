package br.com.instrumental_rental.exceptions;

public class StoreNotFoundException extends Exception {

    private String code;

    private String message;

    public StoreNotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {return code;}
}
