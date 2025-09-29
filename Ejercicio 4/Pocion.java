import java.util.List;
import java.util.Random;

// Clase para pociones de vida

public class Pocion extends Item {
private int cantidadCura;
private int curacionMinima;
private static final Random random = new Random();

public Pocion(int cantidad) {
    super("Poción de Vida", "Restaura puntos de vida", cantidad);
    this.cantidadCura = 40;
    this.curacionMinima = 25;
    this.tipoItem = TipoItem.CURACION;
}

@Override
public boolean usar(Combatiente usuario, Combatiente objetivo) {
    if (!tieneDisponible() || !objetivo.estaVivo()) {
        return false;
    }

    int curacion = curar(objetivo);
    System.out.println(objetivo.getNombre() + " recuperó " + curacion + " PV"); // Mensaje al jugador
    consumir();
    return true;
}

@Override
public boolean usar(Combatiente usuario, List<Combatiente> objetivos) {
    if (!tieneDisponible()) return false;

    boolean usado = false;
    for (Combatiente objetivo : objetivos) {
        if (objetivo.estaVivo()) {
            int curacion = curar(objetivo);
            System.out.println(objetivo.getNombre() + " recuperó " + curacion + " PV");
            usado = true;
        }
    }
    
    if (usado) consumir();
    return usado;
}

public int curar(Combatiente objetivo) {
    int curacion = curacionMinima + random.nextInt(cantidadCura - curacionMinima + 1);
    objetivo.curar(curacion);
    return curacion;
}

public int getCantidadCura() { return cantidadCura; }

@Override
public String toString() {
    return super.toString() + " (Cura: " + curacionMinima + "-" + cantidadCura + " PV)";
}

}
