import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Home extends JDialog{
    private JButton btnImage;
    private JButton btnAgregar;
    private JButton btnConsultar;
    private JButton cerrarSesionButton;
    private JButton btnCalendario;
    private JPanel pnlHome;
    public AgregarPedido pnlAgregar;
    public BufferedImage image;
    public ImageIcon imgHome;
    public Icon icono;
    public Home() {

        setContentPane(pnlHome);
        pnlHome.setBorder(BorderFactory.createTitledBorder("Milkitus El Salado-HOME"));
        pnlHome.setSize(900, 700);
        setModal(true);

        try {
            image = ImageIO.read(new File("images/home.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgHome=new ImageIcon(image);
        //icono=new ImageIcon(imgHome.getImage().getScaledInstance(btnImage.getWidth(),btnImage.getHeight(), Image.SCALE_DEFAULT));


        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CPedido objetoPedido=new CPedido();
                objetoPedido.AgregarPedidoBD();
                AgregarPedido pnlAgregar=new AgregarPedido();
                pnlAgregar.pack();
                pnlAgregar.setVisible(true);

                dispose();


            }
        });
    }


}
