package id.redhat.demo.catalog;

import io.quarkus.hibernate.reactive.panache.PanacheQuery;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CatalogItemRepository implements PanacheRepositoryBase<CatalogItem, Long> {

    public Uni<CatalogItem> findBySku(String sku) {
        PanacheQuery<CatalogItem> query = find("itemSKU", sku);
        return query.firstResult();
    }
}
