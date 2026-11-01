package problem2;

import java.util.List;

public interface IEmployee {
    public void setFullName(String fullName);
    public String getFullName();
    public void setPointLevel(int pointLevel);
    public int getPointLevel();
    public void setAssignedCategories(List <Category> assignedCategories);
    public List <Category> getAssignedCategories();
}
