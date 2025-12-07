package id.redhat.demo.catalog;

import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CatalogItemRepository implements PanacheRepositoryBase<CatalogItem, Long> {

    public Uni<CatalogItem> findByItemId(long itemId) {
        return findById(itemId);
    }

    public Uni<CatalogItem> findByItemSku(String itemSKU) {
        return find("itemSKU", itemSKU).firstResult();
    }
}
