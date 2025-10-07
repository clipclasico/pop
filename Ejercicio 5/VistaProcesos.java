import java.util.ArrayList;
import java.util.Scanner;

public class VistaProcesos
{
    private Scanner scanner;
    
    public VistaProcesos()
    {
        this.scanner = new Scanner(System.in);
    }
    
    public void mostrarMenu()
    {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║    SIMULADOR DE SISTEMA OPERATIVO - PROCESOS   ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        System.out.println("1. Agregar Proceso CPU");
        System.out.println("2. Agregar Proceso I/O");
        System.out.println("3. Agregar Daemon");
        System.out.println("4. Agregar Proceso Tiempo Real");
        System.out.println("5. Agregar Proceso en Red");
        System.out.println("6. Listar todos los procesos");
        System.out.println("7. Ejecutar un proceso específico");
        System.out.println("8. Ejecutar todos los procesos");
        System.out.println("9. Buscar proceso por PID");
        System.out.println("10. Eliminar proceso");
        System.out.println("11. Limpiar todos los procesos");
        System.out.println("0. Salir");
        System.out.println("═════════════════════════════════════════════════");
    }
    
    public void mostrarMensaje(String mensaje)
    {
        System.out.println("->" + mensaje);
    }

    public void mostrarError(String error)
    {
        System.out.println("ERROR: " + error);
    }
    
    public void mostrarProcesos(ArrayList<Proceso> procesos)
    {
        if (procesos.isEmpty())
        {
            System.out.println("\n No hay procesos en el sistema.");
            return;
        }
        
        System.out.println("\n┌─────────────────────── LISTA DE PROCESOS ───────────────────────┐");
        for (Proceso p : procesos)
        {
            System.out.println("│ " + p.toString());
        }
        System.out.println("└──────────────────────────────────────────────────────────────────┘");
        System.out.println("Total de procesos: " + procesos.size());
    }
    
    public void mostrarProceso(Proceso p)
    {
        if (p == null)
        {
            System.out.println("⚠ Proceso no encontrado.");
            return;
        }
        System.out.println("\n┌─────────────────────── INFORMACIÓN DEL PROCESO ──────────────────┐");
        System.out.println("│ " + p.toString());
        System.out.println("└───────────────────────────────────────────────────────────────────┘");
    }

    public int leerOpcion()
    {
        System.out.print("\nSeleccione una opción: ");
        try
        {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e)
        {
            return -1;
        }
    }
    
    public String leerTexto(String prompt)
    {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int leerEntero(String prompt)
    {
        System.out.print(prompt);
        try
        {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e)
        {
            return -1;
        }
    }
    
    public boolean confirmar(String mensaje)
    {
        System.out.print(mensaje + " (S/N): ");
        String respuesta = scanner.nextLine().trim().toUpperCase();
        return respuesta.equals("S") || respuesta.equals("SI");
    }

    public void cerrar()
    {
        scanner.close();
        System.out.println("\n Sistema cerrado correctamente. ¡Hasta pronto!");
    }
}