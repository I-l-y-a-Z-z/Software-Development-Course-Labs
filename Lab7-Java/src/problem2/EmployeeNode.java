package problem2;

import java.util.List;

public class EmployeeNode {
    IEmployee employee;
    int totalPoint;
    EmployeeNode(IEmployee employee, int totalPoint){
        this.employee = employee;
        this.totalPoint = totalPoint;
    }
}
