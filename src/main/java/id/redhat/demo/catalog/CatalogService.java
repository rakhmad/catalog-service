package id.redhat.demo.catalog;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class CatalogService {

    @Inject
    CatalogItemRepository catalogItemRepository;

    public Uni<List<CatalogItem>> getAllCatalogItems() {
        return catalogItemRepository.listAll();
    }

    public Uni<CatalogItem> getCatalogItemById(long id) {
        return catalogItemRepository.findById(id);
    }

    public Uni<CatalogItem> getCatalogItemBySKU(String itemSKU) {
        return catalogItemRepository.findBySku(itemSKU);
    }
}
