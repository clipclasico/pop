/// Clase que representa un contacto con nombre y número telefónico.
/// Incluye métodos para obtener el nombre, el número y una representación en texto.

public class Contacto
{
    private String nombre;
    private String numero;


    public Contacto(String nombre, String numero)
    {
        this.nombre = nombre;
        this.numero = numero;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getNumero()
    {
        return numero;
    }

    @Override
    public String toString()
    {
        return String.format("%s: %s", nombre, numero);
    }
}