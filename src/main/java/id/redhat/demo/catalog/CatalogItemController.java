package id.redhat.demo.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
