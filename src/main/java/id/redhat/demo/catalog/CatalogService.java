package id.redhat.demo.catalog;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class CatalogService {

    @Inject
    CatalogItemRepository catalogItemRepository;

    public Uni<List<CatalogItem>> getAllCatalogItems() {
        return catalogItemRepository.listAll();
    }

    public Uni<CatalogItem> getCatalogItemById(long id) {
        return catalogItemRepository.findByItemId(id)
                .onItem().ifNull().failWith(() -> new NotFoundException("Catalog item not found"));
    }

    public Uni<CatalogItem> getCatalogItemBySKU(String itemSKU) {
        return catalogItemRepository.findByItemSku(itemSKU)
                .onItem().ifNull().failWith(() -> new NotFoundException("Catalog item not found"));
    }
}
