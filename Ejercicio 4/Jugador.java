import java.util.ArrayList;
import java.util.List;

class Jugador extends Combatiente {
    private List<Item> inventario;
    private RolJugador rol;
    private int experiencia;
    private int puntosVidaMax;

    public Jugador(String nombre, RolJugador rol) {
        super(nombre, 100, 20);
        this.rol = rol;
        this.inventario = new ArrayList<>();
        this.experiencia = 0;
        
        rol.aplicarBonificaciones(this);
        
        List<Item> itemsIniciales = rol.obtenerItemsIniciales();
        for (Item item : itemsIniciales) {
            agregarItem(item);
        }
        
        mostrarMensajeInicio();
    }

    @Override
    public void tomarTurno() 
    {

    }

    public boolean usarItem(Item item, Combatiente objetivo) {
        if (!inventario.contains(item) || !item.tieneDisponible()) {
            return false;
        }
        
        boolean exito = item.usar(this, objetivo);
        if (exito && item.getCantidad() <= 0) {
            inventario.remove(item);
        }
        return exito;
    }

    public boolean usarItemMultiple(Item item, List<Combatiente> objetivos) {
        if (!inventario.contains(item) || !item.tieneDisponible()) {
            return false;
        }
        
        boolean exito = item.usar(this, objetivos);
        if (exito && item.getCantidad() <= 0) {
            inventario.remove(item);
        }
        return exito;
    }

    public boolean agregarItem(Item item) {
        if (inventario.size() >= rol.getCapacidadItems()) {
            return false;
        }
        inventario.add(item);
        return true;
    }

    public void removerItem(Item item) {
        inventario.remove(item);
    }

    public void pasarTurno() {
        
    }

    public void cambiarRol(RolJugador nuevoRol) {
        this.rol = nuevoRol;
        nuevoRol.aplicarBonificaciones(this);
    }

    public void ganarExperiencia(int exp) {
        this.experiencia += exp;
    }

    public List<Item> getInventario() { return new ArrayList<>(inventario); }
    public RolJugador getRol() { return rol; }
    public int getExperiencia() { return experiencia; }
    
    public int getPuntosVidaMax() { return this.puntosVidaMax; }
    @Override
    public int getPoderAtaque() { return this.poderAtaque; }

    public void setPuntosVidaMax(int puntosVidaMax) {
        this.puntosVidaMax = puntosVidaMax;
        this.puntosVida = puntosVidaMax;
    }

    public void setPoderAtaque(int poderAtaque) {
        this.poderAtaque = poderAtaque;
    }


    @Override
    public void mostrarMensajeInicio() {
    }

    @Override
    public void mostrarMensajeMuerte() {
    }
}
