public class ProcesoEnRed extends Proceso
{
    private String protocolo;
    private String direccionDestino;
    private int paquetesEnviados;
    
    public ProcesoEnRed(int pid, String nombre, String protocolo, String direccionDestino)
    {
        super(pid, nombre);
        this.protocolo = protocolo;
        this.direccionDestino = direccionDestino;
        this.paquetesEnviados = 0;
    }
    
    public ProcesoEnRed(String nombre, String protocolo, String direccionDestino)
    {
        super(nombre);
        this.protocolo = protocolo;
        this.direccionDestino = direccionDestino;
        this.paquetesEnviados = 0;
    }
    
    // Getters y Setters
    public String getProtocolo()
    {
        return protocolo;
    }
    
    public void setProtocolo(String protocolo)
    {
        this.protocolo = protocolo;
    }
    
    public String getDireccionDestino()
    {
        return direccionDestino;
    }
    
    public void setDireccionDestino(String direccionDestino)
    {
        this.direccionDestino = direccionDestino;
    }
    
    public int getPaquetesEnviados()
    {
        return paquetesEnviados;
    }
    
    @Override
    public void ejecutar()
    {
        int paquetes = (int)(Math.random() * 50) + 10;
        paquetesEnviados += paquetes; // Simula envío de paquetes por la red
    }
    
    @Override
    public String toString()
    {
        return super.toString() + " | Tipo: RED | Protocolo: " + protocolo + " | Destino: " + direccionDestino + " | Paquetes: " + paquetesEnviados;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof ProcesoEnRed)) return false;
        ProcesoEnRed otro = (ProcesoEnRed) obj;
        return this.protocolo.equals(otro.protocolo) && 
               this.direccionDestino.equals(otro.direccionDestino);
    }
}