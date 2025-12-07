package id.redhat.demo.catalog;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
class CatalogResourceTest {

    @Inject
    CatalogItemRepository catalogItemRepository;

    private CatalogItem mouse;
    private CatalogItem microphone;

    @BeforeEach
    void setUp() {
        catalogItemRepository.deleteAll().await().indefinitely();

        mouse = new CatalogItem("Logitech", "Mouse Bluetooth", 150000);
        mouse.setItemSKU("SKU-TES-001");

        microphone = new CatalogItem("Rode", "USB-C Microphone", 2500000);
        microphone.setItemSKU("SKU-TES-002");

        catalogItemRepository.persist(mouse).await().indefinitely();
        catalogItemRepository.persist(microphone).await().indefinitely();
    }

    @Test
    void shouldReturnDefaultMessage() {
        given()
                .accept(ContentType.TEXT)
                .when().get("/v1/default")
                .then()
                .statusCode(200)
                .body(is("Hello from Catalog"));
    }

    @Test
    void shouldListAllCatalogItems() {
        given()
                .accept(ContentType.JSON)
                .when().get("/v1/catalog/items")
                .then()
                .statusCode(200)
                .body("size()", is(2));
    }

    @Test
    void shouldFindCatalogItemById() {
        given()
                .accept(ContentType.JSON)
                .when().get("/v1/catalog/items/id/" + mouse.id)
                .then()
                .statusCode(200)
                .body("itemName", is(mouse.getItemName()))
                .body("itemSKU", is(mouse.getItemSKU()));
    }

    @Test
    void shouldReturnNotFoundForMissingId() {
        given()
                .accept(ContentType.JSON)
                .when().get("/v1/catalog/items/id/9999")
                .then()
                .statusCode(404);
    }

    @Test
    void shouldFindCatalogItemBySku() {
        given()
                .accept(ContentType.JSON)
                .when().get("/v1/catalog/items/sku/" + microphone.getItemSKU())
                .then()
                .statusCode(200)
                .body("itemName", is(microphone.getItemName()))
                .body("itemSKU", is(microphone.getItemSKU()));
    }

    @Test
    void shouldReturnNotFoundForMissingSku() {
        given()
                .accept(ContentType.JSON)
                .when().get("/v1/catalog/items/sku/SKU-NOT-FOUND")
                .then()
                .statusCode(404);
    }
}
