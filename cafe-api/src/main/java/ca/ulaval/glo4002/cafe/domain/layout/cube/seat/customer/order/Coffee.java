package ca.ulaval.glo4002.cafe.domain.layout.cube.seat.customer.order;

import ca.ulaval.glo4002.cafe.domain.layout.cube.seat.customer.Amount;

public record Coffee(CoffeeName coffeeName) {
    public Amount price() {
        return CoffeeTypes.getCoffeeType(coffeeName).price();
    }

    public Recipe recipe() {
        return CoffeeTypes.getCoffeeType(coffeeName).recipe();
    }
}
