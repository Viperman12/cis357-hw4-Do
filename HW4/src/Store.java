// Homework 4: Sales Register Program (OOP Case)
// Course: CIS357
// Due Date: 8/2/2022
// Name Andy Do
// GitHub: https://github.com/Viperman12/cis357-hw4-Do
// Instructor: Il-Hyung Cho
// Program description: A simulation of a cash register using the oop case

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Store {
    public static void main(String args[]) throws FileNotFoundException {
        //Creates Objects and variable
        ProductCatalog catalog = new ProductCatalog();
        Register register = new Register(catalog);
        Scanner s = new Scanner(System.in);
        double totalSale = 0;

        System.out.print("\nBeginning a new sale (Y/N)\t");
        String ans = s.nextLine().toLowerCase();

        // Begins the sale if Y, gives total sale if N
        while (ans.equals("y")) {
            register.makeNewSale();
            totalSale += register.makePayment();

            System.out.println("----------------------------");
            System.out.print("\nBeginning a new sale (Y/N)\t");
            ans = s.nextLine().toLowerCase();
        }
        System.out.printf("\nThe total sale for the day is $\t%.2f\n\n", totalSale);

        //Prompts user a selection of option to edit the catalog
        catalog.editCatalog();
        System.out.println("Thanks for using POST system. Goodbye");
    }
}
