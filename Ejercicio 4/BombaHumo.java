import java.util.Arrays;
import java.util.List;
// importar los paquetes necesarios

// Clase para representar una bomba de humo que proporciona ventaja táctica al grupo

public class BombaHumo extends Item
{
    private double efectoEsquiva;
    private int duracion;

    // Constructor
    public BombaHumo(int cantidad)
    {
        super("Bomba de Humo", "Proporciona ventaja táctica al grupo", cantidad);
        this.efectoEsquiva = 0.3;
        this.duracion = 2;
        this.tipoItem = TipoItem.DEFENSA;
    }

    @Override // Usar la bomba de humo en un objetivo específico
    public boolean usar(Combatiente usuario, Combatiente objetivo)
    {
        List<Combatiente> grupo = Arrays.asList(objetivo);
        return usar(usuario, grupo);
    }

    @Override // Usar la bomba de humo en múltiples objetivos
    public boolean usar(Combatiente usuario, List<Combatiente> objetivos)
    {
        if (!tieneDisponible()) return false;

        aplicarEfectoGrupal(objetivos);
        consumir();
        return true;
    }

    // Método privado para aplicar el efecto de la bomba de humo a un grupo
    public void aplicarEfectoGrupal(List<Combatiente> objetivos)
    {
        for (Combatiente objetivo : objetivos)
        {
            if (objetivo.estaVivo())
            {
                int bonusDefensa = (int)(objetivo.getPuntosVidaMax() * efectoEsquiva);
                objetivo.curar(bonusDefensa);
                System.out.println(objetivo.getNombre() + " recibe un bonus defensivo de " + bonusDefensa + " PV por " + duracion + " turnos");
            }
        }
    }

    @Override // Representación en cadena de la bomba de humo
    public String toString()
    {
        return super.toString() + " (Efecto grupal defensivo)";
    }
}
