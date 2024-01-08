package br.com.instrumental_rental.exceptions;

public class CustomerNotFoundException extends Exception {

    private String code;

    private String message;

    public CustomerNotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }

    //public String path;

    //public String filename;

    //public LocalDateTime time;

    public String getCode() {return code;}
}
