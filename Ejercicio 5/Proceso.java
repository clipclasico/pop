// Giovanni Orozco Calvinisti - 251162

// Clase abstracta Proceso
// Define la estructura base para todos los tipos de procesos

public abstract class Proceso
{
    protected int pid;
    protected String nombre;

    public Proceso(int pid, String nombre)
    {
        this.pid = pid;
        this.nombre = nombre;
    }
    
    public Proceso(String nombre)
    {
        this.pid = -1; // PID no asignado
        this.nombre = nombre;
    }
    
    // Getters y Setters
    public int getPid()
    {
        return pid;
    }
    
    public void setPid(int pid)
    {
        this.pid = pid;
    }
    
    public String getNombre()
    {
        return nombre;
    }
    
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    
    public abstract void ejecutar();

    @Override
    public String toString()
    {
        return "PID: " + pid + " | Nombre: " + nombre;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Proceso)) return false;
        Proceso otro = (Proceso) obj;
        return this.pid == otro.pid;
    }
}