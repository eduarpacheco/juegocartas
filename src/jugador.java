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

    public String getEscaleras() {
        StringBuilder resultado = new StringBuilder("No se encontraron escaleras");

        // Paso 1: Agrupar las cartas por pinta
        carta[][] cartasPorPinta = new carta[4][TOTAL_CARTAS]; // 4 pintas, máximo 10 cartas por jugador
        int[] contadoresPinta = new int[4]; // Contador de cartas por pinta

        for (carta c : cartas) {
            int pintaIndex = c.getPinta().ordinal(); // Índice de la pinta (0: Trebol, 1: Pica, etc.)
            cartasPorPinta[pintaIndex][contadoresPinta[pintaIndex]++] = c;
        }

        // Paso 2: Para cada pinta, buscar escaleras
        boolean hayEscaleras = false;

        for (int pintaIndex = 0; pintaIndex < 4; pintaIndex++) {
            if (contadoresPinta[pintaIndex] >= 2) { // Solo si hay al menos 2 cartas de esa pinta
                // Obtener las cartas de esta pinta
                carta[] cartasPinta = new carta[contadoresPinta[pintaIndex]];
                for (int i = 0; i < contadoresPinta[pintaIndex]; i++) {
                    cartasPinta[i] = cartasPorPinta[pintaIndex][i];
                }

                // Paso 3: Ordenar las cartas por valor (usando ordinal del nombre)
                java.util.Arrays.sort(cartasPinta,
                        (a, b) -> Integer.compare(a.getnombre().ordinal(), b.getnombre().ordinal()));

                // Paso 4: Buscar secuencias consecutivas
                int longitudSecuencia = 1;

                for (int i = 1; i < cartasPinta.length; i++) {
                    if (cartasPinta[i].getnombre().ordinal() == cartasPinta[i - 1].getnombre().ordinal() + 1) {
                        longitudSecuencia++;
                    } else {
                        if (longitudSecuencia >= 2) {
                            // Si encontramos una escalera válida, guardarla
                            hayEscaleras = true;
                            String nombrePinta = "";
                            switch (pintaIndex) {
                                case 0:
                                    nombrePinta = "Tréboles";
                                    break;
                                case 1:
                                    nombrePinta = "Picas";
                                    break;
                                case 2:
                                    nombrePinta = "Corazones";
                                    break;
                                case 3:
                                    nombrePinta = "Diamantes";
                                    break;
                            }
                            Grupo grupo = Grupo.values()[longitudSecuencia]; // Obtener el grupo correspondiente
                            resultado.append("\n").append(grupo).append(" en ").append(nombrePinta).append(": ")
                                    .append(cartasPinta[i - longitudSecuencia].getnombre())
                                    .append(" a ").append(cartasPinta[i - 1].getnombre());
                        }
                        longitudSecuencia = 1; // Reiniciar la longitud
                    }
                }

                // Verificar si la última secuencia es una escalera
                if (longitudSecuencia >= 2) {
                    hayEscaleras = true;
                    String nombrePinta = "";
                    switch (pintaIndex) {
                        case 0:
                            nombrePinta = "Tréboles";
                            break;
                        case 1:
                            nombrePinta = "Picas";
                            break;
                        case 2:
                            nombrePinta = "Corazones";
                            break;
                        case 3:
                            nombrePinta = "Diamantes";
                            break;
                    }
                    Grupo grupo = Grupo.values()[longitudSecuencia]; // Obtener el grupo correspondiente
                    resultado.append("\n").append(grupo).append(" en ").append(nombrePinta).append(": ")
                            .append(cartasPinta[cartasPinta.length - longitudSecuencia].getnombre())
                            .append(" a ").append(cartasPinta[cartasPinta.length - 1].getnombre());
                }
            }
        }

        return hayEscaleras ? resultado.toString() : "No se encontraron escaleras";

    }

    // nuevos metodo para puntaje
    public int getPuntajeCartasSueltas() {
        int puntajeTotal = 0;

        // Recorrer todas las cartas
        for (carta c : cartas) {
            nombrecarta nombre = c.getnombre();

            // Asignar valor según el nombre
            if (nombre == nombrecarta.AS || nombre == nombrecarta.JACK || nombre == nombrecarta.QUEEN
                    || nombre == nombrecarta.KING) {
                puntajeTotal += 10;
            } else {
                puntajeTotal += nombre.ordinal() + 1; // Ajuste para que 2 sea 2, 3 sea 3, etc.
            }
        }

        return puntajeTotal;
    }

}
