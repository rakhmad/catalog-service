package id.redhat.demo.catalog;

import javax.persistence.*;

@Entity
@Table(name = "catalog_item")
public class CatalogItem {
    /**
     * Catalog contains multiple CatalogItems
     * CatalogItems is representation for an item in the catalog.
     * Properties for an CatalogItem
     * 1. id
     * 2. Name
     * 3. Category
     * 4. Short Description
     * 5. Long Description
     * 6. Price
     * 7. Image
     * 8. Enabled (can be seen)
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String itemName;
    @Column(nullable = false)
    private Category itemCategory = Category.GENERIC;
    @Column(nullable = false)
    private String shortDescription;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String longDescription = "Long Default Description";
    @Column(precision = 2)
    private double itemPrice;
    @Column(name = "image_uri")
    private String imageURI = "/images/default.png";
    @Column(nullable = false)
    private boolean itemEnabled = true;

    public CatalogItem(String itemName, Category itemCategory, String shortDescription, String longDescription, double itemPrice, String imageURI) {
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.itemPrice = itemPrice;
        this.imageURI = imageURI;
    }

    public CatalogItem(String itemName, String shortDescription, double itemPrice) {
        this.itemName = itemName;
        this.shortDescription = shortDescription;
        this.itemPrice = itemPrice;
    }

    public CatalogItem() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Category getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(Category itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    public void setItemEnabled(boolean itemEnabled) {
        this.itemEnabled = itemEnabled;
    }
}
