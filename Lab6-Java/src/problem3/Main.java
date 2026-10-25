package problem3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int capacity;
        do {
            System.out.print("Enter the library capacity (at least 2): ");
            capacity = scanner.nextInt();
        } while (capacity < 2);

        Library library = new Library(capacity);
        library.add(new Novel("The Hobbit", "J.R.R. Tolkien", 310, 15.00));
        library.add(new Magazine("National Geographic", "October", 2026));

        int choice;
        do {
            System.out.println("\n1. Add a document");
            System.out.println("2. Display documents");
            System.out.println("3. Delete a document");
            System.out.println("4. Find a document");
            System.out.println("5. Display authors");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    System.out.println("1. Document");
                    System.out.println("2. Book");
                    System.out.println("3. Magazine");
                    System.out.println("4. Dictionary");
                    System.out.println("5. Novel");
                    System.out.println("6. Textbook");
                    System.out.print("Document type: ");
                    int type = scanner.nextInt();
                    scanner.nextLine();
                    if (type < 1 || type > 6){
                        System.out.println("Invalid document type.");
                        break;
                    }

                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    Document doc;
                    if (type == 1){
                        doc = new Document(title);
                    } else if (type == 3){
                        System.out.print("Month: ");
                        String month = scanner.nextLine();
                        System.out.print("Year: ");
                        int year = scanner.nextInt();
                        doc = new Magazine(title, month, year);
                    } else if (type == 4){
                        System.out.print("Language: ");
                        String language = scanner.nextLine();
                        doc = new Dictionary(title, language);
                    } else {
                        System.out.print("Author: ");
                        String author = scanner.nextLine();
                        System.out.print("Number of pages: ");
                        int nbrPages = scanner.nextInt();
                        scanner.nextLine();
                        if (type == 5){
                            System.out.print("Price: ");
                            double price = scanner.nextDouble();
                            doc = new Novel(title, author, nbrPages, price);
                        } else if (type == 6){
                            System.out.print("Level: ");
                            String level = scanner.nextLine();
                            doc = new Textbook(title, author, nbrPages, level);
                        } else {
                            doc = new Book(title, author, nbrPages);
                        }
                    }

                    if (library.add(doc)){
                        System.out.println("Document added.");
                    } else {
                        System.out.println("The library is full.");
                    }
                    break;
                case 2:
                    library.displayDocuments();
                    break;
                case 3:
                    System.out.print("Record number: ");
                    int deleteNumber = scanner.nextInt();
                    if (library.delete(library.document(deleteNumber))){
                        System.out.println("Document deleted.");
                    } else {
                        System.out.println("Document not found.");
                    }
                    break;
                case 4:
                    System.out.print("Record number: ");
                    int findNumber = scanner.nextInt();
                    Document found = library.document(findNumber);
                    if (found == null){
                        System.out.println("Document not found.");
                    } else {
                        System.out.println(found);
                    }
                    break;
                case 5:
                    library.displayAuthors();
                    break;
                case 0:
                    System.out.println("Goodbye.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
        scanner.close();
    }
}
