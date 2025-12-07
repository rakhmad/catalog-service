package id.redhat.demo.catalog;

import io.quarkus.test.junit.QuarkusTest;
import io.rest-assured.http.ContentType;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
class CatalogResourceTest {

    @Inject
    CatalogItemRepository repository;

    private CatalogItem monitor;
    private CatalogItem microphone;

    @BeforeEach
    void setup() {
        repository.deleteAll().await().atMost(Duration.ofSeconds(5));

        monitor = new CatalogItem("BenQ", "Monitor", 2500000);
        monitor.setItemSKU("SKU-DIS-PLAY-01");
        monitor.setItemUPC("UPC-DIS-PLAY-01");

        microphone = new CatalogItem("Rode", "USB-C Microphone", 3500000);
        microphone.setItemSKU("SKU-MIC-USB-01");
        microphone.setItemUPC("UPC-MIC-USB-01");

        persistItem(monitor);
        persistItem(microphone);
    }

    @Test
    void shouldReturnDefaultResponse() {
        given()
                .when().get("/v1/default")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(equalTo("\"Hello from Catalog\""));
    }

    @Test
    void shouldReturnAllCatalogItems() {
        given()
                .when().get("/v1/catalog/items")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("", hasSize(2));
    }

    @Test
    void shouldReturnItemById() {
        given()
                .when().get("/v1/catalog/items/id/" + monitor.getId())
                .then()
                .statusCode(200)
                .body("itemName", equalTo(monitor.getItemName()))
                .body("itemSKU", equalTo(monitor.getItemSKU()));
    }

    @Test
    void shouldReturnNotFoundForMissingId() {
        given()
                .when().get("/v1/catalog/items/id/9999")
                .then()
                .statusCode(404);
    }

    @Test
    void shouldReturnItemBySku() {
        given()
                .when().get("/v1/catalog/items/sku/" + microphone.getItemSKU())
                .then()
                .statusCode(200)
                .body("itemName", equalTo(microphone.getItemName()))
                .body("itemSKU", equalTo(microphone.getItemSKU()));
    }

    @Test
    void shouldReturnNotFoundForMissingSku() {
        given()
                .when().get("/v1/catalog/items/sku/NON-EXISTENT")
                .then()
                .statusCode(404);
    }

    private void persistItem(CatalogItem item) {
        Uni<Void> persist = repository.persist(item);
        persist.await().atMost(Duration.ofSeconds(5));
    }
}
