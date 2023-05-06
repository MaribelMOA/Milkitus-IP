import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CPedido {
    public void AgregarPedidoBD(){
        CConexion objetoConexion=new CConexion();
        SimpleDateFormat df = new SimpleDateFormat("yyyy--MM-dd HH:mm:ss");
        String timeStamp = df.format(new Date());
        //si te mandaran la fehca por un JDateChooser fecha,pondrias en el parentesis
        //fecha.getDate() en lugar de new Date()

        String consulta="INSERT pedido(Fecha_Orden,Estado) VALUES(?,?); ";
        try{
            CallableStatement cs=objetoConexion.estableceConexion().prepareCall(consulta);
            System.out.println("Entro");
            cs.setString(1,timeStamp);
            System.out.println("Entro2");
            String estado="EP";
            cs.setString(2,estado);
            System.out.println("Entro3");
            cs.execute();
            System.out.println("Entro4");
            JOptionPane.showMessageDialog(null,"Se agrego el pedido a la base de datos");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"NO se agrego EL PEDIDO a la base de datos");

        }


    }

    public void CambiarEstadoProgreso(){
        CConexion objetoConexion=new CConexion();

        String consulta="INSERT INTO informenotas(Fecha_Orden,Estado)" +
                "values(?,?) ";
        /*CallableStatement cs=objetoConexion.estableceConexion().prepareCall(sql);
            cs.setString(1,user);
            cs.setString(2,password);*/
    }
    public void CambiarEstadoFin(){
        CConexion objetoConexion=new CConexion();

        String consulta="INSERT INTO informenotas(Fecha_Orden,Estado)" +
                "values(?,?) ";
        /*CallableStatement cs=objetoConexion.estableceConexion().prepareCall(sql);
            cs.setString(1,user);
            cs.setString(2,password);*/
    }

    public int buscarUltimoPedido(){
        //Pedido pedidoReciente=null;
        int noPedido=0;
        CConexion objetoConexion=new CConexion();
        try{
            Connection con=objetoConexion.estableceConexion();
            Statement stat=con.createStatement();
            String consulta="select * from pedido order by No_Pedido desc limit 1;";
            PreparedStatement preparedStatement=con.prepareStatement(consulta);

            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                // pedidoReciente=new Pedido();
                noPedido=resultSet.getInt("No_Pedido");
                //pedidoReciente.No_Pedido=resultSet.getInt("No_Pedido");
                //  pedidoReciente.Cambio=resultSet.getInt()


            }
            stat.close();
            con.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return noPedido;
    }

}
