package problem2;

import java.util.List;

public interface IHelpDesk {
    public void addTicket(ITicket ticket);
    public void addEmployee(IEmployee employee);
    public void completeTicket(String employeeFullName, int ticketId);
    public int getWaitingTicketCount();
    public int getCompletedTicketsTotalPoint();
    public List <CategoryNode> getTicketsTotalPointByCategory();
    public List <EmployeeNode> getTicketsTotalPointByEmployee();
}
