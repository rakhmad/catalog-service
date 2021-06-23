package id.redhat.demo.catalog;

import java.util.concurrent.ThreadLocalRandom;

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
     */

    private final long id;
    private final String itemName;
    private final Category itemCategory;
    private final String shortDescription;
    private final String longDescription;
    private final double itemPrice;
    private final String imageURI;
    private final boolean itemEnabled;

    private final String DEFAULT_IMAGE_URI = "/image/default.jpg";
    private final String DEFAULT_LONG_LOREM = " Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras iaculis posuere purus sit amet accumsan. Duis non ornare neque. Vestibulum sit amet congue dui, at fermentum quam. Fusce ut lacus a augue eleifend condimentum. Vivamus dictum enim justo, ut sagittis purus finibus malesuada. Quisque a libero non felis tincidunt ornare. Sed consectetur, ligula in porttitor venenatis, arcu felis tempus risus, faucibus rhoncus est mi ac arcu. Integer leo libero, consectetur eget lectus quis, ullamcorper interdum sapien. Phasellus id accumsan quam. Nulla dignissim quis orci at pulvinar. Nam quis vestibulum ante, a vestibulum magna.\n" +
            "\n" +
            "Donec quis commodo leo. Nam quis risus felis. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Donec tincidunt tortor sed magna vulputate, nec posuere velit ornare. Suspendisse volutpat elit odio, id pulvinar erat tincidunt eget. Quisque quis diam ultricies, auctor sapien eget, eleifend mauris. Etiam consectetur posuere mauris, non consequat erat efficitur ac. Sed facilisis leo libero, eget efficitur odio vestibulum at. Nunc accumsan sapien at sapien auctor, in semper dui tincidunt. Vestibulum mattis iaculis tortor. Fusce pellentesque aliquet ligula, et consequat nulla hendrerit id. Morbi tempor, sem at condimentum ultricies, massa nibh tempus erat, vitae molestie enim sapien non tortor. Sed sollicitudin odio ut lacus aliquet, quis mollis sapien auctor. ";

    public CatalogItem(long id, String itemName, Category itemCategory, String shortDescription, String longDescription, double itemPrice, String imageURI) {
        this.id = id;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.itemPrice = itemPrice;
        this.imageURI = imageURI;
        this.itemEnabled = true;

    }

    public CatalogItem(String itemName, String shortDescription, double itemPrice) {
        this.id = ThreadLocalRandom.current().nextLong();
        this.itemName = itemName;
        this.shortDescription = shortDescription;
        this.longDescription = DEFAULT_LONG_LOREM;
        this.itemCategory = Category.GENERIC;
        this.itemPrice = itemPrice;
        this.imageURI = DEFAULT_IMAGE_URI;
        this.itemEnabled = true;
    }

    public long getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public Category getItemCategory() {
        return itemCategory;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public String getImageURI() {
        return imageURI;
    }
}
