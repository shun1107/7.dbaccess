package ex_popular_group_story2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex04 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/student";
        String user = "postgres";
        String password = "postgres";

        String sql = """
                delete from members where name = '大野智';
                insert into members
                (name, birth_day, gender, color_id)
                values
                ('杉浦駿', '1996-11-07', '男', 6);
                """;

        try(Connection con = DriverManager.getConnection(url, user, password)){
            con.setAutoCommit(false);
            try(PreparedStatement pstmt = con.prepareStatement(sql)){
                int numOfUpdated = pstmt.executeUpdate();
                System.out.println(numOfUpdated + "件のレコードを更新しました。");
                con.commit();
            }catch(SQLException ex){
            con.rollback();
            System.out.println("SQL = " + sql);
            ex.printStackTrace();
            }
        }catch(SQLException ex){
        ex.printStackTrace();
        }
    }
}