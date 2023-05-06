import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class CConexion {
    Connection conectar=null;
    String databaseName="milkituselsalado";
    String DB_URL="jdbc:mysql://localhost:3306/"+databaseName;
    String USERNAME="root";
    String PASSWORD="Wish!WasHome2";

    public Connection estableceConexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            //JOptionPane.showMessageDialog(null,"Se conecto a la base de datos");
        }catch(Exception e){

            JOptionPane.showMessageDialog(null,"No se conecto a la base de datos");

        }
        return conectar;
    }
}
