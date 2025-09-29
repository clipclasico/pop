import java.util.List;
// Importar los paquetes necesarios

// Clase que representa un elixir que incrementa el ataque temporalmente


public class Elixir extends Item
{
    private int bonusAtaque;
    private int duracionTurnos;

    // Constructor
    public Elixir(int cantidad)
    {
        super("Elixir de Fuerza", "Incrementa el ataque temporalmente", cantidad);
        this.bonusAtaque = 15;
        this.duracionTurnos = 3;
        this.tipoItem = TipoItem.ATAQUE;
    }

    @Override
    public boolean usar(Combatiente usuario, Combatiente objetivo)
    {
        if (!tieneDisponible() || !objetivo.estaVivo())
        {
            return false;
        }

        incrementarAtaque(objetivo);
        consumir();
        return true;
    }

    @Override
    public boolean usar(Combatiente usuario, List<Combatiente> objetivos)
    {
        if (!tieneDisponible()) return false;

        boolean usado = false;
        for (Combatiente objetivo : objetivos)
        {
            if (objetivo.estaVivo())
            {
                incrementarAtaque(objetivo);
                usado = true;
            }
        }
        
        if (usado) consumir();
        return usado;
    }

    public void incrementarAtaque(Combatiente objetivo)
    {
        objetivo.poderAtaque += bonusAtaque;
    }

    // Getters
    public int getBonusAtaque()
    {
        return bonusAtaque;
    }

    public int getDuracionTurnos()
    {
        return duracionTurnos;
    }

    @Override
    public String toString()
    {
        return super.toString() + " (+" + bonusAtaque + " ataque por " + duracionTurnos + " turnos)";
    }
}