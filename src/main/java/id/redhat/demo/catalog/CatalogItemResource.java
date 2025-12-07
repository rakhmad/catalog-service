package id.redhat.demo.catalog;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
public class CatalogItemResource {

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
                .onItem().ifNotNull().transform(item -> Response.ok(item).build())
                .onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND)::build);
    }

    @GET
    @Path("/catalog/items/sku/{sku}")
    public Uni<Response> getCatalogItemBySku(@PathParam("sku") String itemSKU) {
        return catalogService.getCatalogItemBySKU(itemSKU)
                .onItem().ifNotNull().transform(item -> Response.ok(item).build())
                .onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND)::build);
    }
}
