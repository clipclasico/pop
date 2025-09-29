import java.util.List;
// importar los paquetes necesarios

// Clase para representar un antídoto que cura efectos negativos

public class Antidoto extends Item
{
    private double efectividad;

    // Constructor
    public Antidoto(int cantidad)
    {
        super("Antídoto", "Cura envenenamiento y efectos negativos", cantidad);
        this.efectividad = 1.0;
        this.tipoItem = TipoItem.UTILIDAD;
    }

    @Override // Usar el antídoto en un objetivo específico
    public boolean usar(Combatiente usuario, Combatiente objetivo)
    {
        if (!tieneDisponible() || !objetivo.estaVivo())
        {
            return false;
        }

        curarEnvenenamiento(objetivo);
        consumir();
        return true;
    }

    @Override // Usar el antídoto en múltiples objetivos
    public boolean usar(Combatiente usuario, List<Combatiente> objetivos)
    {
        if (!tieneDisponible()) return false;

        boolean usado = false;
        for (Combatiente objetivo : objetivos)
        {
            if (objetivo.estaVivo())
            {
                curarEnvenenamiento(objetivo);
                usado = true;
            }
        }
        
        if (usado) consumir();
        return usado;
    }

    // Método privado para curar envenenamiento
    public void curarEnvenenamiento(Combatiente objetivo) 
    {
        int curacionBase = 20;
        int curacionFinal = (int)(curacionBase * efectividad);
        objetivo.curar(curacionFinal);
    }

    @Override // Representación en cadena del antídoto
    public String toString()
    {
        return super.toString() + " (Cura efectos negativos)";
    }    
}