/// --- Sensor de suelo por defecto ---

public class SensorSuelo extends Dispositivo implements iMedible, iRegistrable
{
    private double profundidad;
    private String tipoSuelo;
    
    public SensorSuelo(String id, String nombre, String fabricante, double consumoElectrico, String ubicacion, double profundidad, String tipoSuelo)
    {
        super(id, nombre, fabricante, consumoElectrico, ubicacion);
        this.profundidad = profundidad;
        this.tipoSuelo = tipoSuelo;
    }
    
    public double getProfundidad()
    {
        return profundidad;
    }

    public String getTipoSuelo()
    {
        return tipoSuelo;
    }
    
    @Override
    public String getTipo()
    {
        return "Sensor de Suelo";
    }
    
    @Override
    public String obtenerMedicion()
    {
        return "Midiendo pH, nutrientes y temperatura a " + profundidad + "m de profundidad";
    }
    
    @Override
    public String registrarEvento()
    {
        return "Evento registrado: Medición de suelo " + tipoSuelo + " completada";
    }
    
    @Override
    public String toString()
    {
        return super.toString() + 
               "Profundidad: " + profundidad + "\n" +
               "Tipo de Suelo: " + tipoSuelo + "\n" +
               "Capacidades: Medible, Registrable\n";
    }
}