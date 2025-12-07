package id.redhat.demo.catalog;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.util.List;

@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
public class CatalogResource {

    private static final String DEFAULT_RESPONSE = "Hello from Catalog";

    @Inject
    CatalogService catalogService;

    @GET
    @Path("/default")
    public Uni<String> getDefaultResponse() {
        return Uni.createFrom().item(DEFAULT_RESPONSE);
    }

    @GET
    @Path("/catalog/items")
    public Uni<List<CatalogItem>> getAllCatalogItems() {
        return catalogService.getAllCatalogItems();
    }

    @GET
    @Path("/catalog/items/id/{id}")
    public Uni<Response> getCatalogItemById(@PathParam("id") long id) {
        return catalogService.getCatalogItemById(id)
                .onItem().transform(item -> Response.ok(item).build())
                .onFailure().recoverWithItem(() -> Response.status(Status.NOT_FOUND).build());
    }

    @GET
    @Path("/catalog/items/sku/{sku}")
    public Uni<Response> getCatalogItemBySku(@PathParam("sku") String sku) {
        return catalogService.getCatalogItemBySKU(sku)
                .onItem().transform(item -> Response.ok(item).build())
                .onFailure().recoverWithItem(() -> Response.status(Status.NOT_FOUND).build());
    }
}
