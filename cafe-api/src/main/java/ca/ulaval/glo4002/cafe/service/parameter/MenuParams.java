package ca.ulaval.glo4002.cafe.service.parameter;

import java.util.List;

import ca.ulaval.glo4002.cafe.domain.inventory.Ingredient;
import ca.ulaval.glo4002.cafe.domain.inventory.IngredientType;
import ca.ulaval.glo4002.cafe.domain.inventory.Quantity;
import ca.ulaval.glo4002.cafe.domain.layout.cube.seat.customer.Amount;
import ca.ulaval.glo4002.cafe.domain.layout.cube.seat.customer.order.Recipe;

public record MenuParams(String name, Amount cost, Recipe recipe) {
    public MenuParams(String name, float cost, int espresso, int water, int chocolate, int milk) {
        this(
            name,
            new Amount(cost),
            new Recipe(List.of(
                new Ingredient(IngredientType.Espresso, new Quantity(espresso)),
                new Ingredient(IngredientType.Water, new Quantity(water)),
                new Ingredient(IngredientType.Chocolate, new Quantity(chocolate)),
                new Ingredient(IngredientType.Milk, new Quantity(milk))))
        );
    }

    public static MenuParams from(String name, float cost, int espresso, int water, int chocolate, int milk) {
        return new MenuParams(name, cost, espresso, water, chocolate, milk);
    }
}
