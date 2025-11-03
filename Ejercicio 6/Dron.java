/// --- Dron heredado de dispositivo ---

abstract class Dron extends Dispositivo {
    protected double autonomiaVuelo;
    protected double alturaMaxima;
    
    public Dron(String id, String nombre, String fabricante, double consumoElectrico, String ubicacion, double autonomiaVuelo, double alturaMaxima)
    {
        super(id, nombre, fabricante, consumoElectrico, ubicacion);
        this.autonomiaVuelo = autonomiaVuelo;
        this.alturaMaxima = alturaMaxima;
    }
    
    public double getAutonomiaVuelo()
    {
        return autonomiaVuelo;
    }

    public double getAlturaMaxima()
    {
        return alturaMaxima;
    }
    
    @Override
    public String toString()
    {
        return super.toString() + 
               "Autonomía de Vuelo: " + autonomiaVuelo + "\n" +
               "Altura Máxima: " + alturaMaxima + "\n";
    }
}