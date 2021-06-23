package id.redhat.demo.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatalogItemController {
    private static final String DEFAULT_RESPONSE = "Hello from Catalog";

    @Autowired
    private CatalogItemRepository catalogItemRepository;

    @GetMapping("/v1/default")
    public String getDefaultResponse() {
        return DEFAULT_RESPONSE;
    }

    @GetMapping("/v1/catalog/items")
    public @ResponseBody Iterable<CatalogItem> getAllCatalogItems() {
        return catalogItemRepository.findAll();
    }
}
