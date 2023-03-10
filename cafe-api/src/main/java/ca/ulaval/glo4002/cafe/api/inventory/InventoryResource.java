package ca.ulaval.glo4002.cafe.api.inventory;

import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import ca.ulaval.glo4002.cafe.api.inventory.assembler.InventoryResponseAssembler;
import ca.ulaval.glo4002.cafe.api.inventory.request.InventoryRequest;
import ca.ulaval.glo4002.cafe.api.inventory.response.InventoryResponse;
import ca.ulaval.glo4002.cafe.service.CafeService;
import ca.ulaval.glo4002.cafe.service.parameter.IngredientsParams;

@Produces(MediaType.APPLICATION_JSON)
@Path("/inventory")
public class InventoryResource {
    private final InventoryResponseAssembler inventoryResponseAssembler = new InventoryResponseAssembler();
    private final CafeService cafeService;

    public InventoryResource(CafeService cafeService) {
        this.cafeService = cafeService;
    }

    @GET
    @Path("/")
    public Response getInventory() {
        InventoryResponse response = inventoryResponseAssembler.toInventoryResponse(cafeService.getInventory());
        return Response.ok(response).build();
    }

    @PUT
    @Path("/")
    public Response putInventory(@Valid InventoryRequest inventoryRequest) {
        IngredientsParams ingredientsParams =
            IngredientsParams.from(inventoryRequest.Chocolate, inventoryRequest.Milk, inventoryRequest.Water, inventoryRequest.Espresso);
        cafeService.addIngredientsToInventory(ingredientsParams);
        return Response.status(200).build();
    }
}
