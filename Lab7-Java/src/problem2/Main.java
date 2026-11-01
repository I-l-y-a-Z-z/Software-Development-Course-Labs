package problem2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            HelpDesk desk = new HelpDesk();
            if (args.length > 0 && args[0].equals("--sample-input")) {
                readCustomInput(scanner, desk);
                printReport(desk);
                return;
            }
            while (true) {
                System.out.println("1. Add employee  2. Add ticket  3. Complete ticket  4. Report  0. Exit");
                if (!scanner.hasNextLine()) return;
                String choice = scanner.nextLine().trim();
                try {
                    switch (choice) {
                        case "1" -> {
                            System.out.println("Full name,point level,categories separated by spaces:");
                            if (!scanner.hasNextLine()) return;
                            desk.addEmployee(parseEmployee(scanner.nextLine()));
                        }
                        case "2" -> {
                            System.out.println("ID,name,category,points:");
                            if (!scanner.hasNextLine()) return;
                            desk.addTicket(parseTicket(scanner.nextLine()));
                        }
                        case "3" -> {
                            System.out.println("Employee full name,ticket ID:");
                            if (!scanner.hasNextLine()) return;
                            int before = desk.getWaitingTicketCount();
                            complete(desk, scanner.nextLine());
                            System.out.println(before > desk.getWaitingTicketCount()
                                    ? "Ticket completed." : "Ticket not completed. Check employee, ticket, category and point level.");
                        }
                        case "4" -> printReport(desk);
                        case "0" -> { return; }
                        default -> System.out.println("Choose a menu option.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid input: " + e.getMessage());
                }
            }
        }
    }

    private static String[] fields(String line, int count) {
        String[] fields = line.split(",", -1);
        if (fields.length != count) throw new IllegalArgumentException("Expected " + count + " comma-separated fields");
        for (int i = 0; i < fields.length; i++) fields[i] = fields[i].trim();
        return fields;
    }

    private static Employee parseEmployee(String line) {
        String[] f = fields(line, 3);
        List<Category> categories = new ArrayList<>();
        if (!f[2].isEmpty()) {
            for (String category : f[2].split("\\s+")) categories.add(Category.valueOf(category));
        }
        return new Employee(f[0], Integer.parseInt(f[1]), categories);
    }

    private static Ticket parseTicket(String line) {
        String[] f = fields(line, 4);
        return new Ticket(Integer.parseInt(f[0]), f[1], Category.valueOf(f[2]), Integer.parseInt(f[3]));
    }

    private static void complete(HelpDesk desk, String line) {
        String[] f = fields(line, 2);
        desk.completeTicket(f[0], Integer.parseInt(f[1]));
    }

    private static void readCustomInput(Scanner scanner, HelpDesk desk) {
        int employees = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < employees; i++) desk.addEmployee(parseEmployee(scanner.nextLine()));
        int tickets = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < tickets; i++) desk.addTicket(parseTicket(scanner.nextLine()));
        int calls = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < calls; i++) complete(desk, scanner.nextLine());
    }

    private static void printReport(HelpDesk desk) {
        System.out.println("WaitingTicketCount:" + desk.getWaitingTicketCount());
        System.out.println("CompletedTicketsTotalPoint:" + desk.getCompletedTicketsTotalPoint());
        System.out.println("TicketsTotalPointByCategory:");
        for (CategoryNode node : desk.getTicketsTotalPointByCategory()) {
            System.out.println(node.category + ":" + node.totalPoint);
        }
        System.out.println("TicketsTotalPointByEmployee:");
        for (EmployeeNode node : desk.getTicketsTotalPointByEmployee()) {
            System.out.println(node.employee.getFullName() + ":" + node.totalPoint);
        }
    }
}
