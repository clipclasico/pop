/// --- Estación meteorológica por defecto ---

public class EstacionMeteorologica extends Dispositivo implements iMedible, iRegistrable
{
    private double altitud;
    private int cantidadSensores;
    
    public EstacionMeteorologica(String id, String nombre, String fabricante, double consumoElectrico, String ubicacion, double altitud, int cantidadSensores)
    {
        super(id, nombre, fabricante, consumoElectrico, ubicacion);
        this.altitud = altitud;
        this.cantidadSensores = cantidadSensores;
    }
    
    public double getAltitud()
    {
        return altitud;
    }

    public int getCantidadSensores()
    {
        return cantidadSensores;
    }
    
    @Override
    public String getTipo()
    {
        return "Estación Meteorológica";
    }
    
    @Override
    public String obtenerMedicion()
    {
        return "Midiendo temperatura, presión, humedad, viento con " + cantidadSensores + " sensores";
    }
    
    @Override
    public String registrarEvento()
    {
        return "Evento registrado: Datos meteorológicos sincronizados a " + altitud;
    }
    
    @Override
    public String toString()
    {
        return super.toString() + 
               "Altitud: " + altitud + "\n" +
               "Cantidad de Sensores: " + cantidadSensores + "\n" +
               "Capacidades: Medible, Registrable\n";
    }
}
