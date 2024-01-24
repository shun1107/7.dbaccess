import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateSample {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/student";
        String user = "postgres";
        String password = "postgres";

        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = null;

        try{
            con = DriverManager.getConnection(url, user, password);
            sql = "insert into employees (name, age) values ('テスト太郎', 19)";
            pstmt = con.prepareStatement(sql);
            int numOfUpdated = pstmt.executeUpdate();
            System.out.println(numOfUpdated + "件のレコードを更新しました。");
        }catch(SQLException ex){
            System.err.println("SQL = " + sql);
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