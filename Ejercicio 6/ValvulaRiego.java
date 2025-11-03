/// --- Valvula de riego por defecto ---

public class ValvulaRiego extends Dispositivo implements iAccionable, iRegistrable
{
    private double caudal;
    private double presion;
    
    public ValvulaRiego(String id, String nombre, String fabricante, double consumoElectrico, String ubicacion, double caudal, double presion)
    {
        super(id, nombre, fabricante, consumoElectrico, ubicacion);
        this.caudal = caudal;
        this.presion = presion;
    }
    
    public double getCaudal()
    {
        return caudal;
    }

    public double getPresion()
    {
        return presion;
    }
    
    @Override
    public String getTipo()
    {
        return "Válvula de Riego";
    }
    
    @Override
    public String ejecutarAccion()
    {
        return "Abriendo válvula - Caudal: " + caudal + " a " + presion;
    }
    
    @Override
    public String registrarEvento()
    {
        return "Evento registrado: Válvula activada exitosamente";
    }
    
    @Override
    public String toString()
    {
        return super.toString() + 
               "Caudal: " + caudal + "\n" +
               "Presión: " + presion + "\n" +
               "Capacidades: Accionable, Registrable\n";
    }
}