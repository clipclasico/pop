/// --- Dron de riego por defecto ---

public class DronRiego extends Dron implements iAccionable, iRegistrable
{
    private double capacidadTanque;
    
    public DronRiego(String id, String nombre, String fabricante, double consumoElectrico, String ubicacion, double autonomiaVuelo, double alturaMaxima, double capacidadTanque)
    {
        super(id, nombre, fabricante, consumoElectrico, ubicacion, autonomiaVuelo, alturaMaxima);
        this.capacidadTanque = capacidadTanque;
    }
    
    public double getCapacidadTanque()
    {
        return capacidadTanque;
    }
    
    @Override
    public String getTipo()
    {
        return "Dron de Riego";
    }
    
    @Override
    public String ejecutarAccion() 
    {
        return "Rociando cultivo con " + capacidadTanque;
    }
    
    @Override
    public String registrarEvento()
    {
        return "Evento registrado: Riego completado en zona asignada";
    }
    
    @Override
    public String toString()
    {
        return super.toString() + 
               "Capacidad del Tanque: " + capacidadTanque + "\n" +
               "Capacidades: Accionable, Registrable\n";
    }
}