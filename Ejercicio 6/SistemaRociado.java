/// --- Sistema de rociado por defecto ---

public class SistemaRociado extends Dispositivo implements iAccionable, iMedible, iRegistrable
{
    private double areaCobertura;
    private String tipoRociado;
    
    public SistemaRociado(String id, String nombre, String fabricante, double consumoElectrico, String ubicacion, double areaCobertura, String tipoRociado)
    {
        super(id, nombre, fabricante, consumoElectrico, ubicacion);
        this.areaCobertura = areaCobertura;
        this.tipoRociado = tipoRociado;
    }
    
    public double getAreaCobertura()
    {
        return areaCobertura;
    }

    public String getTipoRociado()
    {
        return tipoRociado;
    }
    
    @Override
    public String getTipo()
    {
        return "Sistema de Rociado";
    }
    
    @Override
    public String ejecutarAccion()
    {
        return "Rociando parcela tipo " + tipoRociado + " sobre " + areaCobertura;
    }
    
    @Override
    public String obtenerMedicion()
    {
        return "Midiendo cobertura y distribución del rociado";
    }
    
    @Override
    public String registrarEvento()
    {
        return "Evento registrado: Rociado completado en área asignada";
    }
    
    @Override
    public String toString()
    {
        return super.toString() + 
               "Área de Cobertura: " + areaCobertura + "\n" +
               "Tipo de Rociado: " + tipoRociado + "\n" +
               "Capacidades: Accionable, Medible, Registrable\n";
    }
}
