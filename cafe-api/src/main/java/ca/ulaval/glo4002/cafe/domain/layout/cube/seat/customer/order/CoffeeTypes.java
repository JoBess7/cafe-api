package ca.ulaval.glo4002.cafe.domain.layout.cube.seat.customer.order;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.cafe.domain.exception.DuplicateCoffeeTypeException;
import ca.ulaval.glo4002.cafe.domain.exception.InvalidMenuOrderException;
import ca.ulaval.glo4002.cafe.domain.inventory.Ingredient;
import ca.ulaval.glo4002.cafe.domain.inventory.IngredientType;
import ca.ulaval.glo4002.cafe.domain.inventory.Quantity;
import ca.ulaval.glo4002.cafe.domain.layout.cube.seat.customer.Amount;

public class CoffeeTypes {
    private static final List<CoffeeType> types = new ArrayList<>();

    static {
        types.add(new CoffeeType("Americano", new Amount(2.25f), new Recipe(List.of(
            new Ingredient(IngredientType.Espresso, new Quantity(50)),
            new Ingredient(IngredientType.Water, new Quantity(50)))), true));

        types.add(new CoffeeType("Dark Roast", new Amount(2.10f), new Recipe(List.of(
            new Ingredient(IngredientType.Espresso, new Quantity(50)),
            new Ingredient(IngredientType.Water, new Quantity(50)))), true));

        types.add(new CoffeeType("Cappuccino", new Amount(3.29f), new Recipe(List.of(
            new Ingredient(IngredientType.Espresso, new Quantity(50)),
            new Ingredient(IngredientType.Water, new Quantity(40)),
            new Ingredient(IngredientType.Milk, new Quantity(10)))), true));

        types.add(new CoffeeType("Espresso", new Amount(2.95f), new Recipe(List.of(
            new Ingredient(IngredientType.Espresso, new Quantity(60)))), true));

        types.add(new CoffeeType("Flat White", new Amount(3.75f), new Recipe(List.of(
            new Ingredient(IngredientType.Espresso, new Quantity(50)),
            new Ingredient(IngredientType.Milk, new Quantity(50)))), true));

        types.add(new CoffeeType("Latte", new Amount(2.95f), new Recipe(List.of(
            new Ingredient(IngredientType.Espresso, new Quantity(50)),
            new Ingredient(IngredientType.Milk, new Quantity(50)))), true));

        types.add(new CoffeeType("Macchiato", new Amount(4.75f), new Recipe(List.of(
            new Ingredient(IngredientType.Espresso, new Quantity(80)),
            new Ingredient(IngredientType.Milk, new Quantity(20)))), true));

        types.add(new CoffeeType("Mocha", new Amount(4.15f), new Recipe(List.of(
            new Ingredient(IngredientType.Espresso, new Quantity(50)),
            new Ingredient(IngredientType.Milk, new Quantity(40)),
            new Ingredient(IngredientType.Chocolate, new Quantity(10)))), true));
    }

    public static void addNewType(CoffeeType coffeeType) {
        if (types.stream().anyMatch(type -> type.name().equals(coffeeType.name()))) {
            throw new DuplicateCoffeeTypeException();
        }
        types.add(coffeeType);
    }

    public static void removeNewTypes() {
        types.removeIf(coffeeType -> !coffeeType.isDefaultType());
    }

    public static CoffeeType getCoffeeType(CoffeeName name) {
        for (CoffeeType type: types) {
            if (type.name().equals(name.value())) {
                return type;
            }
        }
        throw new InvalidMenuOrderException();
    }

    public static boolean contains(String name) {
        for (CoffeeType type : types) {
            if (type.name().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
