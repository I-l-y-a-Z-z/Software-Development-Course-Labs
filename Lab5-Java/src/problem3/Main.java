package problem3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int size;
            do {
                System.out.print("Enter the number of values: ");
                size = scanner.nextInt();
                if (size < 0) {
                    System.out.println("The number of values cannot be negative.");
                }
            } while (size < 0);

            int[] array = new int[size];
            System.out.println("Enter the values:");
            for (int i = 0; i < array.length; i++) {
                array[i] = scanner.nextInt();
            }

            System.out.println("Array before reversing:");
            for (int value : array) {
                System.out.print(value + " ");
            }
            System.out.println();

            for (int i = 0, j = array.length - 1; i < j; i++, j--) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }

            System.out.println("Array after reversing:");
            for (int value : array) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
