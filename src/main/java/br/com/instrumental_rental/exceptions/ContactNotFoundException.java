package br.com.instrumental_rental.exceptions;

public class ContactNotFoundException extends Exception {

    private String code;

    private String message;

    public ContactNotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {return code;}
}
