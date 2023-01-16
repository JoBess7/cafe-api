package ca.ulaval.glo4002.cafe.large;

import java.util.List;

import io.restassured.response.Response;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.cafe.api.customer.request.OrderRequest;
import ca.ulaval.glo4002.cafe.api.inventory.request.InventoryRequest;
import ca.ulaval.glo4002.cafe.api.menu.request.MenuRequest;
import ca.ulaval.glo4002.cafe.api.operation.request.CheckInRequest;
import ca.ulaval.glo4002.cafe.domain.inventory.Ingredient;
import ca.ulaval.glo4002.cafe.domain.inventory.IngredientType;
import ca.ulaval.glo4002.cafe.domain.inventory.Quantity;
import ca.ulaval.glo4002.cafe.domain.layout.cube.seat.customer.Amount;
import ca.ulaval.glo4002.cafe.domain.layout.cube.seat.customer.order.CoffeeName;
import ca.ulaval.glo4002.cafe.domain.layout.cube.seat.customer.order.CoffeeType;
import ca.ulaval.glo4002.cafe.domain.layout.cube.seat.customer.order.CoffeeTypes;
import ca.ulaval.glo4002.cafe.domain.layout.cube.seat.customer.order.Recipe;
import ca.ulaval.glo4002.cafe.fixture.request.CheckInRequestFixture;
import ca.ulaval.glo4002.cafe.fixture.request.InventoryRequestFixture;
import ca.ulaval.glo4002.cafe.fixture.request.MenuRequestFixture;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuResourceEnd2EndTest {
    private static final String BASE_URL = "http://localhost:8181";
    private static final int ENOUGH_STOCK = 10000;

    private TestServer server;

    @BeforeEach
    public void startServer() throws Exception {
        server = new TestServer();
        server.start();
    }

    @AfterEach
    public void closeServer() throws Exception {
        server.stop();
    }

    @Test
    public void whenPostNewCoffeeType_shouldCreateType() {
        MenuRequest menuRequest = new MenuRequestFixture().build();
        CoffeeType coffeeType = getCoffeeTypeFromRequest(menuRequest);

        given().contentType("application/json").body(menuRequest).when().post(BASE_URL + "/menu");

        assertEquals(coffeeType, CoffeeTypes.getCoffeeType(new CoffeeName(menuRequest.name)));
    }

    @Test
    public void givenNewCoffeeType_whenOrderNewCoffeeType_shouldReturn200() {
        fullInventory();
        MenuRequest menuRequest = new MenuRequestFixture().build();
        given().contentType("application/json").body(menuRequest).when().post(BASE_URL + "/menu");
        CheckInRequest checkInRequest = new CheckInRequestFixture().withCustomerId("1").build();
        given().contentType("application/json").body(checkInRequest).post(BASE_URL + "/check-in");

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.orders = List.of(menuRequest.name);
        Response response = given().contentType("application/json").body(orderRequest).put(BASE_URL + "/customers/" + "1" + "/orders");

        assertEquals(200, response.getStatusCode());
    }

    private CoffeeType getCoffeeTypeFromRequest(MenuRequest menuRequest) {
        return new CoffeeType(menuRequest.name, new Amount(menuRequest.cost),
            new Recipe(List.of(
                new Ingredient(IngredientType.Espresso, new Quantity(menuRequest.ingredients.Espresso)),
                new Ingredient(IngredientType.Water, new Quantity(menuRequest.ingredients.Water)),
                new Ingredient(IngredientType.Chocolate, new Quantity(menuRequest.ingredients.Chocolate)),
                new Ingredient(IngredientType.Milk, new Quantity(menuRequest.ingredients.Milk)))), false);
    }

    private void fullInventory() {
        InventoryRequest inventoryRequest =
            new InventoryRequestFixture().withChocolate(ENOUGH_STOCK).withEspresso(ENOUGH_STOCK).withMilk(ENOUGH_STOCK).withWater(ENOUGH_STOCK).build();
        given().contentType("application/json").body(inventoryRequest).put(BASE_URL + "/inventory");
    }
}
