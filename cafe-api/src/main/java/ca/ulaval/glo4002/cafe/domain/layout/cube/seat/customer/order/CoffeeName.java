package ca.ulaval.glo4002.cafe.domain.layout.cube.seat.customer.order;

import ca.ulaval.glo4002.cafe.domain.exception.InvalidMenuOrderException;

public record CoffeeName(String value) {
    public CoffeeName {
        if (!CoffeeTypes.contains(value)) {
            throw new InvalidMenuOrderException();
        }
    }
}
