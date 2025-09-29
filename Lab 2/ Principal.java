import java.util.ArrayList;
import java.util.Scanner;

public class Principal
{
    private Scanner scanner;
    private JuegoMemoria juego;

    public Principal()
    {
        scanner = new Scanner(System.in);
    }
    public static void main(String[] args)
    {
        Principal programa = new Principal();
        programa.mostrarMenu();

        do
        {
            programa.configurarJuego();
            programa.ejecutarJuego();
        } while (programa.preguntarNuevaPartida());

        System.out.println("¡Gracias por jugar!");
    }

    public void ejecutarJuego()
    {
        while (!juego.verificarFinJuego())
        {
            System.out.println("\nTurno de: " + juego.getJugadorActual().getNombre());
            System.out.println("Puntuación - " + juego.getJugador1().getNombre() + ": " + juego.getJugador1().getPuntuacion() +
            " | " + juego.getJugador2().getNombre() + ": " + juego.getJugador2().getPuntuacion());

            procesarTurnoJugador();

            if (juego.verificarFinJuego())
            {
                juego.mostrarResultados();
            }
        }
    }

    public void procesarTurnoJugador()
    {
        int[] coordenadas = solicitarJugadaJugador();
        juego.procesarJugada(coordenadas[0], coordenadas[1], coordenadas[2], coordenadas[3]);
        juego.getTablero().mostrarTablero();
    }

    public int[] solicitarJugadaJugador()
    {
        int[] coordenadas = new int[4];

        do 
        {
            try
            {
                System.out.print("Selecciona la primera ficha (fila columna): ");
                coordenadas[0] = scanner.nextInt() - 1;
                coordenadas[1] = scanner.nextInt() - 1;

                juego.getTablero().mostrarTablero();

                if (!juego.esJugadaValida(coordenadas[0], coordenadas[1]))
                {
                    System.out.println("Jugada inválida. Intenta de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Usa números enteros.");
                scanner.nextLine();
                coordenadas[0] = -1;
                coordenadas[1] = -1;
            }
        } while (!juego.esJugadaValida(coordenadas[0], coordenadas[1]));

        
        do
        {
            try
            {
                System.out.print("Selecciona la segunda ficha (fila columna): ");
                coordenadas[2] = scanner.nextInt() - 1;
                coordenadas[3] = scanner.nextInt() - 1;

                juego.getTablero().mostrarTablero();

                if (!juego.esJugadaValida(coordenadas[2], coordenadas[3]) ||
                    (coordenadas[0] == coordenadas[2] && coordenadas[1] == coordenadas[3])) {
                    System.out.println("Jugada inválida. No puedes seleccionar la misma ficha dos veces.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Usa números enteros.");
                scanner.nextLine(); // Limpiar buffer
                coordenadas[2] = -1;
                coordenadas[3] = -1;
            }
        } while (!juego.esJugadaValida(coordenadas[2], coordenadas[3]) ||
                 (coordenadas[0] == coordenadas[2] && coordenadas[1] == coordenadas[3]));

        return coordenadas;
    }

    public void mostrarMenu()
    {
        System.out.println("=== JUEGO DE MEMORIA ===");
        System.out.println("¡Bienvenido al juego de memoria para dos jugadores!");
        mostrarInstrucciones();
    }

    public int[] solicitarTamañoTablero()
    {
        int[] dimensiones = new int[2];

        do
        {
            try
            {
                System.out.print("Ingresa el número de filas (mínimo 1): ");
                dimensiones[0] = scanner.nextInt();
                System.out.print("Ingresa el número de columnas (mínimo 1): ");
                dimensiones[1] = scanner.nextInt();

                if (dimensiones[0] < 1 || dimensiones[1] < 1)
                {
                    System.out.println("Las dimensiones deben ser al menos 1x1.");
                } else if ((dimensiones[0] * dimensiones[1]) % 2 != 0)
                {
                    System.out.println("El total de casillas (" + (dimensiones[0] * dimensiones[1]) +
                                       ") debe ser par para poder formar pares. Intenta de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Usa números enteros.");
                scanner.nextLine(); // Limpiar buffer
                dimensiones[0] = 0;
            }
        } while (dimensiones[0] < 1 || dimensiones[1] < 1 ||
                 (dimensiones[0] * dimensiones[1]) % 2 != 0);

        return dimensiones;
    }

    public void configurarJuego()
    {
        int[] dimensiones = solicitarTamañoTablero();
        ArrayList<Jugador> jugadores = crearJugadores();

        juego = new JuegoMemoria(dimensiones[0], dimensiones[1], jugadores.get(0).getNombre(), jugadores.get(1).getNombre());
    }

    public ArrayList<Jugador> crearJugadores()
    {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        scanner.nextLine();

        System.out.print("Ingresa el nombre del Jugador 1: ");
        String nombre1 = scanner.nextLine();
        System.out.print("Ingresa el nombre del Jugador 2: ");
        String nombre2 = scanner.nextLine();

        jugadores.add(new Jugador(nombre1));
        jugadores.add(new Jugador(nombre2));

        return jugadores;
    }

    public boolean preguntarNuevaPartida()
    {
        System.out.print("\n¿Deseas jugar otra partida? (s/n): ");
        String respuesta = scanner.next().toLowerCase();

        if (respuesta.equals("s") || respuesta.equals("si")) {
            juego.reiniciarJuego();
            return true;
        }
        return false;
    }

    public void mostrarInstrucciones()
    {
        System.out.println("\n=== INSTRUCCIONES ===");
        System.out.println("1. Los jugadores alternan turnos");
        System.out.println("2. En tu turno, selecciona dos fichas ingresando fila y columna");
        System.out.println("3. Si las fichas coinciden, ganas un punto y juegas de nuevo");
        System.out.println("4. Si no coinciden, pierdes el turno");
        System.out.println("5. Gana quien tenga más pares al final");
        System.out.println("6. Las coordenadas van de 1 a N (no desde 0)");
        System.out.println();
    }

    public void mostrarEstadísticas()
    {
        System.out.println("=== ESTADÍSTICAS ===");
        System.out.println("Jugador 1 (" + juego.getJugador1().getNombre() + "):");
        System.out.println("- Pares encontrados: " + juego.getJugador1().getPuntuacion());
        System.out.println("- Intentos realizados: " + juego.getJugador1().getIntentos());
        System.out.println("- Lista de pares: " + juego.getJugador1().getParesEncontrados().size() + " pares guardados");

        System.out.println("\nJugador 2 (" + juego.getJugador2().getNombre() + "):");
        System.out.println("- Pares encontrados: " + juego.getJugador2().getPuntuacion());
        System.out.println("- Intentos realizados: " + juego.getJugador2().getIntentos());
        System.out.println("- Lista de pares: " + juego.getJugador2().getParesEncontrados().size() + " pares guardados");
    }

    public boolean validarEntradaNumerica(String entrada)
    {
        try {
            Integer.parseInt(entrada);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
