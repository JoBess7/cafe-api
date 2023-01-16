package ca.ulaval.glo4002.cafe.fixture.request;

import ca.ulaval.glo4002.cafe.api.menu.request.IngredientRequest;
import ca.ulaval.glo4002.cafe.api.menu.request.MenuRequest;

public class MenuRequestFixture {
    public String name = "Pumpkin";
    public float cost = 5.5f;
    public IngredientRequest ingredientRequest = new IngredientRequestFixture()
        .withChocolate(10)
        .withEspresso(20)
        .withMilk(30)
        .withWater(0)
        .build();

    public MenuRequestFixture withName(String name) {
        this.name = name;
        return this;
    }

    public MenuRequestFixture withIngredients(String name) {
        this.name = name;
        return this;
    }

    public MenuRequestFixture withCost(String name) {
        this.name = name;
        return this;
    }

    public MenuRequest build() {
        MenuRequest menuRequest = new MenuRequest();
        menuRequest.name = name;
        menuRequest.ingredients = ingredientRequest;
        menuRequest.cost = cost;
        return menuRequest;
    }
}
