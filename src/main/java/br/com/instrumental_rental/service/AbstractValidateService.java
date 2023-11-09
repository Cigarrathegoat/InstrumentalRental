package br.com.instrumental_rental.service;

public abstract class AbstractValidateService<T> {

    protected abstract Boolean validate (T t);

    protected boolean validateStringIsNullOrBlank(String value) {
        return value.isBlank();
    }

    protected boolean validateLongNotZero(Long value) {
        return value != 0;
    }

    public static boolean isValidInt(int number) {
        return String.valueOf(number).length() == 4;
    }
}
