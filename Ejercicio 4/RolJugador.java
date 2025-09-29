import java.util.List;

// Clase abstracta para los roles de jugador

public abstract class RolJugador
{
    protected double multiplicadorVida;
    protected double multiplicadorAtaque;
    protected int capacidadItems;
    protected String nombreRol;

    public RolJugador() 
    {
    }

    public abstract void aplicarBonificaciones(Jugador jugador);

    public abstract List<Item> obtenerItemsIniciales();

    protected int calcularVidaBonus(int vidaBase) {
        return (int)(vidaBase * multiplicadorVida);
    }

    protected int calcularAtaqueBonus(int ataqueBase) {
        return (int)(ataqueBase * multiplicadorAtaque);
    }

    // Getters
    public int getCapacidadItems() { return capacidadItems; }
    public String getNombreRol() { return nombreRol; }

    
}