package problem2;

import java.util.List;

public interface ITicket {
    public void setId(int id);
    public int getId();
    public void setName(String name);
    public String getName();
    public void setCategory(Category category);
    public Category getCategory();
    public void setPoint(int point);
    public int getPoint();
    public void setIsCompleted(boolean isCompleted);
    public boolean getIsCompleted();
    public void setAssignedEmployee(String assignedEmployee);
    public String getAssignedEmployee();
}
