package problem2;

public class Ticket implements ITicket {
    private int id;
    private String name;
    private Category category;
    private int point;
    private String assignedEmployee;
    private boolean isCompleted;

    public Ticket(int id, String name, Category category, int point) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.point = point;
    }

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public void setCategory(Category category) { this.category = category; }
    public Category getCategory() { return category; }
    public void setPoint(int point) { this.point = point; }
    public int getPoint() { return point; }
    public void setIsCompleted(boolean isCompleted) { this.isCompleted = isCompleted; }
    public boolean getIsCompleted() { return isCompleted; }
    public void setAssignedEmployee(String assignedEmployee) { this.assignedEmployee = assignedEmployee; }
    public String getAssignedEmployee() { return assignedEmployee; }
}
