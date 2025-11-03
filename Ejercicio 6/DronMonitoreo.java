/// --- Dron de monitoreo por defecto ---

public class DronMonitoreo extends Dron implements iMedible, iAccionable, iRegistrable
{
    private String resolucionCamara;
    private String tipoSensor;
    
    public DronMonitoreo(String id, String nombre, String fabricante, double consumoElectrico, String ubicacion, double autonomiaVuelo, double alturaMaxima, String resolucionCamara, String tipoSensor)
    {
        super(id, nombre, fabricante, consumoElectrico, ubicacion, autonomiaVuelo, alturaMaxima);
        this.resolucionCamara = resolucionCamara;
        this.tipoSensor = tipoSensor;
    }
    
    public String getResolucionCamara()
    {
        return resolucionCamara;
    }

    public String getTipoSensor()
    {
        return tipoSensor;
    }
    
    @Override
    public String getTipo()
    {
        return "Dron de Monitoreo";
    }
    
    @Override
    public String obtenerMedicion()
    {
        return "Capturando imágenes " + tipoSensor + " en resolución " + resolucionCamara;
    }
    
    @Override
    public String ejecutarAccion()
    {
        return "Iniciando vuelo de reconocimiento con sensor " + tipoSensor;
    }
    
    @Override
    public String registrarEvento()
    {
        return "Evento registrado: Imágenes multiespectrales almacenadas";
    }
    
    @Override
    public String toString()
    {
        return super.toString() + 
               "Resolución de Cámara: " + resolucionCamara + "\n" +
               "Tipo de Sensor: " + tipoSensor + "\n" +
               "Capacidades: Medible, Accionable, Registrable\n";
    }
}
