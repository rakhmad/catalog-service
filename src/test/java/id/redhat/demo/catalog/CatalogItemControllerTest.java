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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(5)))
                .andDo(print());
    }
}
