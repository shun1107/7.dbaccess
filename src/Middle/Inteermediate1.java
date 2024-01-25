package Middle;

public class Inteermediate1 {
    public static void main(String[] args) {
        EmployeeDao dao = new EmployeeDao();
        Employee employee = dao.load(1);
        
        System.out.print("id=" + employee.getId());
        System.out.print(", name=" + employee.getName());
        System.out.print(", age=" + employee.getAge());
        System.out.print(", gender=" + employee.getGender());
        System.out.print(", departmentId=" + employee.getDepartmentId());
        System.out.println(", department=Department[id=" + employee.getDepartment().getId() + ", name=" + employee.getDepartment().getName() + "]");
    }
}