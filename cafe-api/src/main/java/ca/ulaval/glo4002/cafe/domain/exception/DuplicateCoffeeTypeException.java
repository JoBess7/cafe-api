package ca.ulaval.glo4002.cafe.domain.exception;

public class DuplicateCoffeeTypeException extends CafeException {
    public DuplicateCoffeeTypeException() {
        super("DUPLICATE_COFFEE_TYPE", "A coffee already exists with such name.");
    }
}
