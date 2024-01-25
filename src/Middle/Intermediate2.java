package Middle;

import java.util.List;

public class Intermediate2 {
    public static void main(String[] args) {
        EmployeeDao dao = new EmployeeDao();
        List<Employee> employees = dao.findAll();
        
        for (Employee employee : employees) {
            System.out.print("id=" + employee.getId());
            System.out.print(", name=" + employee.getName());
            System.out.print(", age=" + employee.getAge());
            System.out.print(", gender=" + employee.getGender());
            System.out.print(", departmentId=" + employee.getDepartmentId());
            System.out.println(", department=Department[id=" + employee.getDepartment().getId() + ", name=" + employee.getDepartment().getName() + "]");
        }
    }
}
