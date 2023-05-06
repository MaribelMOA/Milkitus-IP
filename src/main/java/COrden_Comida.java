import javax.swing.*;
import java.sql.CallableStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class COrden_Comida {
    public void AgregarOrdenBD(int No_Pedido, String ID_Comida, int Cant,double Subtotal){
        CConexion objetoConexion=new CConexion();

        String consulta="INSERT orden_comida(No_Pedido,ID_Comida,Cantidad,subtotal) VALUES(?,?,?,?); ";
        try{
            CallableStatement cs=objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setInt(1,No_Pedido);
            cs.setString(2,ID_Comida);
            cs.setInt(3,Cant);
            cs.setDouble(4,Subtotal);

            cs.execute();
            //JOptionPane.showMessageDialog(null,"Se agrego el pedido a la base de datos");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"NO se agrego la orden a la base de datos");
        }
    }
}
