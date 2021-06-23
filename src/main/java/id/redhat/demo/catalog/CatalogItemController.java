package id.redhat.demo.catalog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatalogItemController {
    private static final String defaultResponse = "Hello from Catalog";

    @GetMapping("/v1/default")
    public String getDefaultResponse() {
        return defaultResponse;
    }
}
