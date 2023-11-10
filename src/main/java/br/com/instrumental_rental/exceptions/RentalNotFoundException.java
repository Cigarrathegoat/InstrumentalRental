package br.com.instrumental_rental.exceptions;

public class RentalNotFoundException extends Exception {

    private String code;

    private String message;

    public RentalNotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {return code;}
}
