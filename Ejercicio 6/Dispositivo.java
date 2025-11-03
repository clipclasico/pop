/// --- Dispositivo que implementa Comparable ---


abstract class Dispositivo implements Comparable<Dispositivo>
{
    protected String id;
    protected String nombre;
    protected String fabricante;
    protected double consumoElectrico;
    protected String ubicacion;

    public Dispositivo(String id, String nombre, String fabricante, double consumoElectrico, String ubicacion)
    {
        this.id = id;
        this.nombre = nombre;
        this.fabricante = fabricante;
        this.consumoElectrico = consumoElectrico;
        this.ubicacion = ubicacion;
    }

    public String getId()
    {
        return id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getFabricante()
    {
        return fabricante;
    }

    public double getConsumoElectrico()
    {
        return consumoElectrico;
    }

    public String getUbicacion()
    {
        return ubicacion;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setFabricante(String fabricante)
    {
        this.fabricante = fabricante;
    }

    public void setConsumoElectrico(double consumoElectrico)
    {
        this.consumoElectrico = consumoElectrico;
    }

    public void setUbicacion(String ubicacion)
    {
        this.ubicacion = ubicacion;
    }

    public abstract String getTipo();

    @Override
    public int compareTo(Dispositivo otro)
    {
        return Double.compare(this.consumoElectrico, otro.consumoElectrico);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("Tipo: ").append(getTipo()).append("\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Fabricante: ").append(fabricante).append("\n");
        sb.append("Consumo Eléctrico: ").append(consumoElectrico).append("\n");
        sb.append("Ubicación: ").append(ubicacion).append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Dispositivo that = (Dispositivo) obj;
        return id.equals(that.id);
    }
}