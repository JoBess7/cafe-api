package ca.ulaval.glo4002.cafe.api.menu;

import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import ca.ulaval.glo4002.cafe.api.menu.request.MenuRequest;
import ca.ulaval.glo4002.cafe.service.CafeService;
import ca.ulaval.glo4002.cafe.service.parameter.MenuParams;

@Produces(MediaType.APPLICATION_JSON)
@Path("/menu")
public class MenuResource {
    private final CafeService cafeService;

    public MenuResource(CafeService cafeService) {
        this.cafeService = cafeService;
    }

    @POST
    public Response putMenu(@Valid MenuRequest menuRequest) {
        MenuParams menuParams = MenuParams.from(menuRequest.name, menuRequest.cost, menuRequest.ingredients.Espresso,
            menuRequest.ingredients.Water, menuRequest.ingredients.Chocolate, menuRequest.ingredients.Milk);
        cafeService.addCoffeeType(menuParams);
        return Response.status(200).build();
    }
}
