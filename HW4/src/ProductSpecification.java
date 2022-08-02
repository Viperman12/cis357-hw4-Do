public class ProductSpecification {
    private String id;
    private float price;
    private String description;

    // Constructor
    public ProductSpecification(String id, float price, String description) {
        this.id = id;
        this.price = price;
        this.description = description;
    }


    // Getters
    public String getId() { return id; }

    public float getPrice() { return price; }

    public String getDescription() { return description; }
}
