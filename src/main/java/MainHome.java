import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainHome extends JDialog{
    private JButton btnSalir;
    private JButton btnImage;
    private JTabbedPane tbOpciones;

    public BufferedImage image;
    public ImageIcon imgHome;
    public Icon icono;

    public MainHome(){
        this.setTitle("Milkitus- El Salado");
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        tbOpciones.addTab("Calendario",new JPanel());
        tbOpciones.addTab("Agregar Pedido", new AgregarPedido());
        this.getContentPane().add(tbOpciones);
        this.setVisible(true);
        this.setBounds(0,0,400,400);

        try {
            image = ImageIO.read(new File("images/home.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgHome=new ImageIcon(image);
        icono=new ImageIcon(imgHome.getImage().getScaledInstance(btnImage.getWidth(),btnImage.getHeight(), Image.SCALE_DEFAULT));

        btnImage.setIcon(icono);
        btnImage .setHorizontalTextPosition(SwingConstants.CENTER);
        btnImage.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnImage .setBackground(Color.cyan);
        btnImage .setBorderPainted(false);
        btnImage .setFocusPainted(false);
        btnImage .setContentAreaFilled(false);
        btnImage.setHorizontalAlignment(SwingConstants.CENTER);

    }
}
