import javax.swing.*;
import java.sql.CallableStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class COrden_Bebida {
    public void AgregarOrdenBD(int No_Pedido, String ID_Bebida, int Cant,double Subtotal){
        CConexion objetoConexion=new CConexion();

        String consulta="INSERT orden_bebida(No_Pedido,ID_Bebida,Cantidad,subtotal) VALUES(?,?,?,?); ";
        try{
            CallableStatement cs=objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setInt(1,No_Pedido);
            cs.setString(2,ID_Bebida);
            cs.setInt(3,Cant);
            cs.setDouble(4,Subtotal);

            cs.execute();
            //JOptionPane.showMessageDialog(null,"Se agrego el pedido a la base de datos");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"NO se agrego la orden a la base de datos");
        }
    }
}