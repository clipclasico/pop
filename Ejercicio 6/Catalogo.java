/// --- Modelo ---

import java.util.ArrayList;
import java.util.Collections;

public class Catalogo
{
    private ArrayList<Dispositivo> dispositivos;
    
    public Catalogo()
    {
        this.dispositivos = new ArrayList<>();
    }
    
    public void agregarDispositivo(Dispositivo dispositivo)
    {
        dispositivos.add(dispositivo);
    }
    
    public ArrayList<Dispositivo> getDispositivos()
    {
        return dispositivos;
    }
    
    public Dispositivo buscarPorId(String id)
    {
        for (Dispositivo d : dispositivos)
        {
            if (d.getId().equalsIgnoreCase(id))
            {
                return d;
            }
        }
        return null;
    }
    
    public ArrayList<Dispositivo> buscarPorNombre(String nombre)
    {
        ArrayList<Dispositivo> resultados = new ArrayList<>();
        for (Dispositivo d : dispositivos)
        {
            if (d.getNombre().toLowerCase().contains(nombre.toLowerCase()))
            {
                resultados.add(d);
            }
        }
        return resultados;
    }

    public void ordenarPorConsumo()
    {
        Collections.sort(dispositivos);
    }
    
    public void init()
    {
        agregarDispositivo(new SensorSuelo(
            "SS-001", "Tierra 10mil", "Yobis SA", 0.5, 
            "Parcela A", 1.5, "Arcilloso"
        ));
        
        agregarDispositivo(new SensorSuelo(
            "SS-002", "GroundSense Pro", "AgriSensors", 0.6,
            "Parcela B", 2.0, "Franco"
        ));
        
        agregarDispositivo(new SensorHumedad(
            "SH-001", "HumidityWatch", "ProCasa", 0.3,
            "Invernadero 1", 20.0, 80.0
        ));
        
        agregarDispositivo(new SensorHumedad(
            "SH-002", "TermaFast", "Lo mejor para el mejor", 0.4,
            "Campo Central", 15.0, 90.0
        ));
        
        agregarDispositivo(new EstacionMeteorologica(
            "EM-001", "ClimaS", "M.T", 2.5,
            "Torre Central", 1500.0, 8
        ));
        
        agregarDispositivo(new EstacionMeteorologica(
            "EM-002", "Climate", "SkyMonitor", 3.0,
            "Norte", 1450.0, 12
        ));
        
        agregarDispositivo(new DronRiego(
            "DR-001", "AquaDrone X1", "Granja Solar", 5.0,
            "Hangar Principal", 45.0, 150.0, 50.0
        ));
        
        agregarDispositivo(new DronRiego(
            "DR-002", "DronTT", "AgroAir", 4.5,
            "Base Sur", 60.0, 200.0, 75.0
        ));
        
        agregarDispositivo(new DronMonitoreo(
            "DM-001", "VisionDrone Elite", "TechSky", 6.0,
            "Hangar Principal", 90.0, 300.0, "4K", "Multiespectral"
        ));
        
        agregarDispositivo(new DronMonitoreo(
            "DM-002", "ScanMaster Pro", "AgroVision", 5.5,
            "Base Este", 75.0, 250.0, "HD", "Infrarrojo"
        ));
        
        agregarDispositivo(new ValvulaRiego(
            "VR-001", "Control 3000", "HydroTech", 1.2,
            "Sistema Central", 150.0, 45.0
        ));

        agregarDispositivo(new SistemaRociado(
            "SR-001", "SprayMaster Ultra", "CropCare", 3.5,
            "Parcela C", 5.0, "Aspersión fina"
        ));
    }
}