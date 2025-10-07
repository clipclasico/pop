// ProcesoTiempoReal.java
// Clase que representa un proceso de tiempo real

public class ProcesoTiempoReal extends Proceso
{
    private int prioridad;
    private int deadline;
    private boolean critico;
    
    public ProcesoTiempoReal(int pid, String nombre, int prioridad, int deadline)
    {
        super(pid, nombre);
        this.prioridad = prioridad;
        this.deadline = deadline;
        this.critico = prioridad >= 8;
    }

    public ProcesoTiempoReal(String nombre, int prioridad, int deadline)
    {
        super(nombre);
        this.prioridad = prioridad;
        this.deadline = deadline;
        this.critico = prioridad >= 8;
    }
    
    // Getters y Setters
    public int getPrioridad()
    {
        return prioridad;
    }
    
    public void setPrioridad(int prioridad)
    {
        this.prioridad = prioridad;
        this.critico = prioridad >= 8;
    }
    
    public int getDeadline()
    {
        return deadline;
    }
    
    public boolean isCritico()
    {
        return critico;
    }
    
    public boolean verificarDeadline() {
        
        return Math.random() < 0.85; // Simula verificación de cumplimiento de deadline
    }
    
    @Override
    public void ejecutar() {
        boolean cumplioDeadline = verificarDeadline();

        if (critico && !cumplioDeadline)
        {
            deadline = (int)(deadline * 1.5); // Aumenta el deadline en un 50% si no se cumple
        }
    }
    
    @Override
    public String toString()
    {
        String nivel = critico ? "CRÍTICO" : "NORMAL";
        return super.toString() + " | Tipo: TIEMPO REAL | Prioridad: " + prioridad + " | Deadline: " + deadline + "ms | Nivel: " + nivel;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof ProcesoTiempoReal)) return false;
        ProcesoTiempoReal otro = (ProcesoTiempoReal) obj;
        return this.prioridad == otro.prioridad && this.deadline == otro.deadline;
    }
}