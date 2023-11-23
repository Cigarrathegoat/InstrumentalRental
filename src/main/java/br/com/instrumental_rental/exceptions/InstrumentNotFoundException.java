package br.com.instrumental_rental.exceptions;

public class InstrumentNotFoundException extends Throwable {

    private String code;

    private String message;

    public InstrumentNotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {return code;}
}
