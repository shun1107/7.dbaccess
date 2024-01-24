package ex_popular_group_story2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ex03 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/student";
        String user = "postgres";
        String password = "postgres";

        String sql = """
                select
                m.name,
                m.birth_day,
                m.gender,
                c.name as color_name
                from members m
                join colors c
                on m.color_id = c.id;
                """;

        try(Connection con = DriverManager.getConnection(url, user, password)){
            con.setAutoCommit(false);
            try(PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                            String name = rs.getString("name");
                            String birthDay = rs.getString("birth_day");
                            String gender = rs.getString("gender");
                            String colorName = rs.getString("color_name");
            
                            System.out.println("Name: " + name);
                            System.out.println("Birth Day: " + birthDay);
                            System.out.println("Gender: " + gender);
                            System.out.println("Color Name: " + colorName);
                            System.out.println();
                }
                con.commit();
            }catch(SQLException ex){
            con.rollback();
            System.err.println("SQL関連の例外が発生しました。");
            System.err.println("発行したSQLは「" + sql + "」");
            ex.printStackTrace();
            }
        }catch(SQLException ex){
        ex.printStackTrace();
        }
    }
}