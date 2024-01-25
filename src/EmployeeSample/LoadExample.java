package EmployeeSample;

public class LoadExample {
    public static void main(String[] args) {
        EmployeeDao dao = new EmployeeDao();
        Employee employee = dao.load(1);
        
        System.out.println("ID:" + employee.getId());
        System.out.println("名前:" + employee.getName());
        System.out.println("年齢:" + employee.getAge());
        System.out.println("性別:" + employee.getGender());
        System.out.println("部署ID:" + employee.getDepartmentId());

    }
}
