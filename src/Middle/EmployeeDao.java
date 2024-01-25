package Middle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import EmployeeSample.DBManager;

public class EmployeeDao {
    private static final String TABLE_NAME1 = "employees";
    private static final String TABLE_NAME2 = "departments";

    public Employee load(Integer id) {
        Connection con = DBManager.createConnection();

        String sql = """
                select
                e.id,
                e.name,
                e.age,
                e.gender,
                e.department_id,
                d.name as dep_name
                from 
                """
                + TABLE_NAME1 +
                """
                 e
                join 
                """
                + TABLE_NAME2 +
                """
                 d
                on
                e.department_id = d.id
                where e.id = ?;
                """;
        
        // "select id, name, age, gender, department_id from " + TABLE_NAME + " where id = ?";

        try{
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setAge(rs.getInt("age"));
                employee.setGender(rs.getString("gender"));
                employee.setDepartmentId(rs.getInt("department_id"));

                Department department = new Department();
                department.setId(rs.getInt("department_id"));
                department.setName(rs.getString("dep_name"));
                employee.setDepartment(department);

                return employee;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("load処理に失敗しました", e);
        }finally{
            DBManager.closeConnection(con);
        }

    }

    public List<Employee> findAll() {
        Connection con = DBManager.createConnection();

        String sql = """
                select
                e.id,
                e.name,
                e.age,
                e.gender,
                e.department_id,
                d.name as dep_name
                from 
                """
                + TABLE_NAME1 +
                """
                 e
                join
                """
                + TABLE_NAME2 +
                """
                 d
                on
                e.department_id = d.id;
                """;

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            List<Employee> employees = new ArrayList<>();

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setAge(rs.getInt("age"));
                employee.setGender(rs.getString("gender"));
                employee.setDepartmentId(rs.getInt("department_id"));

                Department department = new Department();
                department.setId(rs.getInt("department_id"));
                department.setName(rs.getString("dep_name"));
                employee.setDepartment(department);

                employees.add(employee);
            }

            return employees;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("findAll処理に失敗しました", e);
        } finally {
            DBManager.closeConnection(con);
        }
    }

    public List<Employee> findByDepartmentID(int departmentId) {
        Connection con = DBManager.createConnection();

        String sql = """
            select
            e.id,
            e.name,
            e.age,
            e.gender,
            e.department_id,
            d.name as dep_name
            from 
            """
            + TABLE_NAME1 +
            """
             e
            join
            """
            + TABLE_NAME2 +
            """
             d
            on
            e.department_id = d.id
            where e.department_id = ?;
            """;

    try {
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, departmentId);
        ResultSet rs = pstmt.executeQuery();

        List<Employee> employees = new ArrayList<>();

        while (rs.next()) {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setAge(rs.getInt("age"));
            employee.setGender(rs.getString("gender"));
            employee.setDepartmentId(rs.getInt("department_id"));

            Department department = new Department();
            department.setId(rs.getInt("department_id"));
            department.setName(rs.getString("dep_name"));
            employee.setDepartment(department);

            employees.add(employee);
        }

        return employees;
    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("findByDepartmentID処理に失敗しました", e);
    } finally {
        DBManager.closeConnection(con);
    }
    }
}
