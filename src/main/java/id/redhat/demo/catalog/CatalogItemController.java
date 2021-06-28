package id.redhat.demo.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
public class CatalogItemController {
    private static final String DEFAULT_RESPONSE = "Hello from Catalog";

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/v1/default")
    public String getDefaultResponse() {
        return DEFAULT_RESPONSE;
    }

    @GetMapping("/v1/catalog/items")
    public ResponseEntity<List<CatalogItem>> getAllCatalogItems() {
        return new ResponseEntity<>(catalogService.getAllCatalogItems(), HttpStatus.OK);
    }

    @GetMapping("/v1/catalog/items/id/{id}")
    public ResponseEntity<CatalogItem> getCatalogItemById(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(catalogService.getCatalogItemById(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/v1/catalog/items/sku/{sku}")
    public ResponseEntity<CatalogItem> getCatalogItemBySku(@PathVariable("sku") String itemSKU) {
        try {
            return new ResponseEntity<>(catalogService.getCatalogItemBySKU(itemSKU), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
