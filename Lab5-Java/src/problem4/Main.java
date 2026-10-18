package problem4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ShoppingCart cart = new ShoppingCart();
            String shopAgain;
            do {
                System.out.print("Enter the item name: ");
                String name = scanner.nextLine();
                System.out.print("Enter the item price: ");
                double price = scanner.nextDouble();
                System.out.print("Enter the quantity: ");
                int quantity = scanner.nextInt();

                cart.addToCart(name, price, quantity);
                System.out.println(cart);
                System.out.print("Continue shopping? (y/n): ");
                shopAgain = scanner.next();
                scanner.nextLine();
            } while (shopAgain.equalsIgnoreCase("y"));

            System.out.printf("Please pay %.2f%n", cart.getTotalPrice());
        }
    }
}
