import java.util.Scanner;

public class Register {
    private Sale sale;
    private ProductCatalog catalog;
    Scanner s = new Scanner(System.in);

    Register(ProductCatalog catalog)    {
        this.catalog = catalog;
    }

    /***
     * Makes a new Sale Object and allows user to enter item code and quantity
     */
    public void makeNewSale() {
        sale = new Sale();
        String input = "";

        System.out.println("----------------------------");

        // Adds items to the sale object
        while (!input.equals("-1")) {
            String code = null;
            System.out.print("Enter product code: ");
            input = s.nextLine();
            if (input.equals("0000"))
            {
                catalog.printAllItem();
                continue;
            }
            else if (input.equals("-1"))
                break;
            else if (isCode(input))
                code = input;
            else    {
                continue;
            }

            System.out.print("\t\t item name: " + catalog.getSpecification(code).getDescription() + "\n");
            boolean validQuantity = false;
            do {
                System.out.print("Enter quantity:\t\t");
                input = s.nextLine();
                if (isNum(input))
                    validQuantity = true;
            } while (!validQuantity);
            int quant = Integer.parseInt(input);

            enterItem(code, quant);
            System.out.printf("\t\titem total: $ %6.2f%n", (catalog.getSpecification(code).getPrice() * quant));
        }
    }

    /***
     * Passes a string and an integer and create a line item
     * @param id code of the item
     * @param quantity How many of the item will be entered
     */
    public void enterItem(String id, int quantity) { sale.makeLineItem(catalog.getSpecification(id), quantity); }

    /***
     * Gives the total and price of all the items and prompts user to put in a tender amount
     * @return the total so it can be added to the totalSale
     */
    public double makePayment()    {
        double total = sale.getTotal();
        double price = sale.getPrice();
        double tender;

        System.out.println("----------------------------\nItem Listed:");
        sale.Summary();
        System.out.printf("Subtotal\t\t\t$ %6.2f%n", total);
        System.out.printf("%s %6.2f%n", "Total with Tax (6%) $", price);
        do {
            if (price < 10)
                System.out.printf("Tendered amount \t$   ");
            else
                System.out.printf("Tendered amount \t$  ");
            tender = Double.parseDouble(s.nextLine());
        } while (!amountIsEnough(tender, price));
        double change = tender - price;

        System.out.printf("Change\t\t\t\t$ %6.2f%n", change);

        return total;
    }

    /***
     * Determines whether tender is enough to pay price
     * @param tender Amount that should be greater than price
     * @param price Amount that should be less than tender
     * @return boolean whether tender is greater than price
     */
    boolean amountIsEnough(double tender, double price)
    {

        try
        {
            if (price > tender)
                throw new IllegalArgumentException("!!! Not enough. Enter again.");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /***
     * Determines whether a is a code
     * @param a String that could be a code
     * @return boolean whether String a is a code or not
     */
    boolean isNum(String a)
    {
        try
        {
            if (a == null)
                throw new IllegalArgumentException("!!! Invalid data type\n");

            double d = Double.parseDouble(a);
        }
        catch(NumberFormatException e)
        {
            System.out.println("!!! Invalid data type\n");
            return false;
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    /***
     * Determines whether a is a number
     * @param a String that could be a number
     * @return boolean whether String a is a number or not
     */
    boolean isCode(String a)
    {
        try
        {
            int ascii = a.charAt(0);
            if (a == null || !(ascii >= 65 && ascii <= 90))
                throw new NumberFormatException("!!! Invalid data type\n");

            if (a.length() != 4)
                throw new NumberFormatException("!!! Invalid Product code\n");

            return true;
        }
        catch(NumberFormatException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
