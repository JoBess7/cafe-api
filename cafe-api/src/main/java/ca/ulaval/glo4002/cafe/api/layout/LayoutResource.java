package ca.ulaval.glo4002.cafe.api.layout;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import ca.ulaval.glo4002.cafe.api.layout.assembler.LayoutResponseAssembler;
import ca.ulaval.glo4002.cafe.api.layout.response.LayoutResponse;
import ca.ulaval.glo4002.cafe.service.CafeService;
import ca.ulaval.glo4002.cafe.service.customer.CustomerService;

@Path("/layout")
@Produces(MediaType.APPLICATION_JSON)
public class LayoutResource {
    private final CafeService cafeService;
    private final LayoutResponseAssembler layoutResponseAssembler = new LayoutResponseAssembler();

    public LayoutResource(CafeService cafeService, CustomerService customersService) {
        this.cafeService = cafeService;
    }
    @GET
    public Response layout() {
        LayoutResponse response = layoutResponseAssembler.toLayoutResponse(cafeService.getLayout());
        return Response.ok(response).build();
    }
}
