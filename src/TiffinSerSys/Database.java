package TiffinSerSys;


import java.sql.*;

public class Database {

    Connection c;
    Statement s;
    Database(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
             c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tss","root","");
             s = c.createStatement();
            System.out.println("Connected Successfully");
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Database();
    }
}
