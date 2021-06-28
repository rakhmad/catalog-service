package id.redhat.demo.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("catalogItemRepository")
public interface CatalogItemRepository extends JpaRepository<CatalogItem, Long> {
    Optional<CatalogItem> findCatalogItemById(Long itemId);

    Optional<CatalogItem> findCatalogItemByItemSKU(String itemSKU);
}
