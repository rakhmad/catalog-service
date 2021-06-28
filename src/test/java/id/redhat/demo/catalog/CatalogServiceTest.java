package id.redhat.demo.catalog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CatalogServiceTest {

    @Autowired
    private CatalogItemRepository catalogItemRepository;

    @Test
    void itShouldReturnAllAvailableCatalogItems() {
        CatalogItem expected = new CatalogItem("Logitech", "Mouse Bluetooth", 150000);
        catalogItemRepository.save(expected);
        CatalogService catalogService = new CatalogService(catalogItemRepository);

        List<CatalogItem> catalogItemList = catalogService.getAllCatalogItems();
        CatalogItem result = catalogItemList.get(catalogItemList.size() - 1);

        assertEquals(expected.getItemName(), result.getItemName());
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getItemPrice(), result.getItemPrice());
    }

    @Test
    void itShouldReturnSingleCatalogItem() {
        CatalogItem expected = new CatalogItem("Logitech", "Mouse Bluetooth", 150000);
        catalogItemRepository.save(expected);
        CatalogService catalogService = new CatalogService(catalogItemRepository);

        CatalogItem result = catalogService.getCatalogItemById(expected.getId());
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getItemName(), result.getItemName());
        assertEquals(expected.getItemPrice(), result.getItemPrice());
    }

    @Test
    void itShouldThrowExceptionWhenNoIdFound() {
        CatalogService catalogService = new CatalogService(catalogItemRepository);
        assertThrows(EntityNotFoundException.class,
                () -> {
                    CatalogItem result = catalogService.getCatalogItemById(0);
                });
    }

    @Test
    void itShouldSearchBySKU() {
        String itemSKU = "SKU-TES-T1-123";
        CatalogItem expected = new CatalogItem("Logitech", "Mouse Bluetooth", 150000);
        expected.setItemSKU(itemSKU);
        catalogItemRepository.save(expected);
        CatalogService catalogService = new CatalogService(catalogItemRepository);

        CatalogItem result = catalogService.getCatalogItemBySKU(itemSKU);
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getItemUPC(), result.getItemUPC());
        assertEquals(expected.getItemSKU(), result.getItemSKU());
        assertEquals(expected.getItemName(), result.getItemName());
        assertEquals(expected.getItemPrice(), result.getItemPrice());

    }

    @Test
    void itShouldThrowExceptionWhenNoSKUFound() {
        CatalogService catalogService = new CatalogService(catalogItemRepository);
        assertThrows(EntityNotFoundException.class,
                () -> {
                    CatalogItem result = catalogService.getCatalogItemBySKU("0000000");
                });
    }
}
