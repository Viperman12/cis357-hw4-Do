public class SalesLineItem {
    private int quantity;
    private ProductSpecification spec;

    // Constructor
    SalesLineItem (ProductSpecification spec, int quantity) {
        this.spec = spec;
        this.quantity = quantity;
    }

    // Getters
    float getTotal()   { return spec.getPrice() * quantity; }

    int getQuantity() { return quantity; }

    ProductSpecification getSpec() { return spec; }

    // Setters
    void addQuantity(int quantity) { this.quantity += quantity; }
}
