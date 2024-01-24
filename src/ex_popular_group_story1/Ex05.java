package ex_popular_group_story1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex05 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/student";
        String user = "postgres";
        String password = "postgres";

        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = null;
        
        try{
            con = DriverManager.getConnection(url, user, password);
            sql = """
                delete from members where name in ('杉浦駿', '櫻井翔');
                """;
            pstmt = con.prepareStatement(sql);
            int numOfUpdated = pstmt.executeUpdate();
            System.out.println(numOfUpdated + "件のレコードを更新しました。");
        }catch(SQLException ex){
            System.out.println("SQL = " + sql);
            ex.printStackTrace();
        }finally{
            try{
                if(con != null){
                    con.close();
                }
                if(pstmt != null){
                    pstmt.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
