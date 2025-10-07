// ProcesoIO.java
// Clase que representa un proceso de entrada/salida (I/O)

public class ProcesoIO extends Proceso
{
    private String dispositivo;
    private int tiempoEspera;
    private boolean bloqueado;
    
    public ProcesoIO(int pid, String nombre, String dispositivo)
    {
        super(pid, nombre);
        this.dispositivo = dispositivo;
        this.tiempoEspera = 0;
        this.bloqueado = false;
    }
    
    public ProcesoIO(String nombre, String dispositivo)
    {
        super(nombre);
        this.dispositivo = dispositivo;
        this.tiempoEspera = 0;
        this.bloqueado = false;
    }
    
    // Getters y Setters
    public String getDispositivo()
    {
        return dispositivo;
    }
    
    public void setDispositivo(String dispositivo)
    {
        this.dispositivo = dispositivo;
    }
    
    public int getTiempoEspera()
    {
        return tiempoEspera;
    }
    
    public boolean isBloqueado()
    {
        return bloqueado;
    }
    
    @Override
    public void ejecutar() // Simula tiempo de espera por I/O
    {
        bloqueado = Math.random() < 0.3; // 30% de probabilidad de bloqueo

        if (bloqueado)
        {
            tiempoEspera += (int)(Math.random() * 500) + 100;
        } else {
            tiempoEspera = 0;
        }
    }
    
    @Override
    public String toString()
    {
        String estado = bloqueado ? "BLOQUEADO" : "ACTIVO";
        return super.toString() + " | Tipo: I/O | Dispositivo: " + dispositivo + " | Estado: " + estado + " | Espera: " + tiempoEspera + "ms";
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof ProcesoIO)) return false;
        ProcesoIO otro = (ProcesoIO) obj;
        return this.dispositivo.equals(otro.dispositivo);
    }
}