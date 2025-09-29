import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
// Importar los paquetes necesarios

// Clase para manejar la lógica de una batalla entre un jugador y enemigos

public class Batalla
{
    private Jugador jugador;
    private List<Enemigo> enemigos;
    private Queue<String> ultimasAcciones;
    private boolean batallaActiva;
    private int contadorTurnos;

    // Constructor
    public Batalla(Jugador jugador, List<Enemigo> enemigos)
    {
        this.jugador = jugador;
        this.enemigos = new ArrayList<>(enemigos);
        this.ultimasAcciones = new LinkedList<>();
        this.batallaActiva = true;
        this.contadorTurnos = 0;
    }

    // Inicia la batalla
    public void iniciarBatalla()
    {
        registrarAccion("¡La batalla ha comenzado!");
        registrarAccion(jugador.getNombre() + " (" + jugador.getRol().getNombreRol() + ") se enfrenta a " + enemigos.size() + " enemigos");
        
        for (Enemigo enemigo : enemigos)
        {
            registrarAccion(enemigo.getNombre() + " aparece en el campo de batalla");
        }
    }

    // Ejecuta un turno completo de la batalla
    public void ejecutarTurno()
    {
        if (!batallaActiva) return;
        
        contadorTurnos++;
        registrarAccion("=== Turno " + contadorTurnos + " ===");
        
        // Turno del jugador
        if (jugador.estaVivo())
        {
            ejecutarTurnoJugador();
        }
        
        // Turnos de enemigos
        if (batallaActiva)
        {
            ejecutarTurnoEnemigos();
        }
        
        // Verificar fin de batalla
        if (verificarFinBatalla())
        {
            batallaActiva = false;
        }
    }

    public void ejecutarTurnoJugador()
    {

    }

    // Ejecuta los turnos de todos los enemigos vivos
    public void ejecutarTurnoEnemigos()
    {
        for (Enemigo enemigo : enemigos)
        {
            if (enemigo.estaVivo())
            {
                AccionEnemigo accion = enemigo.decidirAccion();
                procesarAccionEnemigo(enemigo, accion);
                
                
                if (enemigo.getEsJefe() && enemigo instanceof Orco)
                {
                    ((Orco) enemigo).regeneracionRapida();
                }
            }
        }
    }

    // Procesa la acción decidida por un enemigo
    private void procesarAccionEnemigo(Enemigo enemigo, AccionEnemigo accion)
    {
        switch (accion)
        {
            case ATACAR:
                enemigo.atacar(jugador);
                registrarAccion(enemigo.getNombre() + " ataca a " + jugador.getNombre() + " por " + enemigo.getPoderAtaque() + " de daño");
                break;
                
            case HABILIDAD_ESPECIAL:
                enemigo.usarHabilidadEspecial(jugador);
                registrarAccion(enemigo.getNombre() + " usa su habilidad especial");
                break;
                
            case HABILIDAD_JEFE:
                if (enemigo.getEsJefe())
                {
                    List<Combatiente> objetivos = Arrays.asList(jugador);
                    enemigo.usarHabilidadJefe(objetivos);
                    registrarAccion(enemigo.getNombre() + " usa su habilidad de jefe");
                }
                break;
                
            case PASAR:
                registrarAccion(enemigo.getNombre() + " pasa su turno");
                break;
        }
    }

    // Muestra el estado actual de la batalla
    public boolean verificarFinBatalla()
    {
        return !jugador.estaVivo() || getEnemigosVivos().isEmpty();
    }

    // Verifica si el jugador ha ganado la batalla
    public boolean verificarVictoria()
    {
        return jugador.estaVivo() && getEnemigosVivos().isEmpty();
    }

    // Registra una acción en el historial de la batalla
    public void registrarAccion(String accion)
    {
        ultimasAcciones.offer(accion);
        if (ultimasAcciones.size() > 3)
        {
            ultimasAcciones.poll();
        }
    }

    // Obtiene una lista de enemigos que aún están vivos
    public List<Enemigo> getEnemigosVivos()
    {
        List<Enemigo> vivos = new ArrayList<>();
        for (Enemigo enemigo : enemigos)
        {
            if (enemigo.estaVivo())
            {
                vivos.add(enemigo);
            }
        }
        return vivos;
    }

    // Getters
    public Jugador getJugador()
    {
        return jugador;
    }

    public List<Enemigo> getEnemigos()
    {
        return new ArrayList<>(enemigos);
    }

    public Queue<String> getUltimasAcciones()
    {
        return new LinkedList<>(ultimasAcciones);
    }

    // Verifica si la batalla sigue activa
    public boolean isBatallaActiva()
    {
        return batallaActiva;
    }
    
    // Setter
    public void setBatallaActiva(boolean activa) { this.batallaActiva = activa; }
}