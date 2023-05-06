import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class AgregarPedido extends JDialog{
    private JButton btnTacosPescado;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button14;
    private JButton button15;
    private JButton button16;
    private JButton button17;
    private JButton button18;
    private JButton button19;
    private JSpinner spCantidad;
    private JSpinner spExtra;
    private JButton agregarExtraButton;
    private JButton btnGuardar;
    private JButton btnBorrar;
    private JButton btnPagar;
    private JPanel pnlAgregar;
    private JTable tbOrdenes;
    private JLabel lblNoPedido;
    public BufferedImage image;
    public ImageIcon imgTacosPescado;
    public Icon icono;
    DefaultTableModel tbModel;

    public AgregarPedido(){
        setContentPane(pnlAgregar);
        pnlAgregar.setBorder(BorderFactory.createTitledBorder("Milkitus El Salado-HOME"));
        pnlAgregar.setSize(1000, 700);
        setModal(true);

        spCantidad.setValue(1);
        spExtra.setValue(1);

        tbModel=(DefaultTableModel)tbOrdenes.getModel();
        CPedido thisPedido=new CPedido();
        int noPedido=thisPedido.buscarUltimoPedido();
        lblNoPedido.setText("No. Pedido: #"+String.valueOf(noPedido));


        //  tbOrdenes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //JScrollPane pane=new JScrollPane(tbOrdenes);
        //add(pane);

        try {
            image = ImageIO.read(new File("images/tacosPescado.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgTacosPescado=new ImageIcon(image);
      //  icono=new ImageIcon(imgTacosPescado.getImage().getScaledInstance(btnTacosPescado.getWidth(),btnTacosPescado.getHeight(),Image.SCALE_DEFAULT));

        btnTacosPescado.setIcon(icono);
        tbOrdenes.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                DefaultTableModel modelo = new DefaultTableModel();
                modelo.addColumn("Numero");
                modelo.addColumn("Nombre");
                modelo.addColumn("Cantidad");
                modelo.addColumn("Subtotal");
                tbOrdenes.setModel(modelo);

            }
        });

        //regresarButton.addActionListener(e -> dispose());

        btnTacosPescado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id_comida="T1";
                int valorSpinner = Integer.parseInt(spCantidad.getValue().toString());
                agregarOrdenComida(id_comida,valorSpinner);
            }
        });
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //this.dispose();
            }
        });
    }
    public void agregarOrdenComida(String id, int cantidad){

        if(cantidad<0){
            JOptionPane.showMessageDialog(this,"Porfavor ingrese una cantida diferente a 0");
        }else{
            Menu comida=buscarComida(id);
            double subtotal=cantidad*comida.Precio;
            int noOrden=tbOrdenes.getRowCount()+1;

            //String data[]={String.valueOf(noOrden),comida.Nombre,String.valueOf(subtotal)};
            Vector v=new Vector();
            v.add(String.valueOf(noOrden));
            v.add(comida.getNombre());
            v.add(String.valueOf(cantidad));
            v.add(String.valueOf(subtotal));
            tbModel.addRow(v);
            spCantidad.setValue(1);

            CPedido thisPedido=new CPedido();
            int noPedido=thisPedido.buscarUltimoPedido();
            COrden_Comida objetoComida=new COrden_Comida();
            objetoComida.AgregarOrdenBD(noPedido,id, cantidad,subtotal);
            JOptionPane.showMessageDialog(this,"Se agrego la orden");
        }
    }
    private Menu buscarComida(String ID_Comida){
        Menu comida=null;
        CConexion objetoConexion=new CConexion();
        try{

            Connection con=objetoConexion.estableceConexion();
            Statement stat=con.createStatement();
            String consulta="SELECT *FROM menu_comida WHERE ID_Comida=?";
            PreparedStatement preparedStatement=con.prepareStatement(consulta);
            preparedStatement.setString(1,ID_Comida);

            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                comida=new Menu();
                comida.ID_Producto=resultSet.getString("ID_Comida");
                comida.Nombre=resultSet.getString("Nombre");
                comida.Precio=resultSet.getDouble("Precio");
            }
            stat.close();
            con.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return comida;
    }

    private Menu_Bebida buscarBebida(String ID_Bebida){
        Menu_Bebida bebida=null;
        CConexion objetoConexion=new CConexion();
        try{

            Connection con=objetoConexion.estableceConexion();
            Statement stat=con.createStatement();
            String consulta="SELECT *FROM menu_bebida WHERE ID_Bebida=?";
            PreparedStatement preparedStatement=con.prepareStatement(consulta);
            preparedStatement.setString(1,ID_Bebida);

            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                bebida=new Menu_Bebida();
                bebida.ID_Producto=resultSet.getString("ID_Bebida");
                bebida.Nombre=resultSet.getString("Nombre");
                bebida.Precio=resultSet.getDouble("Precio");
                bebida.Tipo=resultSet.getString("Tipo");
            }
            stat.close();
            con.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return bebida;
    }
}
