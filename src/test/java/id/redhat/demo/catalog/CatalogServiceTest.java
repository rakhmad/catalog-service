package id.redhat.demo.catalog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CatalogServiceTest {

    @Autowired
    private CatalogItemRepository catalogItemRepository;

    @Test
    void getAllTodos() {
        CatalogItem item = new CatalogItem("Logitech", "Mouse Bluetooth", 150000);
        catalogItemRepository.save(item);
        CatalogService catalogService = new CatalogService(catalogItemRepository);

        List<CatalogItem> catalogItemList = catalogService.getAllCatalogItems();
        CatalogItem catalogItem = catalogItemList.get(catalogItemList.size() - 1);

        assertEquals(item.getItemName(), catalogItem.getItemName());
        assertEquals(item.getId(), catalogItem.getId());
        assertEquals(item.getItemPrice(), catalogItem.getItemPrice());
    }
}
