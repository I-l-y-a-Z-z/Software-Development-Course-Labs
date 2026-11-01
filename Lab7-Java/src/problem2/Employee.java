package problem2;

import java.util.ArrayList;
import java.util.List;

public class Employee implements IEmployee {
    private String fullName;
    private int pointLevel;
    private List<Category> assignedCategories;

    public Employee(String fullName, int pointLevel, List<Category> assignedCategories) {
        this.fullName = fullName;
        this.pointLevel = pointLevel;
        setAssignedCategories(assignedCategories);
    }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getFullName() { return fullName; }
    public void setPointLevel(int pointLevel) { this.pointLevel = pointLevel; }
    public int getPointLevel() { return pointLevel; }
    public void setAssignedCategories(List<Category> assignedCategories) {
        this.assignedCategories = new ArrayList<>(assignedCategories);
    }
    public List<Category> getAssignedCategories() { return new ArrayList<>(assignedCategories); }
}
