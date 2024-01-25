package EmployeeSample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DepartmentDao {
    private static final String TABLE_NAME = "departments";

    public Department load(int id){
        Connection con = DBManager.createConnection();

        String sql = """
                select
                id,
                name
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
                Department department = new Department();
                department.setId(rs.getInt("id"));
                department.setName(rs.getString("name"));
                return department;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("load処理に失敗しました", e);
        }finally{
            DBManager.closeConnection(con);
        }
    }

    public int insert(Department department){
        Connection con = DBManager.createConnection();

        String sql = """
                insert into 
                """
                + TABLE_NAME +
                """
                (id,name)
                values
                (?,?);
                """;

        try{
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, department.getId());
            pstmt.setString(2, department.getName());
            int result = pstmt.executeUpdate();
            return result;
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("insert処理に失敗しました", e);
        }finally{
            DBManager.closeConnection(con);
        }
    }

    public int update(Department department){
        Connection con = DBManager.createConnection();

        String sql = """
                update 
                """
                + TABLE_NAME +
                """
                 set
                id = ?,
                name = ?
                where id = ?;
                """;

        try{
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, department.getId());
            pstmt.setString(2, department.getName());
            pstmt.setInt(3, department.getId());

            int result = pstmt.executeUpdate();
            return result;

        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("update処理に失敗しました", e);
        }finally{
            DBManager.closeConnection(con);
        }
    }

    public int deleteById(int id){
        Connection con = DBManager.createConnection();

        String sql = """
                delete from 
                """
                + TABLE_NAME +
                """
                 where id = ?;
                """;

        try{
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            int result = pstmt.executeUpdate();
            return result;
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("delete処理に失敗しました", e);
        }finally{
            DBManager.closeConnection(con);
        }
    }
}
