/// --- Sensor de humedad por defecto ---

public class SensorHumedad extends Dispositivo implements iMedible, iRegistrable
{
    private double rangoMin;
    private double rangoMax;
    
    public SensorHumedad(String id, String nombre, String fabricante, double consumoElectrico, String ubicacion, double rangoMin, double rangoMax)
    {
        super(id, nombre, fabricante, consumoElectrico, ubicacion);
        this.rangoMin = rangoMin;
        this.rangoMax = rangoMax;
    }
    
    public double getRangoMin()
    {
        return rangoMin;
    }

    public double getRangoMax()
    {
        return rangoMax;
    }
    
    @Override
    public String getTipo()
    {
        return "Sensor de Humedad";
    }
    
    @Override
    public String obtenerMedicion()
    {
        return "Midiendo humedad relativa (rango: " + rangoMin + " - " + rangoMax + ")";
    }
    
    @Override
    public String registrarEvento()
    {
        return "Evento registrado: Lectura de humedad almacenada";
    }
    
    @Override
    public String toString()
    {
        return super.toString() + 
               "Rango Mínimo: " + rangoMin + "%\n" +
               "Rango Máximo: " + rangoMax + "%\n" +
               "Capacidades: Medible, Registrable\n";
    }
}
