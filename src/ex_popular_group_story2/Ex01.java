package ex_popular_group_story2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex01 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/student";
        String user = "postgres";
        String password = "postgres";

        String sql = """
                drop table if exists members;
                drop table if exists colors;
                create table colors(
                    id serial primary key,
                    name text
                );
                create table members(
                    id serial primary key,
                    name text not null,
                    birth_day date,
                    gender varchar(1),
                    color_id integer references colors(id)
                );
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