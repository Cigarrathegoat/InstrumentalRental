package br.com.instrumental_rental.exceptions;

public class AttendantNotFoundException extends Exception {

    private String code;

    private String message;

    public AttendantNotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {return code;}
}
