package br.com.instrumental_rental.exceptions;

public class TheAddressNotFoundException extends Exception {

    private String code;

    private String message;

    public TheAddressNotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {return code;}
}
