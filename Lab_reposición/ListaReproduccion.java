/// Clase que representa una lista de reproducción de canciones.
/// Permite agregar canciones y obtener información sobre la lista.
/// Incluye métodos para obtener el nombre, las canciones y el tamaño de la lista.

import java.util.ArrayList;


public class ListaReproduccion
{
    private String nombre;
    private ArrayList<Cancion> canciones;


    public ListaReproduccion(String nombre)
    {
        this.nombre = nombre;
        this.canciones = new ArrayList<>();
    }

    public void agregarCancion(Cancion cancion)
    {
        canciones.add(cancion);
    }

    public String getNombre()
    {
        return nombre;
    }

    public ArrayList<Cancion> getCanciones()
    {
        return canciones;
    }


    public Cancion getCancion(int indice)
    {
        if (indice >= 0 && indice < canciones.size())
            {
            return canciones.get(indice);
        }
        return null;
    }


    public int getTamanio()
    {
        return canciones.size();
    }

    @Override
    public String toString()
    {
        return String.format("%s (%d canciones)", nombre, canciones.size());
    }
}