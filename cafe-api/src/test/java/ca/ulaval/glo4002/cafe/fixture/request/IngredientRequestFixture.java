package ca.ulaval.glo4002.cafe.fixture.request;

import ca.ulaval.glo4002.cafe.api.menu.request.IngredientRequest;

public class IngredientRequestFixture {
    public int espresso;
    public int water;
    public int chocolate;
    public int milk;

    public IngredientRequestFixture withEspresso(int espresso) {
        this.espresso = espresso;
        return this;
    }

    public IngredientRequestFixture withWater(int water) {
        this.water = water;
        return this;
    }

    public IngredientRequestFixture withChocolate(int chocolate) {
        this.chocolate = chocolate;
        return this;
    }

    public IngredientRequestFixture withMilk(int milk) {
        this.milk = milk;
        return this;
    }

    public IngredientRequest build() {
        IngredientRequest ingredientRequest = new IngredientRequest();
        ingredientRequest.Espresso = espresso;
        ingredientRequest.Water = water;
        ingredientRequest.Chocolate = chocolate;
        ingredientRequest.Milk = milk;
        return ingredientRequest;
    }
}
