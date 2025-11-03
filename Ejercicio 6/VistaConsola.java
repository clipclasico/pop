/// --- Vista ---

import java.util.Scanner;

class VistaConsola
{
    private Scanner scanner;
    
    public VistaConsola()
    {
        this.scanner = new Scanner(System.in);
    }
    
    public void mostrarMenu() {
        System.out.println("SISTEMA AGRO-TECNOLÓGICO - COOPERATIVA");
        System.out.println("  1. Listar todos los dispositivos");
        System.out.println("  2. Buscar dispositivo por ID");
        System.out.println("  3. Buscar dispositivos por nombre");
        System.out.println("  4. Ordenar dispositivos por consumo eléctrico");
        System.out.println("  5. Salir del sistema\n");
        System.out.print("Seleccione una opción: ");
    }
    
    public void mostrarMensaje(String mensaje)
    {
        System.out.println(mensaje);
    }
    
    public int solicitarOpcion()
    {
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine();
            return opcion;
        } catch (Exception e)
        {
            scanner.nextLine();
            return -1;
        }
    }
    
    public String solicitarTexto(String prompt)
    {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    public void cerrar()
    {
        scanner.close();
    }
}