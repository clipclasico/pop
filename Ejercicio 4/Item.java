import java.util.List;

abstract class Item {
    protected String nombre;
    protected String descripcion;
    protected int cantidad;
    protected TipoItem tipoItem;

    public Item(String nombre, String descripcion, int cantidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public abstract boolean usar(Combatiente usuario, Combatiente objetivo);

    public abstract boolean usar(Combatiente usuario, List<Combatiente> objetivos);

    public boolean tieneDisponible() {
        return cantidad > 0;
    }

    public void consumir() {
        if (cantidad > 0) {
            cantidad--;
        }
    }

    // Getters
    public int getCantidad()
    {
        return cantidad;
    }

    public String getNombre()
    {
        return nombre;
    }
    public String getDescripcion()
    {
        return descripcion;
    }

    @Override
    public String toString() {
        return nombre + " x" + cantidad + " - " + descripcion;
    }
}
