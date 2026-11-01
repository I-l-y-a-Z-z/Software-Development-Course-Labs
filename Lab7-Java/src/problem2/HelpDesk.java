package problem2;

import java.util.ArrayList;
import java.util.List;

public class HelpDesk implements IHelpDesk {
    private final List<IEmployee> employees = new ArrayList<>();
    private final List<ITicket> tickets = new ArrayList<>();

    public void addTicket(ITicket ticket) {
        if (ticket == null) throw new IllegalArgumentException("Ticket is required");
        for (ITicket existing : tickets) {
            if (existing.getId() == ticket.getId()) throw new IllegalArgumentException("Duplicate ticket ID");
        }
        tickets.add(ticket);
    }

    public void addEmployee(IEmployee employee) {
        if (employee == null) throw new IllegalArgumentException("Employee is required");
        for (IEmployee existing : employees) {
            if (existing.getFullName().equals(employee.getFullName())) {
                throw new IllegalArgumentException("Duplicate employee name");
            }
        }
        employees.add(employee);
    }

    public void completeTicket(String employeeFullName, int ticketId) {
        for (IEmployee employee : employees) {
            if (!employee.getFullName().equals(employeeFullName)) continue;
            for (ITicket ticket : tickets) {
                if (ticket.getId() == ticketId && !ticket.getIsCompleted()
                        && employee.getPointLevel() >= ticket.getPoint()
                        && employee.getAssignedCategories().contains(ticket.getCategory())) {
                    ticket.setAssignedEmployee(employeeFullName);
                    ticket.setIsCompleted(true);
                    return;
                }
            }
            return;
        }
    }

    public int getWaitingTicketCount() {
        int count = 0;
        for (ITicket ticket : tickets) if (!ticket.getIsCompleted()) count++;
        return count;
    }

    public int getCompletedTicketsTotalPoint() {
        int total = 0;
        for (ITicket ticket : tickets) if (ticket.getIsCompleted()) total += ticket.getPoint();
        return total;
    }

    public List<CategoryNode> getTicketsTotalPointByCategory() {
        List<CategoryNode> result = new ArrayList<>();
        for (Category category : Category.values()) {
            int total = 0;
            for (ITicket ticket : tickets) if (ticket.getCategory() == category) total += ticket.getPoint();
            result.add(new CategoryNode(category, total));
        }
        return result;
    }

    public List<EmployeeNode> getTicketsTotalPointByEmployee() {
        List<EmployeeNode> result = new ArrayList<>();
        for (IEmployee employee : employees) {
            int total = 0;
            for (ITicket ticket : tickets) {
                if (ticket.getIsCompleted() && employee.getFullName().equals(ticket.getAssignedEmployee())) {
                    total += ticket.getPoint();
                }
            }
            result.add(new EmployeeNode(employee, total));
        }
        return result;
    }
}
