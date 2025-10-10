import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// Clase VistaHospital para la interfaz de usuario

public class VistaHospital
{
    private Scanner scanner;
    private GestorHospital gestor;

    public VistaHospital(GestorHospital gestor)
    {
        this.scanner = new Scanner(System.in);
        this.gestor = gestor;
    }

    public void iniciar()
    {
        mostrarMensaje("\n╔═══════════════════════════════════════════════════════════════╗");
        mostrarMensaje("║            SISTEMA INTEGRAL DE GESTIÓN HOSPITALARIA           ║");
        mostrarMensaje("╚═══════════════════════════════════════════════════════════════╝\n");
        
        mostrarMenuPrincipal();
        scanner.close();
    }
    
    private void mostrarMenuPrincipal()
    {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("MENÚ PRINCIPAL");
        System.out.println("═".repeat(60));
        System.out.println("1. Gestión de Personal Médico");
        System.out.println("2. Gestión de Citas");
        System.out.println("3. Reportes");
        System.out.println("4. Historial de Reagendamientos");
        System.out.println("5. Salir");
        System.out.println("═".repeat(60));
        System.out.print("Seleccione una opción: ");
        
        int opcion = leerOpcion(5);
        
        switch (opcion)
        {
            case 1: 
                gestionarPersonal();
                mostrarMenuPrincipal();
                break;
            case 2: 
                gestionarCitas();
                mostrarMenuPrincipal();
                break;
            case 3: 
                generarReportes();
                mostrarMenuPrincipal();
                break;
            case 4: 
                mostrarHistorialReagendamientos();
                mostrarMenuPrincipal();
                break;
            case 5: 
                mostrarExito("¡Gracias por usar el sistema! Hasta pronto.");
                break;
        }
    }

    private void gestionarPersonal()
    {
        mostrarMenuGestionPersonal();
        int opcion = leerOpcion(4);
        
        switch (opcion)
        {
            case 1: 
                menuAgregarPersonal();
                gestionarPersonal();
                break;
            case 2: 
                verInformacionTrabajador();
                gestionarPersonal();
                break;
            case 3: 
                listarPersonalPorDepartamento();
                gestionarPersonal();
                break;
            case 4: 
                return;
        }
    }
    
    private void mostrarMenuGestionPersonal()
    {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("GESTIÓN DE PERSONAL MÉDICO");
        System.out.println("─".repeat(60));
        System.out.println("1. Agregar nuevo trabajador");
        System.out.println("2. Ver información de trabajador");
        System.out.println("3. Listar personal por departamento");
        System.out.println("4. Volver al menú principal");
        System.out.println("─".repeat(60));
        System.out.print("Seleccione una opción: ");
    }

    private void menuAgregarPersonal()
    {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("AGREGAR NUEVO TRABAJADOR");
        System.out.println("─".repeat(60));
        System.out.println("Seleccione el tipo de trabajador:");
        System.out.println("1. Doctor");
        System.out.println("2. Cirujano");
        System.out.println("3. Enfermero");
        System.out.println("4. Radiólogo");
        System.out.println("5. Farmacéutico");
        System.out.println("6. Terapeuta");
        System.out.println("─".repeat(60));
        System.out.print("Opción: ");
        
        int tipo = leerOpcion(6);
        
        System.out.print("ID de empleado: ");
        String id = leerTexto("ID de empleado");
        
        System.out.print("Nombre completo: ");
        String nombre = leerTexto("Nombre completo");
        
        System.out.print("Departamento: ");
        String departamento = leerTexto("Departamento");
        
        int experiencia = leerEntero("Años de experiencia", 0, 50);
        double salarioBase = leerDouble("Salario base (Q)", 3000, 50000);
        
        TrabajadorMedico trabajador = null;
        
        switch (tipo)
        {
            case 1:
                System.out.print("Especialización: ");
                String especializacion = leerTexto("Especialización");
                int capacidad = leerEntero("Capacidad de pacientes por día", 5, 30);
                double tarifa = leerDouble("Tarifa por consulta (Q)", 100, 2000);
                trabajador = new Doctor(id, nombre, departamento, experiencia, salarioBase, especializacion, capacidad, tarifa);
                break;
                
            case 2:
                System.out.print("Tipos de operaciones (separados por coma): ");
                String ops = scanner.nextLine();
                List<String> operaciones = Arrays.asList(ops.split(","));
                int horas = leerEntero("Horas de cirugía disponibles", 10, 60);
                double bono = leerDouble("Bono por riesgo (Q)", 1000, 10000);
                double tarifaHora = leerDouble("Tarifa por hora (Q)", 200, 3000);
                trabajador = new Cirujano(id, nombre, departamento, experiencia, salarioBase, operaciones, horas, bono, tarifaHora);
                break;
                
            case 3:
                System.out.print("Tipo de turno (diurno/nocturno): ");
                String turno = leerTexto("Tipo de turno");
                System.out.print("Nivel de certificación: ");
                String certificacion = leerTexto("Nivel de certificación");
                trabajador = new Enfermero(id, nombre, departamento, experiencia, salarioBase, turno, certificacion);
                break;
                
            case 4:
                System.out.print("Equipos certificados (separados por coma): ");
                String eq = scanner.nextLine();
                List<String> equipos = Arrays.asList(eq.split(","));
                double tarifaEstudio = leerDouble("Tarifa por estudio (Q)", 200, 3000);
                trabajador = new Radiologo(id, nombre, departamento, experiencia, salarioBase, equipos, tarifaEstudio);
                break;
                
            case 5:
                int limite = leerEntero("Límite de prescripciones por día", 20, 200);
                System.out.print("¿Tiene licencia para sustancias controladas? (si/no): ");
                boolean licencia = scanner.nextLine().equalsIgnoreCase("si");
                trabajador = new Farmaceutico(id, nombre, departamento, experiencia, salarioBase, limite, licencia);
                break;
                
            case 6:
                System.out.print("Tipo de terapia especializada: ");
                String terapia = leerTexto("Tipo de terapia");
                int duracion = leerEntero("Duración promedio de sesión (minutos)", 30, 180);
                double tarifaSesion = leerDouble("Tarifa por sesión (Q)", 150, 1500);
                trabajador = new Terapeuta(id, nombre, departamento, experiencia, salarioBase, terapia, duracion, tarifaSesion);
                break;
        }
        
        if (trabajador != null)
        {
            gestor.agregarTrabajador(trabajador);
            mostrarExito("¡Trabajador agregado exitosamente!");
        } else {
            mostrarError("Error al agregar el trabajador.");
        }
    }

    private void verInformacionTrabajador()
    {
        System.out.print("\nIngrese el ID del empleado: ");
        String id = leerTexto("ID del empleado");
        
        TrabajadorMedico trabajador = gestor.obtenerTrabajador(id);
        if (trabajador != null)
        {
            System.out.println("\n" + trabajador.mostrarInformacion());
        } else {
            mostrarError("No se encontró ningún trabajador con ese ID.");
        }
        pausar();
    }

    private void listarPersonalPorDepartamento()
    {
        System.out.print("\nIngrese el nombre del departamento: ");
        String depto = leerTexto("Departamento");
        
        List<TrabajadorMedico> personal = gestor.listarPersonalPorDepartamento(depto);
        
        if (personal.isEmpty())
        {
            mostrarError("No hay personal en ese departamento.");
        } else {
            System.out.println("\n" + "═".repeat(60));
            System.out.println("PERSONAL DEL DEPARTAMENTO: " + depto.toUpperCase());
            System.out.println("═".repeat(60));
            for (TrabajadorMedico t : personal)
            {
                System.out.println(t.mostrarInformacion());
                System.out.println("-".repeat(60));
            }
            System.out.println("Total: " + personal.size() + " trabajadores");
        }
        pausar();
    }

    private void gestionarCitas()
    {
        mostrarMenuGestionCitas();
        int opcion = leerOpcion(7);
        
        switch (opcion)
        {
            case 1: 
                menuProgramarCita();
                gestionarCitas();
                break;
            case 2: 
                menuReagendarCita();
                gestionarCitas();
                break;
            case 3: 
                menuCancelarCita();
                gestionarCitas();
                break;
            case 4: 
                menuConfirmarCita();
                gestionarCitas();
                break;
            case 5: 
                verCitasPorTrabajador();
                gestionarCitas();
                break;
            case 6: 
                verCitasPorEstado();
                gestionarCitas();
                break;
            case 7: 
                return;
        }
    }
    
    private void mostrarMenuGestionCitas()
    {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("GESTIÓN DE CITAS");
        System.out.println("─".repeat(60));
        System.out.println("1. Programar nueva cita");
        System.out.println("2. Reagendar cita");
        System.out.println("3. Cancelar cita");
        System.out.println("4. Confirmar cita");
        System.out.println("5. Ver citas por trabajador");
        System.out.println("6. Ver citas por estado");
        System.out.println("7. Volver al menú principal");
        System.out.println("─".repeat(60));
        System.out.print("Seleccione una opción: ");
    }

    private void menuProgramarCita()
    {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("PROGRAMAR NUEVA CITA");
        System.out.println("─".repeat(60));
        
        System.out.print("Nombre del paciente: ");
        String paciente = leerTexto("Nombre del paciente");
        
        System.out.print("ID del trabajador médico: ");
        String idTrabajador = leerTexto("ID del trabajador");
        
        TrabajadorMedico trabajador = gestor.obtenerTrabajador(idTrabajador);
        if (trabajador == null) {
            mostrarError("No se encontró el trabajador con ese ID.");
            return;
        }
        
        LocalDateTime fechaHora = leerFechaHora();
        
        if (!gestor.verificarDisponibilidad(trabajador, fechaHora))
        {
            mostrarError("El trabajador no está disponible en ese horario.");
            System.out.print("¿Desea buscar otro horario? (si/no): ");
            if (scanner.nextLine().equalsIgnoreCase("si")) {
                menuProgramarCita();
            }
            return;
        }
        
        System.out.println("\nTipo de cita:");
        System.out.println("1. Consulta General");
        System.out.println("2. Cirugía");
        System.out.println("3. Terapia");
        System.out.println("4. Diagnóstico");
        System.out.println("5. Farmacia");
        System.out.println("6. Radiología");
        System.out.print("Seleccione: ");
        
        int tipoCitaNum = leerOpcion(6);
        TipoCita tipoCita = TipoCita.values()[tipoCitaNum - 1];
        
        CitaMedica cita = gestor.programarCita(paciente, trabajador, fechaHora, tipoCita);
        mostrarExito("¡Cita programada exitosamente!");
        System.out.println("\n" + cita.toString());
        pausar();
    }

    private void menuReagendarCita()
    {
        System.out.print("\nID de la cita a reagendar: ");
        String idCita = leerTexto("ID de la cita");
        
        LocalDateTime nuevaFecha = leerFechaHora();
        
        if (gestor.reagendarCita(idCita, nuevaFecha))
        {
            mostrarExito("¡Cita reagendada exitosamente!");
        } else {
            mostrarError("No se pudo reagendar la cita. Verifique la disponibilidad.");
        }
        pausar();
    }

    private void menuCancelarCita()
    {
        System.out.print("\nID de la cita a cancelar: ");
        String idCita = leerTexto("ID de la cita");
        
        System.out.print("Motivo de cancelación: ");
        String motivo = leerTexto("Motivo");
        
        if (gestor.cancelarCita(idCita, motivo))
        {
            mostrarExito("Cita cancelada exitosamente.");
        } else {
            mostrarError("No se encontró la cita con ese ID.");
        }
        pausar();
    }

    private void menuConfirmarCita()
    {
        System.out.print("\nID de la cita a confirmar: ");
        String idCita = leerTexto("ID de la cita");
        
        if (gestor.confirmarCita(idCita))
        {
            mostrarExito("¡Cita confirmada exitosamente!");
        } else {
            mostrarError("No se pudo confirmar la cita.");
        }
        pausar();
    }

    private void verCitasPorTrabajador()
    {
        System.out.print("\nID del trabajador: ");
        String idTrabajador = leerTexto("ID del trabajador");
        
        List<CitaMedica> citas = gestor.obtenerCitasPorTrabajador(idTrabajador);
        
        if (citas.isEmpty())
        {
            mostrarError("No hay citas para ese trabajador.");
        } else {
            System.out.println("\n" + "═".repeat(60));
            System.out.println("CITAS DEL TRABAJADOR: " + idTrabajador);
            System.out.println("═".repeat(60));
            for (CitaMedica c : citas)
            {
                System.out.println(c.toString());
                System.out.println("-".repeat(60));
            }
            System.out.println("Total: " + citas.size() + " citas");
        }
        pausar();
    }

    private void verCitasPorEstado()
    {
        System.out.println("\nSeleccione el estado:");
        System.out.println("1. PROGRAMADA");
        System.out.println("2. CONFIRMADA");
        System.out.println("3. EN_PROGRESO");
        System.out.println("4. COMPLETADA");
        System.out.println("5. CANCELADA");
        System.out.println("6. REAGENDADA");
        System.out.print("Opción: ");
        
        int opcion = leerOpcion(6);
        EstadoCita estado = EstadoCita.values()[opcion - 1];
        
        List<CitaMedica> citas = gestor.obtenerCitasPorEstado(estado);
        
        if (citas.isEmpty())
        {
            mostrarError("No hay citas con ese estado.");
        } else {
            System.out.println("\n" + "═".repeat(60));
            System.out.println("CITAS CON ESTADO: " + estado);
            System.out.println("═".repeat(60));
            for (CitaMedica c : citas)
            {
                System.out.println(c.toString());
                System.out.println("-".repeat(60));
            }
            System.out.println("Total: " + citas.size() + " citas");
        }
        pausar();
    }

    private void generarReportes()
    {
        mostrarMenuReportes();
        int opcion = leerOpcion(4);
        
        switch (opcion)
        {
            case 1: 
                mostrarReportePersonal();
                generarReportes();
                break;
            case 2: 
                mostrarReporteCitas();
                generarReportes();
                break;
            case 3: 
                mostrarReporteFinanciero();
                generarReportes();
                break;
            case 4: 
                return;
        }
    }
    
    private void mostrarMenuReportes()
    {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("REPORTES");
        System.out.println("─".repeat(60));
        System.out.println("1. Reporte de Personal");
        System.out.println("2. Reporte de Citas");
        System.out.println("3. Reporte Financiero");
        System.out.println("4. Volver al menú principal");
        System.out.println("─".repeat(60));
        System.out.print("Seleccione una opción: ");
    }

    private void mostrarReportePersonal()
    {
        System.out.println("\n" + gestor.generarReportePersonal());
        pausar();
    }

    private void mostrarReporteCitas()
    {
        System.out.println("\n" + gestor.generarReporteCitas());
        pausar();
    }

    private void mostrarReporteFinanciero()
    {
        System.out.println("\n" + gestor.generarReporteFinanciero());
        pausar();
    }

    private void mostrarHistorialReagendamientos()
    {
        List<CitaMedica> citasConHistorial = gestor.obtenerHistorialReagendamientos();
        
        if (citasConHistorial.isEmpty())
        {
            mostrarError("No hay citas con historial de cambios.");
            pausar();
            return;
        }
        
        System.out.println("\n" + "═".repeat(80));
        System.out.println("HISTORIAL DE REAGENDAMIENTOS");
        System.out.println("═".repeat(80));
        
        for (CitaMedica cita : citasConHistorial)
        {
            System.out.println("\n" + cita.toString());
            System.out.println("\nHistorial de cambios:");
            for (HistorialCambio cambio : cita.getHistorialCambios())
            {
                System.out.println("  " + cambio.toString());
            }
            System.out.println("-".repeat(80));
        }
        pausar();
    }

    private int leerEntero(String mensaje, int min, int max)
    {
        int valor = 0;
        boolean valido = false;
        
        do {
            try {
                System.out.print(mensaje + " (" + min + "-" + max + "): ");
                valor = Integer.parseInt(scanner.nextLine());
                if (valor >= min && valor <= max)
                {
                    valido = true;
                } else {
                    mostrarError("El valor debe estar entre " + min + " y " + max);
                }
            } 
                catch (NumberFormatException e)
            {
                mostrarError("Por favor ingrese un número válido.");
            }
        } while (!valido);
        
        return valor;
    }

    private double leerDouble(String mensaje, double min, double max)
    {
        double valor = 0;
        boolean valido = false;
        
        do {
            try {
                System.out.print(mensaje + " (" + min + "-" + max + "): ");
                valor = Double.parseDouble(scanner.nextLine());
                if (valor >= min && valor <= max)
                {
                    valido = true;
                } else {
                    mostrarError("El valor debe estar entre " + min + " y " + max);
                }
            } catch (NumberFormatException e)
            {
                mostrarError("Por favor ingrese un número válido.");
            }
        } while (!valido);
        
        return valor;
    }

    private String leerTexto(String mensaje)
    {
        String texto = scanner.nextLine().trim();
        if (texto.isEmpty())
        {
            mostrarError("El campo no puede estar vacío.");
            return leerTexto(mensaje);
        }
        return texto;
    }

    private LocalDateTime leerFechaHora()
    {
        LocalDateTime fechaHora = null;
        boolean valido = false;
        
        do {
            try {
                int dia = leerEntero("Día", 1, 31);
                int mes = leerEntero("Mes", 1, 12);
                int anio = leerEntero("Año", 2025, 2030);
                int hora = leerEntero("Hora", 0, 23);
                int minuto = leerEntero("Minuto", 0, 59);
                
                fechaHora = LocalDateTime.of(anio, mes, dia, hora, minuto);
                valido = true;
            } catch (Exception e)
            {
                mostrarError("Fecha u hora inválida. Intente nuevamente.");
            }
        } while (!valido);
        
        return fechaHora;
    }

    private int leerOpcion(int maxOpciones)
    {
        int opcion = 0;
        boolean valido = false;
        
        do {
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                if (opcion >= 1 && opcion <= maxOpciones)
                {
                    valido = true;
                } else {
                    mostrarError("Opción inválida. Seleccione entre 1 y " + maxOpciones);
                    System.out.print("Intente nuevamente: ");
                }
            } catch (NumberFormatException e)
            {
                mostrarError("Por favor ingrese un número válido.");
                System.out.print("Intente nuevamente: ");
            }
        } while (!valido);
        
        return opcion;
    }

    private void pausar()
    {
        System.out.println("\nPresione ENTER para continuar...");
        scanner.nextLine();
    }

    private void mostrarMensaje(String mensaje)
    {
        System.out.println(mensaje);
    }

    private void mostrarError(String mensaje)
    {
        System.out.println("ERROR: " + mensaje);
    }

    private void mostrarExito(String mensaje)
    {
        System.out.println("EXITO: " + mensaje);
    }
}