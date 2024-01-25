package EmployeeSample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeDao {
    private static final String TABLE_NAME = "employees";

    public Employee load(int id){
        Connection con = DBManager.createConnection();

        String sql = """
                select
                id,
                name,
                age,
                gender,
                department_id
                from 
                """
                + TABLE_NAME +
                """
                 where id = ?;
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
}
