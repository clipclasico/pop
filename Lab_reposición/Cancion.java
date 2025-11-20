/// Clase que representa una canción con sus metadatos.
/// Incluye nombre, duración, autor y género musical.


public class Cancion
{
    private String nombre;
    private String duracion;
    private String autor;
    private String genero;

    public Cancion(String nombre, String duracion, String autor, String genero)
    {
        this.nombre = nombre;
        this.duracion = duracion;
        this.autor = autor;
        this.genero = genero;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getDuracion()
    {
        return duracion;
    }

    public String getAutor()
    {
        return autor;
    }

    public String getGenero()
    {
        return genero;
    }


    @Override
    public String toString()
    {
        return String.format("%s - %s | %s | %s", nombre, autor, duracion, genero);
    }
}