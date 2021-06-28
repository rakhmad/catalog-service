package id.redhat.demo.catalog;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CatalogService {
    private final CatalogItemRepository catalogItemRepository;

    public CatalogService(CatalogItemRepository catalogItemRepository) {
        this.catalogItemRepository = catalogItemRepository;
    }

    public List<CatalogItem> getAllCatalogItems() {
        return catalogItemRepository.findAll();
    }

    public CatalogItem getCatalogItemById(long id) {
        return catalogItemRepository.findCatalogItemById(id).orElseThrow(EntityNotFoundException::new);
    }

    public CatalogItem getCatalogItemBySKU(String itemSKU) {
        return catalogItemRepository.findCatalogItemByItemSKU(itemSKU).orElseThrow(EntityNotFoundException::new);
    }
}
