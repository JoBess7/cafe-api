package ca.ulaval.glo4002.cafe.small.cafe.domain.layout.cube.seat.customer.order;

import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.cafe.domain.exception.InvalidMenuOrderException;
import ca.ulaval.glo4002.cafe.domain.layout.cube.seat.customer.order.CoffeeName;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CoffeeNameTest {
    private static final String INVALID_NAME = "Nonexistent";

    @Test
    public void givenInvalidCoffeeName_whenCreatingCoffeeName_shouldThrowInvalidMenuOrderException() {
        assertThrows(InvalidMenuOrderException.class, () -> new CoffeeName(INVALID_NAME));
    }
}
