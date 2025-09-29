import java.util.Arrays;
import java.util.List;
// Importar otros paquetes necesarios

// Clase ControladorBatalla para manejar la lógica del juego

public class ControladorBatalla
{
    private Batalla modelo;
    private VistaBatalla vista;
    private GeneradorEnemigos generador;

    // Constructor
    public ControladorBatalla()
    {
        this.vista = new VistaBatalla();
        this.generador = new GeneradorEnemigos();
    }

    // Inicializa una nueva batalla
    public void inicializar()
    {
        vista.mostrarIntroduccion();
        
        Jugador jugador = configurarJugador();
        List<Enemigo> enemigos = generarTresOrcos();
        modelo = new Batalla(jugador, enemigos);
        modelo.iniciarBatalla();
    }

    //  Configura el jugador solicitando nombre y rol
    public Jugador configurarJugador()
    {
        String nombre = vista.solicitarNombreJugador();
        if (nombre.isEmpty())
        {
            nombre = "Héroe";
        }
        
        RolJugador rol = vista.mostrarSeleccionRol();
        return new Jugador(nombre, rol);
    }

    // Genera tres enemigos orcos
    public List<Enemigo> generarTresOrcos()
    {
        return generador.generarTresOrcos();
    }

    // Ejecuta el bucle principal del juego
    public void ejecutarJuego()
    {
        boolean continuar = true;
        
        do
        {
            int opcion = vista.mostrarMenuPrincipal();
            
            switch (opcion)
            {
                case 1:
                    inicializar();
                    ejecutarBatalla();
                    break;

                case 2:
                    continuar = false;
                    break;
            }
        } while (continuar);
        
        vista.mostrarMensaje("¡Gracias por jugar!");
        vista.cerrar();
    }

    // Ejecuta la batalla hasta que termine
    private void ejecutarBatalla()
    {
        do
        {
            vista.mostrarEstadoBatalla(modelo);

            if (modelo.getJugador().estaVivo())
            {
                procesarTurnoJugador();
            }
            
            if (modelo.isBatallaActiva() && modelo.getJugador().estaVivo())
            {
                modelo.ejecutarTurnoEnemigos();
            }
            
            if (modelo.verificarFinBatalla())
            {
                manejarFinBatalla();
                break;
            }
            
        } while (modelo.isBatallaActiva());
    }

    // Procesa el turno del jugador
    public void procesarTurnoJugador()
    {
        AccionJugador accion = vista.mostrarMenuTurno();
        
        switch (accion)
        {
            case ATACAR:
                manejarAtaqueJugador();
                break;
                
            case USAR_ITEM:
                manejarUsoItem();
                break;
                
            case PASAR_TURNO:
                modelo.registrarAccion(modelo.getJugador().getNombre() + " pasa su turno");
                break;
                
            case SALIR:
                modelo.setBatallaActiva(false);
                vista.mostrarMensaje("Has abandonado la batalla");
                return;
        }
    }

    // Maneja el ataque del jugador a un enemigo
    private void manejarAtaqueJugador()
    {
        List<Enemigo> enemigosVivos = modelo.getEnemigosVivos();
        
        if (enemigosVivos.isEmpty())
        {
            vista.mostrarError("No hay enemigos para atacar");
            return;
        }
        
        Enemigo objetivo = vista.seleccionarObjetivo(enemigosVivos);
        if (objetivo != null)
        {
            int vidaAntes = objetivo.getPuntosVida();
            modelo.getJugador().atacar(objetivo);
            int danioRealizado = vidaAntes - objetivo.getPuntosVida();
            
            modelo.registrarAccion(modelo.getJugador().getNombre() + " ataca a " + objetivo.getNombre() + " por " + danioRealizado + " de daño");
                                 
            if (!objetivo.estaVivo())
            {
                modelo.registrarAccion(objetivo.getNombre() + " ha sido derrotado");
            }
        }
    }

    // Maneja el uso de un item por parte del jugador
    private void manejarUsoItem()
    {
        List<Item> inventario = modelo.getJugador().getInventario();
        
        if (inventario.isEmpty())
        {
            vista.mostrarError("No tienes items en tu inventario");
            return;
        }
        
        Item item = vista.mostrarInventario(inventario);
        if (item == null)
        {
            return;
        }
        
        if (item instanceof Pocion || item instanceof Elixir || item instanceof Antidoto)
        {
            boolean exito = modelo.getJugador().usarItem(item, modelo.getJugador());
            if (exito)
            {
                modelo.registrarAccion(modelo.getJugador().getNombre() + " usa " + item.getNombre());
            }
            else
            {
                vista.mostrarError("No se pudo usar el item");
            }
        } else if (item instanceof BombaHumo)
        {
            List<Combatiente> grupo = Arrays.asList(modelo.getJugador());
            boolean exito = modelo.getJugador().usarItemMultiple(item, grupo);
            if (exito)
            {
                modelo.registrarAccion(modelo.getJugador().getNombre() + " usa " + item.getNombre() + " (efecto grupal)");
            } else {
                vista.mostrarError("No se pudo usar el item");
            }
        }
    }

    // Maneja el fin de la batalla
    public void manejarFinBatalla()
    {
        vista.mostrarEstadoBatalla(modelo);
        
        if (modelo.verificarVictoria())
        {
            vista.mostrarVictoria();
            modelo.getJugador().ganarExperiencia(100);
        }
        else
        {
            vista.mostrarDerrota();
        }
        
        vista.esperarEnter();
    }

    // Reinicia el juego
    public void reiniciarJuego()
    {
        this.modelo = null;
    }
}