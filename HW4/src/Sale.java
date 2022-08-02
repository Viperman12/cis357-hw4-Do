import java.util.ArrayList;
import java.util.List;

public class Sale {
    private List<SalesLineItem> saleLineItems = new ArrayList<>();

    /***
     * Looks through all the List to see if an SLI with the same ProductSpec exist and adds the quantity to that if it exist, otherwise creates a new SLI
     * @param spec ProductSpec Object used to create an SLI
     * @param quantity Integer used to create an SLI
     */
    public void makeLineItem(ProductSpecification spec, int quantity) {
        boolean unique = true;
        for(int i = 0; i < saleLineItems.size(); i++)
            if (spec.getId().equals(saleLineItems.get(i).getSpec().getId()))
            {
                unique = false;
                saleLineItems.get(i).addQuantity(quantity);
                break;
            }

        if (unique)
            saleLineItems.add(new SalesLineItem(spec, quantity));
    }

    /***
     * The sum of all the SLI price multiplied by the quantity of each
     * @return total
     */
    public double getTotal() {
        double sum = 0;
        for(int k = 0; k < saleLineItems.size(); k++) {
            sum += saleLineItems.get(k).getTotal();
        }
        return sum;
    }

    /***
     * The sum of all the SLI price multiplied by the quantity of each with tax on some product
     * @return total with tax
     */
    float getPrice()    {
        float sum = 0;
        for(int k = 0; k < saleLineItems.size(); k++) {
            ProductSpecification ps = saleLineItems.get(k).getSpec();
            if (ps.getId().charAt(0) == 65)
                sum += saleLineItems.get(k).getTotal() * 1.06;
            else
                sum += saleLineItems.get(k).getTotal();
        }
        return sum;
    }

    /***
     * Prints out the quantity, name, and total of each product
     */
    void Summary()  {
        saleLineItems.forEach(sli -> {
            ProductSpecification ps = sli.getSpec();
            System.out.printf("%5d %-13s $ %6.2f%n", sli.getQuantity(), ps.getDescription(), (sli.getQuantity() * ps.getPrice()));
        });
    }
}
