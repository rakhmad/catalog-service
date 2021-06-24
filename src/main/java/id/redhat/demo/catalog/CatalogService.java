package id.redhat.demo.catalog;

import org.springframework.stereotype.Service;

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
}
