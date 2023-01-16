package ca.ulaval.glo4002.cafe.domain.layout.cube.seat.customer.order;

import ca.ulaval.glo4002.cafe.domain.layout.cube.seat.customer.Amount;

public record CoffeeType(String name, Amount price, Recipe recipe, boolean isDefaultType) {
}
