import java.util.ArrayList;
import java.util.Scanner;

class JuegoMemoria {
    private Tablero tablero;
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;
    private boolean juegoTerminado;
    private int paresRestantes;

    public JuegoMemoria(int filas, int columnas, String nombreJugador1, String nombreJugador2) {
        this.tablero = new Tablero(filas, columnas);
        this.jugador1 = new Jugador(nombreJugador1);
        this.jugador2 = new Jugador(nombreJugador2);
        this.jugadorActual = jugador1;
        this.juegoTerminado = false;
        this.paresRestantes = (filas * columnas) / 2;
    }
    
    public void procesarJugada(int fila1, int columna1, int fila2, int columna2)
    {
    jugadorActual.registrarIntento(1);

    tablero.revelarFicha(fila1, columna1);
    tablero.revelarFicha(fila2, columna2);

    System.out.println("\n--- Fichas reveladas ---");
    tablero.mostrarTablero();

    System.out.println("Presiona ENTER para continuar...");
    new Scanner(System.in).nextLine();

    if (evaluarJugada(fila1, columna1, fila2, columna2))
    {
        System.out.println("¡Correcto! " + jugadorActual.getNombre() + " encontró un par.");
        jugadorActual.incrementarPuntuacion();
        tablero.marcarEmparejadas(fila1, columna1, fila2, columna2);

        ArrayList<Ficha> par = new ArrayList<>();
        par.add(tablero.getFicha(fila1, columna1));
        par.add(tablero.getFicha(fila2, columna2));
        jugadorActual.agregarParEncontrado(par);

        paresRestantes--;
    } else {
        System.out.println("No coinciden. Turno perdido.");
        // Ocultar si no es par
        tablero.ocultarFicha(fila1, columna1);
        tablero.ocultarFicha(fila2, columna2);
        cambiarTurno();
    }
}

    public int[] solicitarJugada()
    {
        return new int[4];
    }

    public boolean evaluarJugada(int fila1, int columna1, int fila2, int columna2)
    {
        return tablero.getFicha(fila1, columna1).getSimbolo().equals(
               tablero.getFicha(fila2, columna2).getSimbolo());
    }
    
    public void cambiarTurno()
    {
        jugadorActual = (jugadorActual == jugador1) ? jugador2 : jugador1;
    }
    
    public boolean verificarFinJuego()
    {
        return paresRestantes == 0;
    }

    public void mostrarResultados()
    {
        System.out.println("\n=== JUEGO TERMINADO ===");
        System.out.println("Resultados finales:");
        System.out.println(jugador1.getNombre() + ": " + jugador1.getIntentos() + " pares encontrados, " + jugador1.getIntentos() + " intentos");
        System.out.println(jugador2.getNombre() + ": " + jugador2.getIntentos() + " pares encontrados, " + jugador2.getIntentos() + " intentos");
        
        if (jugador1.getIntentos() > jugador2.getIntentos())
        {
            System.out.println("¡Ganador: " + jugador1.getNombre() + "!");
        } else if (jugador2.getIntentos() > jugador1.getIntentos()) {
            System.out.println("¡Ganador: " + jugador2.getNombre() + "!");
        } else {
            System.out.println("¡Empate!");
        }
    }
    
    public void reiniciarJuego()
    {
        jugador1 = new Jugador(jugador1.getNombre());
        jugador2 = new Jugador(jugador2.getNombre());
        jugadorActual = jugador1;
        juegoTerminado = false;
        paresRestantes = (tablero.getFilas() * tablero.getColumnas()) / 2;
        tablero.inicializarFichas();
    }
    
    public Jugador getJugadorActual()
    {
        return jugadorActual;
    }
    
    public Jugador getJugador1()
    {
        return jugador1;
    }
    
    public Jugador getJugador2()
    {
        return jugador2;
    }

    public Tablero getTablero()
    {
        return tablero;
    }

    public boolean esJugadaValida(int fila, int columna)
    {
        return tablero.jugadaValida(fila, columna);
    }
}