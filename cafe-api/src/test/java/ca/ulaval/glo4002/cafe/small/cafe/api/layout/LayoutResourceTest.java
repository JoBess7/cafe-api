package ca.ulaval.glo4002.cafe.small.cafe.api.layout;

import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.cafe.api.layout.LayoutResource;
import ca.ulaval.glo4002.cafe.domain.CafeName;
import ca.ulaval.glo4002.cafe.service.CafeService;
import ca.ulaval.glo4002.cafe.service.customer.CustomerService;
import ca.ulaval.glo4002.cafe.service.dto.LayoutDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

public class LayoutResourceTest {
    private static final CafeName A_CAFE_NAME = new CafeName("Bob");
    private static final LayoutDTO A_LAYOUT_DTO = new LayoutDTO(A_CAFE_NAME, new ArrayList<>());

    private CafeService cafeService;
    private CustomerService customerService;
    private LayoutResource layoutResource;

    @BeforeEach
    public void createLayoutResource() {
        cafeService = mock(CafeService.class);
        customerService = mock(CustomerService.class);
        layoutResource = new LayoutResource(cafeService, customerService);
    }

    @Test
    public void whenGettingLayout_shouldGetLayout() {
        when(cafeService.getLayout()).thenReturn(A_LAYOUT_DTO);

        layoutResource.layout();

        verify(cafeService).getLayout();
    }

    @Test
    public void whenGettingLayout_shouldReturn200() {
        when(cafeService.getLayout()).thenReturn(A_LAYOUT_DTO);

        Response response = layoutResource.layout();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}
