import java.util.ArrayList;

class Jugador
{
    private String nombre;
    private int puntuacion;
    private ArrayList<ArrayList<Ficha>> paresEncontrados;
    private int intentos;

    public Jugador(String nombre)
    {
        this.nombre = nombre;
        this.puntuacion = 0;
        this.paresEncontrados = new ArrayList<>();
        this.intentos = 0;
    }

    public String getNombre()
    {
        return nombre;
    }

    public int getPuntuacion()
    {
        return puntuacion;
    }

    public void incrementarPuntuacion()
    {
        puntuacion++;
    }

    public void agregarParEncontrado(ArrayList<Ficha> par)
    {
        paresEncontrados.add(par);
    }

    public void registrarIntento(int numeroIntentos)
    {
        this.intentos += numeroIntentos;
    }

    public int getIntentos()
    {
        return intentos;
    }

    public ArrayList<ArrayList<Ficha>> getParesEncontrados()
    {
        return paresEncontrados;
    }
}
