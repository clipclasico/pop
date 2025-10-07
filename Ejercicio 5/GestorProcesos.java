import java.util.ArrayList;

public class GestorProcesos
{
    private ArrayList<Proceso> listaProcesos;
    private int contadorPID;
    
    public GestorProcesos()
    {
        this.listaProcesos = new ArrayList<>();
        this.contadorPID = 1000; // PIDs comienzan en 1000
    }

    public void agregarProceso(Proceso p)
    {
        if (p.getPid() == -1) {
            p.setPid(generarPID());
        }
        listaProcesos.add(p);
    }
    
    public void agregarProceso(Proceso p, int pid)
    {
        p.setPid(pid);
        listaProcesos.add(p);
        
        if (pid >= contadorPID) {
            contadorPID = pid + 1; // Actualizar contador si el PID es mayor
        }
    }
    
    public boolean eliminarProceso(int pid)
    {
        for (int i = 0; i < listaProcesos.size(); i++) {
            if (listaProcesos.get(i).getPid() == pid) {
                listaProcesos.remove(i);
                return true;
            }
        }
        return false;
    }

    public Proceso buscarProceso(int pid)
    {
        for (Proceso p : listaProcesos)
        {
            if (p.getPid() == pid) {
                return p;
            }
        }
        return null;
    }
    
    public ArrayList<Proceso> obtenerProcesos()
    {
        return listaProcesos;
    }
    

    public void ejecutarTodos()
    {
        for (Proceso p : listaProcesos)
        {
            p.ejecutar(); // Polimorfismo
        }
    }

    public boolean ejecutarProceso(int pid)
    {
        for (Proceso p : listaProcesos)
        {
            if (p.getPid() == pid)
            {
                p.ejecutar(); // Polimorfismo
                return true;
            }
        }
        return false;
    }
    
    public int obtenerCantidadProcesos()
    {
        return listaProcesos.size();
    }
    
    public void limpiarProcesos()
    {
        listaProcesos.clear();
    }

    public int generarPID()
    {
        return contadorPID++;
    }
}