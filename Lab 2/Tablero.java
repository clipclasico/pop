import java.util.ArrayList;
import java.util.Collections;

class Tablero
{
    private Ficha[][] fichas;
    private int filas;
    private int columnas;
    private char[] simbolosDisponibles = {'☺','☹','♥','♦','♣','♠','♪','♫','☀','☁','☂','★','☆','✓','✗','☮','☯','⚑','☕','⚡'};
    
    public Tablero(int filas, int columnas)
    {
        this.filas = filas;
        this.columnas = columnas;
        this.fichas = new Ficha[filas][columnas];
        inicializarFichas();
    }
    
    public void inicializarFichas()
    {
        ArrayList<String> simbolos = new ArrayList<>();
        int totalFichas = filas * columnas;
        
        for (int i = 0; i < totalFichas / 2; i++)
        {
            String simbolo = String.valueOf(simbolosDisponibles[i % simbolosDisponibles.length]);
            simbolos.add(simbolo);
            simbolos.add(simbolo);
        }
        
        Collections.shuffle(simbolos);
        
        int index = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                fichas[i][j] = new Ficha(simbolos.get(index++));
            }
        }
    }
    
    public void mostrarTablero()
    {
    System.out.print("   ");
    for (int j = 0; j < columnas; j++)
    {
        System.out.print("  " + (j + 1));
    }
    System.out.println();

    for (int i = 0; i < filas; i++)
    {
        System.out.print((i + 1) + "  ");
        for (int j = 0; j < columnas; j++)
        {
            if (fichas[i][j].esRevelada() || fichas[i][j].esEmparejada())
            {
                System.out.print(fichas[i][j].getSimbolo() + "  ");
            } else {
                System.out.print("X  ");
            }
        }
        System.out.println();
    }
}

    public boolean jugadaValida(int fila, int columna)
    {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas)
        {
            return false;
        }
        return !fichas[fila][columna].esEmparejada() && !fichas[fila][columna].esRevelada();
    }
    public void revelarFicha(int fila, int columna)
    {
        fichas[fila][columna].setRevelada(true);
    }
    
    public void ocultarFicha(int fila, int columna)
    {
        fichas[fila][columna].setRevelada(false);
    }
    
    public void marcarEmparejadas(int fila1, int columna1, int fila2, int columna2)
    {
        fichas[fila1][columna1].setEmparejada(true);
        fichas[fila2][columna2].setEmparejada(true);
    }

    public Ficha getFicha(int fila, int columna)
    {
        return fichas[fila][columna];
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
}
