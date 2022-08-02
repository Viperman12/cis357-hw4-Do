import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ProductCatalog {
    private static Map<String, ProductSpecification> productSpecs = new HashMap<>();
    private File file;
    Scanner s = new Scanner(System.in);

    // Prompt user for a file and uses that to create the catalog
    public ProductCatalog() throws FileNotFoundException {
        String filePath;
        System.out.print("Welcome to POST system \n");
        do {
            System.out.print("\nInput File: ");
            filePath = s.nextLine();
        } while(!new File(filePath).exists());

        file = new File(filePath);
        Scanner output = new Scanner(file);
        while (output.hasNext()) {
            String[] line = output.nextLine().split(", ", 3);
            String code = line[0];
            String name = line[1];
            float price = Float.parseFloat(line[2]);
            ProductSpecification ps = new ProductSpecification(code, price, name);
            productSpecs.put(code, ps);
        }
        output.close();
    }

    /***
     * Gets the specific PS using a string
     * @param id String used to find the PS
     * @return PS object
     */
    public ProductSpecification getSpecification(String id) {
        return productSpecs.get(id);
    }

    /***
     * Allows user to make edits to the catalog or just show the list
     * @throws FileNotFoundException
     */
    public void editCatalog() throws FileNotFoundException {
        // Begins the modification of items
        char c = 'A';
        while(!(c == 'Q'))
        {
            System.out.print("\nDo you want to update the items data? (A/D/M/Q): ");
            c = s.nextLine().toUpperCase().charAt(0);

            // Switch Case for the four options. Breaks if input is Q
            switch ((int) c) {
                case 65:
                    addItem();
                    break;
                case 68:
                    deleteItem();
                    break;
                case 77:
                    updateItem();
                    break;
                default:
                    printAllItem();
            }
        }
    }

    /***
     * Adds an item object to an ArrayList and uses a Print Writer to print them onto the file
     * @throws FileNotFoundException
     */
    void addItem() throws FileNotFoundException {
        Scanner s = new Scanner(System.in);

        // Gets code
        String code;
        do {
            System.out.print("item code: ");
            code = s.nextLine();
        } while(!isCode(code));

        // Gets name
        System.out.print("item name: ");
        String name = s.nextLine();

        // Gets price
        String temp;
        do {
            System.out.print("item price: ");
            temp = s.nextLine();
        }  while(!isNum(temp));
        float price = Float.parseFloat(temp);

        productSpecs.put(code, new ProductSpecification(code, price, name));

        writeFile();

        System.out.println("Item add successful");
    }

    /***
     * Deletes an item object to an ArrayList and uses a Print Writer to print them onto the file
     * @throws FileNotFoundException
     */
    void deleteItem() throws FileNotFoundException {
        Scanner s = new Scanner(System.in);

        // Gets code
        String code;
        do {
            System.out.print("item code: ");
            code = s.nextLine();
        } while(!isCode(code));

        productSpecs.remove(code);

        writeFile();

        System.out.println("Item delete successful");
    }

    /***
     * Updates an item object to an ArrayList and uses a Print Writer to print them onto the file
     * @throws FileNotFoundException
     */
    void updateItem() throws FileNotFoundException {
        Scanner s = new Scanner(System.in);

        // Gets Code
        String code;
        do {
            System.out.print("item code: ");
            code = s.nextLine();
        } while(!(isCode(code) && productSpecs.containsKey(code)));

        // Gets Name
        System.out.print("item name: ");
        String name = s.nextLine();

        String temp;
        do {
            System.out.print("item price: ");
            temp = s.nextLine();
        } while(!isNum(temp));
        float price = Float.parseFloat(temp);

        productSpecs.replace(code, new ProductSpecification(code, price, name));

        writeFile();

        System.out.println("Item modify successful");
    }

    /***
     * Prints all elements of an ArrayList of items
     */
    void printAllItem()  {
        System.out.printf("%n%-15s %-15s %-15s%n", "item code", "item name", "unit price");

        productSpecs.forEach((code, ps) -> {
            System.out.printf("%-15s %-15s %-15.2f%n", code, ps.getDescription(), ps.getPrice());
        });

        System.out.println();
    }

    /***
     * Writes all the items on the input file in csv
     * @throws FileNotFoundException
     */
    public void writeFile() throws FileNotFoundException {
        PrintWriter output = new PrintWriter(file);
        productSpecs.forEach((productCode, ps) ->{
            output.println(productSpecs.get(productCode).getId() + ", " + productSpecs.get(productCode).getDescription() + ", " + productSpecs.get(productCode).getPrice());
        });
        output.close();
    }

    /***
     * Determines whether a is a number
     * @param a String that could be a number
     * @return boolean whether String a is a number or not
     */
    static boolean isNum(String a)
    {
        try
        {
            if (a == null)
                throw new IllegalArgumentException("!!! Invalid data type\n");



            double d = Double.parseDouble(a);
//            for (int i = 0; i < a.length(); i++)
//            {
//                int charInt = a.charAt(i);
//                if (!(charInt >= 48 && charInt <= 57))
//                    throw new NumberFormatException("!!! Invalid data type\n");
//            }
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
     * Determines whether a is a code
     * @param a String that could be a code
     * @return boolean whether String a is a code or not
     */
    static boolean isCode(String a)
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
