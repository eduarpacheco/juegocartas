import java.util.Random;

import javax.swing.JPanel;

public class jugador {

    private final int TOTAL_CARTAS = 10;
    private final int MARGEN = 10;
    private final int SEPARACION = 40;
    private carta[] cartas = new carta[TOTAL_CARTAS];
    private Random r = new Random();

    public void repartir() {
        for (int i = 0; i < TOTAL_CARTAS; i++) {

            cartas[i] = new carta(r);

        }

    }

    public void mostrar(JPanel pnl) {

        pnl.removeAll();

        int posicion = MARGEN + TOTAL_CARTAS * SEPARACION;
        for (carta carta : cartas) {
            carta.mostrar(pnl, posicion, MARGEN);
            posicion -= SEPARACION;

        }
        pnl.repaint();

    }

    public String getGrupos() {

        String resultado = "no se encontraron grupos";

        // calcular contadores
        int[] contadores = new int[nombrecarta.values().length];
        for (carta carta : cartas) {
            contadores[carta.getnombre().ordinal()]++;

        }

        // validar si hubo grupos

        boolean hayGrupos = false;
        for (int contador : contadores) {
            if (contador >= 2) {
                hayGrupos = true;
                break;
            }
        }

        // obtener los grupos
        if (hayGrupos) {

            resultado = "se hallaron los siguientes grupos: \n";

            for (int i = 0; i < contadores.length; i++) {
                int contador = contadores[i];
                if (contador >= 2) {

                    resultado += Grupo.values()[contador] + "de" + nombrecarta.values()[i] + "\n";
                }

            }
        }

        return resultado;
    }
    // NUEVOS METODOS


   

}
