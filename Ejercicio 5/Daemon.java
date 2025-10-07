// Daemon.java
// Clase que representa un proceso daemon (servicio en segundo plano)

public class Daemon extends Proceso
{
    private String servicio;
    private boolean activo;
    private int ciclosEjecutados;
    
    public Daemon(int pid, String nombre, String servicio)
    {
        super(pid, nombre);
        this.servicio = servicio;
        this.activo = true;
        this.ciclosEjecutados = 0;
    }

    public Daemon(String nombre, String servicio)
    {
        super(nombre);
        this.servicio = servicio;
        this.activo = true;
        this.ciclosEjecutados = 0;
    }
    
    // Getters y Setters
    public String getServicio()
    {
        return servicio;
    }
    
    public void setServicio(String servicio)
    {
        this.servicio = servicio;
    }
    
    public boolean isActivo()
    {
        return activo;
    }
    
    public int getCiclosEjecutados()
    {
        return ciclosEjecutados;
    }
    
    public void activar()
    {
        this.activo = true;
    }
    
    public void desactivar()
    {
        this.activo = false;
    }
    
    @Override
    public void ejecutar()
    {
        if (activo) {
            ciclosEjecutados++; // Simula trabajo continuo del servicio
        }
    }
    
    @Override
    public String toString()
    {
        String estado = activo ? "ACTIVO" : "INACTIVO";
        return super.toString() + " | Tipo: DAEMON | Servicio: " + servicio + " | Estado: " + estado + " | Ciclos: " + ciclosEjecutados;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof Daemon)) return false;
        Daemon otro = (Daemon) obj;
        return this.servicio.equals(otro.servicio);
    }
}