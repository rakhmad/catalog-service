package id.redhat.demo.catalog;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class CatalogItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CatalogService catalogService;

    @Test
    void getAllCatalogItems() throws Exception {
        List<CatalogItem> catalogItemList = new ArrayList<>();
        catalogItemList.add(new CatalogItem("Logitech", "Mouse Bluetooth", 150000));
        catalogItemList.add(new CatalogItem("iPad", "Tablet", 150000000));
        catalogItemList.add(new CatalogItem("Kindle", "E-Reader", 3000000));
        catalogItemList.add(new CatalogItem("Sidola", "Minyak Kayu Putih", 10000));
        catalogItemList.add(new CatalogItem("Blue Yeti", "Microphone", 3000000));
        when(catalogService.getAllCatalogItems()).thenReturn(catalogItemList);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/catalog/items")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(5)))
                .andDo(print());
    }

    @Test
    void getCatalogItemById() throws Exception {
        long itemId = 1500L;
        CatalogItem item = new CatalogItem("Rode", "USB-C Microphone", 2500000);
        item.setId(itemId);
        when(catalogService.getCatalogItemById(itemId)).thenReturn(item);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/catalog/items/id/" + itemId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemName", is(item.getItemName())))
                .andDo(print());
    }

    @Test
    void getNotAvailableItemById() throws Exception {
        when(catalogService.getCatalogItemById(1000)).thenThrow(EntityNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/catalog/items/id/1000")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void getCatalogItemBySKU() throws Exception {
        String itemSKU = "SKU-AAA-BB-123";
        CatalogItem item = new CatalogItem("Rode", "USB-C Microphone", 2500000);
        item.setItemSKU(itemSKU);
        when(catalogService.getCatalogItemBySKU(itemSKU)).thenReturn(item);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/catalog/items/sku/SKU-AAA-BB-123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemName", is(item.getItemName())))
                .andExpect(jsonPath("$.itemSKU", is(item.getItemSKU())))
                .andDo(print());
    }

    @Test
    void getNotAvailableItemBySku() throws Exception {
        when(catalogService.getCatalogItemBySKU("ABCDEFGA")).thenThrow(EntityNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/catalog/items/sku/ABCDEFGA")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

}
