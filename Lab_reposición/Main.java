/// --------------------------------
/// Main.java
/// Autor: Giovanni Orozco
/// Programa para Mercedez-Benz
/// Sistema de Radio Inteligente
/// --------------------------------

import java.util.Scanner;

public class Main
{
    
    private static Scanner scanner = new Scanner(System.in);
    private static Radio radioActual = null;

    public static void main(String[] args)
    {
        mostrarBanner();
        menuSeleccionClase();
    }

    private static void mostrarBanner()
    {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║                                                  ║");
        System.out.println("║            MERCEDES-BENZ RADIO SYSTEM            ║");
        System.out.println("║                                                  ║");
        System.out.println("║          Simulador de Radio Inteligente          ║");
        System.out.println("║             Universidad del Valle                ║");
        System.out.println("║                                                  ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");
    }

    private static void menuSeleccionClase()
    {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│  SELECCIONE CLASE DE VEHÍCULO       │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│  1. Clase A (Premium)               │");
        System.out.println("│     - Speaker/Auriculares           │");
        System.out.println("│     - Planificar Viajes             │");
        System.out.println("│                                     │");
        System.out.println("│  2. Clase B (Ejecutivo)             │");
        System.out.println("│     - Rellamada                     │");
        System.out.println("│     - Tarjetas de Presentación      │");
        System.out.println("│                                     │");
        System.out.println("│  3. Clase C (Urbano)                │");
        System.out.println("│     - Llamada en Espera             │");
        System.out.println("│     - Pronóstico del Tiempo         │");
        System.out.println("│                                     │");
        System.out.println("│  0. Salir                           │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.print("Opción: ");
        
        String opcion = scanner.nextLine();
        procesarSeleccionClase(opcion);
    }

    private static void procesarSeleccionClase(String opcion)
    {
        switch (opcion)
        {
            case "1":
                radioActual = new RadioClaseA();
                System.out.println("\nRadio Clase A inicializado.");
                menuPrincipal();
                break;
            case "2":
                radioActual = new RadioClaseB();
                System.out.println("\nRadio Clase B inicializado.");
                menuPrincipal();
                break;
            case "3":
                radioActual = new RadioClaseC();
                System.out.println("\nRadio Clase C inicializado.");
                menuPrincipal();
                break;
            case "0":
                System.out.println("\n¡Gracias por usar Mercedes-Benz Radio System!");
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.println("Opción inválida. Intente nuevamente.");
                menuSeleccionClase();
        }
    }

    private static void menuPrincipal()
    {
        System.out.println("\n" + radioActual.obtenerEstado());
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│       MENÚ PRINCIPAL                │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│  1. Encender/Apagar                 │");
        System.out.println("│  2. Controlar Volumen               │");
        System.out.println("│  3. Modo Radio                      │");
        System.out.println("│  4. Modo Reproducción               │");
        System.out.println("│  5. Modo Teléfono                   │");
        System.out.println("│  6. Modo Productividad              │");
        System.out.println("│  0. Cambiar Clase de Vehículo       │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.print("Opción: ");
        
        String opcion = scanner.nextLine();
        procesarMenuPrincipal(opcion);
    }

    private static void procesarMenuPrincipal(String opcion)
    {
        switch (opcion)
        {
            case "1":
                menuEncendido();
                break;
            case "2":
                menuVolumen();
                break;
            case "3":
                radioActual.cambiarModo("RADIO");
                menuModoRadio();
                break;
            case "4":
                radioActual.cambiarModo("REPRODUCCION");
                menuModoReproduccion();
                break;
            case "5":
                radioActual.cambiarModo("TELEFONO");
                menuModoTelefono();
                break;
            case "6":
                radioActual.cambiarModo("PRODUCTIVIDAD");
                menuModoProductividad();
                break;
            case "0":
                menuSeleccionClase();
                break;
            default:
                System.out.println("Opción inválida.");
                menuPrincipal();
        }
    }

    private static void menuEncendido()
    {
        System.out.println("\n1. Encender");
        System.out.println("2. Apagar");
        System.out.println("0. Volver");
        System.out.print("Opción: ");
        
        String opcion = scanner.nextLine();
        procesarMenuEncendido(opcion);
    }

    private static void procesarMenuEncendido(String opcion)
    {
        switch (opcion)
        {
            case "1":
                radioActual.encender();
                menuPrincipal();
                break;
            case "2":
                radioActual.apagar();
                menuPrincipal();
                break;
            case "0":
                menuPrincipal();
                break;
            default:
                System.out.println("❌ Opción inválida.");
                menuEncendido();
        }
    }

    private static void menuVolumen()
    {
        System.out.println("\n1. Subir Volumen (+1)");
        System.out.println("2. Bajar Volumen (-1)");
        System.out.println("0. Volver");
        System.out.print("Opción: ");
        
        String opcion = scanner.nextLine();
        procesarMenuVolumen(opcion);
    }

    private static void procesarMenuVolumen(String opcion)
    {
        switch (opcion)
        {
            case "1":
                radioActual.subirVolumen();
                menuVolumen();
                break;
            case "2":
                radioActual.bajarVolumen();
                menuVolumen();
                break;
            case "0":
                menuPrincipal();
                break;
            default:
                System.out.println("Opción inválida.");
                menuVolumen();
        }
    }

    private static void menuModoRadio()
    {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│       MODO RADIO FM/AM              │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│  1. Cambiar Banda (FM/AM)           │");
        System.out.println("│  2. Subir Emisora (+0.5)            │");
        System.out.println("│  3. Bajar Emisora (-0.5)            │");
        System.out.println("│  4. Guardar Emisora                 │");
        System.out.println("│  5. Cargar Emisora                  │");
        System.out.println("│  0. Volver                          │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.print("Opción: ");
        
        String opcion = scanner.nextLine();
        procesarMenuRadio(opcion);
    }

    private static void procesarMenuRadio(String opcion)
    {
        switch (opcion)
        {
            case "1":
                radioActual.cambiarBanda();
                menuModoRadio();
                break;
            case "2":
                radioActual.subirEmisora();
                menuModoRadio();
                break;
            case "3":
                radioActual.bajarEmisora();
                menuModoRadio();
                break;
            case "4":
                System.out.print("Ingrese posición (1-50): ");
                String pos = scanner.nextLine();
                try {
                    radioActual.guardarEmisora(Integer.parseInt(pos));
                } catch (NumberFormatException e) {
                    System.out.println("Debe ingresar un número válido.");
                }
                menuModoRadio();
                break;
            case "5":
                System.out.print("Ingrese posición (1-50): ");
                String posCargar = scanner.nextLine();
                try {
                    radioActual.cargarEmisora(Integer.parseInt(posCargar));
                } catch (NumberFormatException e) {
                    System.out.println("Debe ingresar un número válido.");
                }
                menuModoRadio();
                break;
            case "0":
                menuPrincipal();
                break;
            default:
                System.out.println("Opción inválida.");
                menuModoRadio();
        }
    }

    private static void menuModoReproduccion()
    {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│     MODO REPRODUCCIÓN               │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│  1. Seleccionar Lista               │");
        System.out.println("│  2. Siguiente Canción               │");
        System.out.println("│  3. Canción Anterior                │");
        System.out.println("│  4. Ver Canción Actual              │");
        System.out.println("│  0. Volver                          │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.print("Opción: ");
        
        String opcion = scanner.nextLine();
        procesarMenuReproduccion(opcion);
    }

    private static void procesarMenuReproduccion(String opcion)
    {
        switch (opcion)
        {
            case "1":
                System.out.println("\nListas disponibles:");
                System.out.println("- Rock Classics");
                System.out.println("- Pop Hits");
                System.out.println("- Jazz Lounge");
                System.out.print("Ingrese nombre de lista: ");
                String nombreLista = scanner.nextLine();
                radioActual.seleccionarLista(nombreLista);
                menuModoReproduccion();
                break;
            case "2":
                radioActual.siguienteCancion();
                menuModoReproduccion();
                break;
            case "3":
                radioActual.cancionAnterior();
                menuModoReproduccion();
                break;
            case "4":
                Cancion actual = radioActual.obtenerCancionActual();
                if (actual != null) {
                    System.out.println("\n♪ Reproduciendo:");
                    System.out.println(actual);
                } else {
                    System.out.println("No hay canción seleccionada.");
                }
                menuModoReproduccion();
                break;
            case "0":
                menuPrincipal();
                break;
            default:
                System.out.println("Opción inválida.");
                menuModoReproduccion();
        }
    }

    private static void menuModoTelefono()
    {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│       MODO TELÉFONO                 │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│  1. Conectar Teléfono               │");
        System.out.println("│  2. Desconectar Teléfono            │");
        System.out.println("│  3. Ver Contactos                   │");
        System.out.println("│  4. Llamar Contacto                 │");
        System.out.println("│  5. Finalizar Llamada               │");
        
        if (radioActual instanceof RadioClaseA)
            {
            System.out.println("│  6. Speaker/Auriculares (Clase A)   │");
        } else if (radioActual instanceof RadioClaseB)
            {
            System.out.println("│  7. Rellamar Último (Clase B)       │");
        } else if (radioActual instanceof RadioClaseC)
            {
            System.out.println("│  8. Llamada en Espera (Clase C)     │");
        }
        
        System.out.println("│  0. Volver                          │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.print("Opción: ");
        
        String opcion = scanner.nextLine();
        procesarMenuTelefono(opcion);
    }

    private static void procesarMenuTelefono(String opcion)
    {
        switch (opcion)
        {
            case "1":
                radioActual.conectarTelefono();
                menuModoTelefono();
                break;
            case "2":
                radioActual.desconectarTelefono();
                menuModoTelefono();
                break;
            case "3":
                radioActual.mostrarContactos();
                menuModoTelefono();
                break;
            case "4":
                System.out.print("Ingrese nombre del contacto: ");
                String nombre = scanner.nextLine();
                radioActual.llamarContacto(nombre);
                menuModoTelefono();
                break;
            case "5":
                radioActual.finalizarLlamada();
                menuModoTelefono();
                break;
            case "6":
                if (radioActual instanceof RadioClaseA)
                    {
                    menuAudioClaseA();
                } else {
                    System.out.println("Opción no disponible.");
                    menuModoTelefono();
                }
                break;
            case "7":
                if (radioActual instanceof RadioClaseB)
                    {
                    ((RadioClaseB) radioActual).rellamarUltimo();
                    menuModoTelefono();
                } else {
                    System.out.println("Opción no disponible.");
                    menuModoTelefono();
                }
                break;
            case "8":
                if (radioActual instanceof RadioClaseC)
                    {
                    menuLlamadaEsperaClaseC();
                } else {
                    System.out.println("Opción no disponible.");
                    menuModoTelefono();
                }
                break;
            case "0":
                menuPrincipal();
                break;
            default:
                System.out.println("Opción inválida.");
                menuModoTelefono();
        }
    }

    private static void menuAudioClaseA()
    {
        System.out.println("\n1. Cambiar a Speaker");
        System.out.println("2. Cambiar a Auriculares");
        System.out.println("0. Volver");
        System.out.print("Opción: ");
        
        String opcion = scanner.nextLine();
        procesarMenuAudioClaseA(opcion);
    }

    private static void procesarMenuAudioClaseA(String opcion)
    {
        RadioClaseA radioA = (RadioClaseA) radioActual;
        switch (opcion)
        {
            case "1":
                radioA.cambiarASpeaker();
                menuModoTelefono();
                break;
            case "2":
                radioA.cambiarAAuriculares();
                menuModoTelefono();
                break;
            case "0":
                menuModoTelefono();
                break;
            default:
                System.out.println("Opción inválida.");
                menuAudioClaseA();
        }
    }

    private static void menuLlamadaEsperaClaseC()
    {
        System.out.println("\n1. Cambiar a Llamada en Espera");
        System.out.println("2. Simular Llamada Entrante");
        System.out.println("0. Volver");
        System.out.print("Opción: ");
        
        String opcion = scanner.nextLine();
        procesarMenuLlamadaEsperaClaseC(opcion);
    }

    private static void procesarMenuLlamadaEsperaClaseC(String opcion)
    {
        RadioClaseC radioC = (RadioClaseC) radioActual;
        switch (opcion)
        {
            case "1":
                radioC.cambiarALlamadaEnEspera();
                menuModoTelefono();
                break;
            case "2":
                System.out.print("Ingrese nombre del contacto entrante: ");
                String nombre = scanner.nextLine();
                Contacto contacto = radioC.buscarContactoRecursivo(nombre, 0);
                if (contacto != null) {
                    radioC.recibirLlamadaEntrante(contacto);
                } else {
                    System.out.println("Contacto no encontrado.");
                }
                menuModoTelefono();
                break;
            case "0":
                menuModoTelefono();
                break;
            default:
                System.out.println("Opción inválida.");
                menuLlamadaEsperaClaseC();
        }
    }

    private static void menuModoProductividad()
    {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│     MODO PRODUCTIVIDAD              │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│  1. Ver Funcionalidad               │");
        
        if (radioActual instanceof RadioClaseA)
            {
            System.out.println("│  2. Planificar Viaje (Clase A)      │");
        } else if (radioActual instanceof RadioClaseB)
            {
            System.out.println("│  3. Agregar Tarjeta (Clase B)       │");
        } else if (radioActual instanceof RadioClaseC)
            {
            System.out.println("│  4. Actualizar Clima (Clase C)      │");
        }
        
        System.out.println("│  0. Volver                          │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.print("Opción: ");
        
        String opcion = scanner.nextLine();
        procesarMenuProductividad(opcion);
    }

    private static void procesarMenuProductividad(String opcion)
    {
        switch (opcion)
        {
            case "1":
                System.out.println(radioActual.usarFuncionalidadProductividad());
                menuModoProductividad();
                break;
            case "2":
                if (radioActual instanceof RadioClaseA)
                    {
                    System.out.print("Ingrese destino: ");
                    String destino = scanner.nextLine();
                    System.out.print("Ingrese fecha (DD/MM/YYYY HH:MM): ");
                    String fecha = scanner.nextLine();
                    ((RadioClaseA) radioActual).planificarViaje(destino, fecha);
                    menuModoProductividad();
                } else {
                    System.out.println("Opción no disponible.");
                    menuModoProductividad();
                }
                break;
            case "3":
                if (radioActual instanceof RadioClaseB)
                    {
                    System.out.print("Ingrese información de tarjeta: ");
                    String tarjeta = scanner.nextLine();
                    ((RadioClaseB) radioActual).agregarTarjeta(tarjeta);
                    menuModoProductividad();
                } else {
                    System.out.println("Opción no disponible.");
                    menuModoProductividad();
                }
                break;
            case "4":
                if (radioActual instanceof RadioClaseC)
                    {
                    System.out.print("Ingrese nuevo pronóstico: ");
                    String clima = scanner.nextLine();
                    ((RadioClaseC) radioActual).actualizarPronosticoManual(clima);
                    menuModoProductividad();
                } else {
                    System.out.println("Opción no disponible.");
                    menuModoProductividad();
                }
                break;
            case "0":
                menuPrincipal();
                break;
            default:
                System.out.println("Opción inválida.");
                menuModoProductividad();
        }
    }
}