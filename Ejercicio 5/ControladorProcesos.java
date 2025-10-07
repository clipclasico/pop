public class ControladorProcesos
{
    private GestorProcesos modelo;
    private VistaProcesos vista;

    public ControladorProcesos(GestorProcesos modelo, VistaProcesos vista)
    {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar()
    {
        vista.mostrarMensaje("Sistema de gestión de procesos iniciado");
        procesarMenu();
    }

    private void procesarMenu()
    {
        vista.mostrarMenu();
        int opcion = vista.leerOpcion();
        
        switch (opcion)
        {
            case 1:
                agregarProcesoCPU();
                procesarMenu();
                break;
            case 2:
                agregarProcesoIO();
                procesarMenu();
                break;
            case 3:
                agregarDaemon();
                procesarMenu();
                break;
            case 4:
                agregarProcesoTiempoReal();
                procesarMenu();
                break;
            case 5:
                agregarProcesoEnRed();
                procesarMenu();
                break;
            case 6:
                listarProcesos();
                procesarMenu();
                break;
            case 7:
                ejecutarProceso();
                procesarMenu();
                break;
            case 8:
                ejecutarTodosProcesos();
                procesarMenu();
                break;
            case 9:
                buscarProcesoPorPID();
                procesarMenu();
                break;
            case 10:
                eliminarProceso();
                procesarMenu();
                break;
            case 11:
                limpiarSistema();
                procesarMenu();
                break;
            case 0:
                vista.cerrar();
                break;
            default:
                vista.mostrarError("Opción no válida");
                procesarMenu();
        }
    }

// Métodos para cada opción del menú

    private void agregarProcesoCPU()
    {
        String nombre = vista.leerTexto("Nombre del proceso: ");
        String tipoCalculo = vista.leerTexto("Tipo de cálculo (compilación/renderizado/codificación): ");
        
        ProcesoCPU proceso = new ProcesoCPU(nombre, tipoCalculo);
        modelo.agregarProceso(proceso);
        
        vista.mostrarMensaje("Proceso CPU agregado con PID: " + proceso.getPid());
    }
    
    private void agregarProcesoIO()
    {
        String nombre = vista.leerTexto("Nombre del proceso: ");
        String dispositivo = vista.leerTexto("Dispositivo (disco/teclado/impresora/red): ");
        
        ProcesoIO proceso = new ProcesoIO(nombre, dispositivo);
        modelo.agregarProceso(proceso);
        
        vista.mostrarMensaje("Proceso I/O agregado con PID: " + proceso.getPid());
    }

    private void agregarDaemon()
    {
        String nombre = vista.leerTexto("Nombre del daemon: ");
        String servicio = vista.leerTexto("Servicio (logging/monitoreo/backup/web): ");
        
        Daemon proceso = new Daemon(nombre, servicio);
        modelo.agregarProceso(proceso);
        
        vista.mostrarMensaje("Daemon agregado con PID: " + proceso.getPid());
    }

    private void agregarProcesoTiempoReal()
    {
        String nombre = vista.leerTexto("Nombre del proceso: ");
        int prioridad = vista.leerEntero("Prioridad (1-10, mayor = más prioritario): ");
        int deadline = vista.leerEntero("Deadline en milisegundos: ");
        
        if (prioridad < 1 || prioridad > 10)
        {
            vista.mostrarError("Prioridad debe estar entre 1 y 10");
            return;
        }
        
        ProcesoTiempoReal proceso = new ProcesoTiempoReal(nombre, prioridad, deadline);
        modelo.agregarProceso(proceso);
        
        vista.mostrarMensaje("Proceso Tiempo Real agregado con PID: " + proceso.getPid());
    }

    private void agregarProcesoEnRed()
    {
        String nombre = vista.leerTexto("Nombre del proceso: ");
        String protocolo = vista.leerTexto("Protocolo (TCP/UDP/HTTP/FTP): ");
        String destino = vista.leerTexto("Dirección destino (IP o URL): ");
        
        ProcesoEnRed proceso = new ProcesoEnRed(nombre, protocolo, destino);
        modelo.agregarProceso(proceso);
        
        vista.mostrarMensaje("Proceso en Red agregado con PID: " + proceso.getPid());
    }
    
    private void listarProcesos()
    {
        vista.mostrarProcesos(modelo.obtenerProcesos());
    }
    
    private void ejecutarProceso()
    {
        int pid = vista.leerEntero("Ingrese el PID del proceso a ejecutar: ");
        
        if (modelo.ejecutarProceso(pid))
        {
            vista.mostrarMensaje("Proceso " + pid + " ejecutado exitosamente");
            Proceso p = modelo.buscarProceso(pid);
            vista.mostrarProceso(p);
        } else {
            vista.mostrarError("No se encontró el proceso con PID: " + pid);
        }
    }
    
    private void ejecutarTodosProcesos()
    {
        if (modelo.obtenerCantidadProcesos() == 0)
        {
            vista.mostrarError("No hay procesos para ejecutar");
            return;
        }
        
        modelo.ejecutarTodos();
        vista.mostrarMensaje("Todos los procesos han sido ejecutados");
        vista.mostrarProcesos(modelo.obtenerProcesos());
    }
    
    private void buscarProcesoPorPID()
    {
        int pid = vista.leerEntero("Ingrese el PID a buscar: ");
        Proceso p = modelo.buscarProceso(pid);
        vista.mostrarProceso(p);
    }

    private void eliminarProceso()
    {
        int pid = vista.leerEntero("Ingrese el PID del proceso a eliminar: ");
        
        if (vista.confirmar("¿Está seguro de eliminar el proceso " + pid + "?"))
        {
            if (modelo.eliminarProceso(pid))
            {
                vista.mostrarMensaje("Proceso eliminado exitosamente");
            } else
            {
                vista.mostrarError("No se encontró el proceso con PID: " + pid);
            }
        }
    }

    private void limpiarSistema()
    {
        if (vista.confirmar("¿Está seguro de eliminar TODOS los procesos?")) {
            modelo.limpiarProcesos();
            vista.mostrarMensaje("Sistema limpiado. Todos los procesos han sido eliminados");
        }
    }
}