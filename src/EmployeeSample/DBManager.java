package EmployeeSample;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
    private final static String URL = "jdbc:postgresql://localhost:5432/student";
    private final static String USER_NAME = "postgres";
    private final static String PASSWORD = "postgres";

    public static Connection createConnection(){
        try{
            Connection con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            return con;
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("DBの接続に失敗しました", e);
        }
    }

    public static void closeConnection(Connection con){
        try{
            if(con != null){
                con.close();
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("DBの切断に失敗しました", e);
        }
    }
}
