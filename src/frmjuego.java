import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

public class frmjuego extends JFrame  {

    private JPanel pnlJugador1, pnlJugador2;

    public frmjuego(){

        setSize(600, 300);
        setTitle("juego de cartas");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JButton btnRepartir = new JButton("repartir");
        btnRepartir.setBounds(10, 10, 100, 25);
        getContentPane().add(btnRepartir); 

        JButton btnVerificar = new JButton("verificar");
        btnVerificar.setBounds(120, 10, 100, 25);
        getContentPane().add(btnVerificar); 

        pnlJugador1=new JPanel();
        pnlJugador1.setBackground(new Color(153, 255, 50));

        pnlJugador2=new JPanel();
        pnlJugador2.setBackground(new Color(0, 255, 255));

        JTabbedPane tpJugadores = new JTabbedPane();
        tpJugadores.addTab("martin estrada contreras", pnlJugador1);
        tpJugadores.addTab("raul vidal", pnlJugador2); 

        tpJugadores.setBounds(10, 40, 550, 200);
        getContentPane().add(tpJugadores); 

    }

}
