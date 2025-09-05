import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

public class frmjuego extends JFrame {

    private JPanel pnlJugador1, pnlJugador2;
    private jugador jugador1, jugador2;
    private JTabbedPane tpJugadores;

    public frmjuego() {

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

        // (NUEVO) Agregamos botones para escalaras y puntaje
        JButton btnEscaleras = new JButton("Buscar Escaleras");
        btnEscaleras.setBounds(230, 10, 150, 25);
        getContentPane().add(btnEscaleras);

        JButton btnPuntaje = new JButton("Calcular Puntaje");
        btnPuntaje.setBounds(390, 10, 150, 25);
        getContentPane().add(btnPuntaje);

        pnlJugador1 = new JPanel();
        pnlJugador1.setLayout(null);
        pnlJugador1.setBackground(new Color(153, 255, 50));

        pnlJugador2 = new JPanel();
        pnlJugador2.setLayout(null);
        pnlJugador2.setBackground(new Color(0, 255, 255));

        tpJugadores = new JTabbedPane();
        tpJugadores.addTab("martin estrada contreras", pnlJugador1);
        tpJugadores.addTab("raul vidal", pnlJugador2);

        tpJugadores.setBounds(10, 40, 550, 200);
        getContentPane().add(tpJugadores);

        // agregar los eventos

        btnRepartir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                repartir();
            }

        });

        btnVerificar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                verificar();
            }

        });
        jugador1 = new jugador();
        jugador2 = new jugador();

        // Eventos nuevos
        btnEscaleras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (tpJugadores.getSelectedIndex()) {
                    case 0:
                        JOptionPane.showMessageDialog(null, jugador1.getEscaleras());
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(null, jugador2.getEscaleras());
                        break;
                }
            }
        });

        btnPuntaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (tpJugadores.getSelectedIndex()) {
                    case 0:
                        JOptionPane.showMessageDialog(null,
                                "Puntaje de cartas sueltas: " + jugador1.getPuntajeCartasSueltas());
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(null,
                                "Puntaje de cartas sueltas: " + jugador2.getPuntajeCartasSueltas());
                        break;
                }
            }
        });

    }

    private void repartir() {
        // repartir cartas
        jugador1.repartir();
        jugador2.repartir();

        // mostrar cartas de cada jugador
        jugador1.mostrar(pnlJugador1);
        jugador2.mostrar(pnlJugador2);

    }

    private void verificar() {
        switch (tpJugadores.getSelectedIndex()) {
            case 0:
                JOptionPane.showMessageDialog(null, jugador1.getGrupos());

                break;

            case 1:
                JOptionPane.showMessageDialog(null, jugador2.getGrupos());
                break;
        }

    }

}
