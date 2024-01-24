package ex_popular_group_story1;

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

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;

        try{
            con = DriverManager.getConnection(url, user, password);
            sql = """
                select *
                from members
                order by id;
                """;
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String birthDay = rs.getString("birth_day");
                String gender = rs.getString("gender");
                int colorId = rs.getInt("color_id");

                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Birth Day: " + birthDay);
                System.out.println("Gender: " + gender);
                System.out.println("Color ID: " + colorId);
                System.out.println();
            }
        }catch(SQLException ex){
            System.err.println("SQL関連の例外が発生しました。");
            System.err.println("発行したSQLは「" + sql + "」");
            ex.printStackTrace();
        }finally{
            try{
                if(con != null){
                    con.close();
                }
                if(pstmt != null){
                    pstmt.close();
                }
                if(rs != null){
                    rs.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
