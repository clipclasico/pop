import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
// Importar los paquetes necesarios

// Clase para manejar la vista y la interacción con el usuario

public class VistaBatalla
{
    private Scanner scanner;
    private static final String SEPARADOR = "=====================================";

    public VistaBatalla()
    {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenuPrincipal()
    {
        limpiarPantalla();
        System.out.println(SEPARADOR);
        System.out.println("  Super Juego de Roles Orcos VS Humanos");
        System.out.println("  Universidad del Valle de Guatemala");
        System.out.println("  POO - Ejercicio 4");
        System.out.println(SEPARADOR);
        System.out.println("1. Nueva Batalla");
        System.out.println("2. Salir");
        System.out.println(SEPARADOR);
        System.out.print("Selecciona una opción: ");
        
        return leerEntero(1, 2);
    }

    public AccionJugador mostrarMenuTurno() {
        System.out.println("\n--- Tu Turno ---");
        System.out.println("1. Atacar enemigo");
        System.out.println("2. Usar item");
        System.out.println("3. Pasar turno");
        System.out.println("4. Salir de la batalla");
        System.out.print("¿Qué deseas hacer? ");
        
        int opcion = leerEntero(1, 4);
        
        switch (opcion) {
            case 1: return AccionJugador.ATACAR;
            case 2: return AccionJugador.USAR_ITEM;
            case 3: return AccionJugador.PASAR_TURNO;
            case 4: return AccionJugador.SALIR;
            default: return AccionJugador.PASAR_TURNO;
        }
    }

    public void mostrarEstadoBatalla(Batalla batalla) {
        limpiarPantalla();
        System.out.println(SEPARADOR);
        System.out.println("    ESTADO DE LA BATALLA");
        System.out.println(SEPARADOR);
        
        // Estado del jugador
        System.out.println("JUGADOR:");
        System.out.println("  " + batalla.getJugador().toString());
        
        // Estado de enemigos
        System.out.println("\nENEMIGOS:");
        List<Enemigo> enemigos = batalla.getEnemigos();
        for (int i = 0; i < enemigos.size(); i++) {
            Enemigo enemigo = enemigos.get(i);
            String estado = enemigo.estaVivo() ? "VIVO" : "MUERTO";
            System.out.println("  " + (i + 1) + ". " + enemigo.toString() + " [" + estado + "]");
        }
        
        // Últimas acciones
        mostrarUltimasAcciones(batalla.getUltimasAcciones());
    }

    public void mostrarUltimasAcciones(Queue<String> acciones) {
        System.out.println("\n--- ÚLTIMAS ACCIONES ---");
        if (acciones.isEmpty()) {
            System.out.println("  No hay acciones registradas");
        } else {
            for (String accion : acciones) {
                System.out.println("  • " + accion);
            }
        }
        System.out.println(SEPARADOR);
    }

    public Enemigo seleccionarObjetivo(List<Enemigo> enemigos) {
        List<Enemigo> enemigosVivos = new ArrayList<>();
        for (Enemigo enemigo : enemigos) {
            if (enemigo.estaVivo()) {
                enemigosVivos.add(enemigo);
            }
        }
        
        if (enemigosVivos.isEmpty()) {
            return null;
        }
        
        System.out.println("\n--- SELECCIONAR OBJETIVO ---");
        for (int i = 0; i < enemigosVivos.size(); i++) {
            System.out.println((i + 1) + ". " + enemigosVivos.get(i).getNombre() + 
                             " (Vida: " + enemigosVivos.get(i).getPuntosVida() + ")");
        }
        
        System.out.print("Selecciona objetivo (1-" + enemigosVivos.size() + "): ");
        int seleccion = leerEntero(1, enemigosVivos.size()) - 1;
        
        return enemigosVivos.get(seleccion);
    }

    public Item mostrarInventario(List<Item> items) {
        if (items.isEmpty()) {
            mostrarMensaje("Tu inventario está vacío");
            return null;
        }
        
        System.out.println("\n--- INVENTARIO ---");
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (item.tieneDisponible()) {
                System.out.println((i + 1) + ". " + item.toString());
            }
        }
        
        System.out.print("Selecciona item (1-" + items.size() + ") o 0 para cancelar: ");
        int seleccion = leerEntero(0, items.size());
        
        if (seleccion == 0) {
            return null;
        }
        
        Item itemSeleccionado = items.get(seleccion - 1);
        if (!itemSeleccionado.tieneDisponible()) {
            mostrarError("Ese item no tiene usos disponibles");
            return null;
        }
        
        return itemSeleccionado;
    }

    public RolJugador mostrarSeleccionRol() {
        System.out.println("\n--- SELECCIÓN DE ROL ---");
        System.out.println("1. Guerrero - Más vida y ataque, pocos items");
        System.out.println("2. Explorador - Stats normales, muchos items");
        System.out.print("Selecciona tu rol (1-2): ");
        
        int seleccion = leerEntero(1, 2);
        
        return seleccion == 1 ? new Guerrero() : new Explorador();
    }

    public String solicitarNombreJugador() {
        System.out.print("Ingresa el nombre de tu personaje: ");
        return leerEntrada().trim();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarError(String error) {
        System.out.println("ERROR: " + error);
    }

    public void mostrarVictoria() {
        limpiarPantalla();
        System.out.println(SEPARADOR);
        System.out.println("    ¡VICTORIA!");
        System.out.println("  Has derrotado a todos los enemigos");
        System.out.println(SEPARADOR);
    }

    public void mostrarDerrota()
    {
        limpiarPantalla();
        System.out.println(SEPARADOR);
        System.out.println("    DERROTA");
        System.out.println("  Has sido derrotado en combate");
        System.out.println(SEPARADOR);
    }

    public String leerEntrada()
    {
        return scanner.nextLine();
    }

    public int leerEntero(int min, int max) {
        int numero = -1;
        boolean entradaValida = false;
        
        do {
            try {
                String entrada = scanner.nextLine().trim();
                numero = Integer.parseInt(entrada);
                
                if (numero >= min && numero <= max) {
                    entradaValida = true;
                } else {
                    System.out.print("Por favor ingresa un número entre " + min + " y " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Por favor ingresa un número válido: ");
            }
        } while (!entradaValida);
        
        return numero;
    }

    public void esperarEnter() {
        System.out.print("\nPresiona Enter para continuar...");
        scanner.nextLine();
    }

    public void limpiarPantalla() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public void mostrarIntroduccion() {
        limpiarPantalla();
        System.out.println(SEPARADOR);
        System.out.println("  BIENVENIDO AL JUEGO DE BATALLA");
        System.out.println(SEPARADOR);
        System.out.println("Te enfrentarás a 3 Orcos poderosos");
        System.out.println("Algunos pueden ser jefes más peligrosos");
        System.out.println("Elige tu rol y prepárate para la batalla");
        System.out.println(SEPARADOR);
    }

    public void cerrar() {
        scanner.close();
    }
}