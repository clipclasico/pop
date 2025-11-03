/// --- Main ---

public class Principal
{
    
    private static ControladorDispositivos controlador;
    private static VistaConsola vista;
    
    public static void main(String[] args)
    {
        controlador = new ControladorDispositivos();
        vista = new VistaConsola();
        
        controlador.inicializarSistema();
        
        System.out.println("¡BIENVENIDO AL SISTEMA AGRO-TECNOLÓGICO!");
        System.out.println("\nSistema inicializado correctamente.");
        System.out.println("Catálogo cargado con " + controlador.obtenerCantidadDispositivos() + " dispositivos.\n");
        
        mostrarMenuRecursivo();
    }
    
    private static void mostrarMenuRecursivo()
    {
        vista.mostrarMenu();
        int opcion = vista.solicitarOpcion();
        
        switch (opcion) {
            case 1:
                String listado = controlador.listarTodosDispositivos();
                System.out.println(listado);
                System.out.println("\nPresione Enter para continuar...");
                vista.solicitarTexto("");
                mostrarMenuRecursivo();
                return;
                
            case 2:
                String id = vista.solicitarTexto("\nIngrese el ID del dispositivo: ");
                String resultadoId = controlador.buscarDispositivoPorId(id);
                System.out.println(resultadoId);
                System.out.println("\nPresione Enter para continuar...");
                vista.solicitarTexto("");
                mostrarMenuRecursivo();
                return;
                
            case 3:
                String nombre = vista.solicitarTexto("\nIngrese el nombre (o parte del nombre): ");
                String resultadoNombre = controlador.buscarDispositivosPorNombre(nombre);
                System.out.println(resultadoNombre);
                System.out.println("\nPresione Enter para continuar...");
                vista.solicitarTexto("");
                mostrarMenuRecursivo();
                return;
                
            case 4:
                String resultadoOrden = controlador.ordenarYListarPorConsumo();
                System.out.println(resultadoOrden);
                System.out.println("\nPresione Enter para continuar...");
                vista.solicitarTexto("");
                mostrarMenuRecursivo();
                return;
                
            case 5:
                System.out.println("Gracias por usar el Sistema Agro-Tecnológico. Cerrando sistema...");
                vista.cerrar();
                return;
                
            default:
                System.out.println("\nOpción inválida. Por favor, seleccione una opción del 1 al 5.\n");
                mostrarMenuRecursivo();
                return;
        }
    }
}