// Clase Terapeuta que extiende TrabajadorMedico

public class Terapeuta extends TrabajadorMedico
{
    private String tipoTerapia;
    private int duracionPromedioSesion;
    private int sesionesRealizadas;
    private double tarifaPorSesion;

    public Terapeuta(String idEmpleado, String nombreCompleto, String departamento, int aniosExperiencia, double salarioBase, String tipoTerapia, int duracionPromedioSesion, double tarifaPorSesion)
    {
        super(idEmpleado, nombreCompleto, departamento, aniosExperiencia, salarioBase);
        this.tipoTerapia = tipoTerapia;
        this.duracionPromedioSesion = duracionPromedioSesion;
        this.tarifaPorSesion = tarifaPorSesion;
        this.sesionesRealizadas = 0;
    }

    @Override
    public double calcularSalarioTotal()
    {
        return getSalarioBase() + (sesionesRealizadas * tarifaPorSesion);
    }

    @Override
    public String mostrarInformacion()
    {
        return String.format("%s\nTipo: Terapeuta | Especialidad: %s | Duración sesión: %d min\n" + "Tarifa/sesión: Q%.2f | Sesiones realizadas: %d | Salario total: Q%.2f",
            toString(), tipoTerapia, duracionPromedioSesion, tarifaPorSesion, 
            sesionesRealizadas, calcularSalarioTotal());
    }

    public void registrarSesion()
    {
        sesionesRealizadas++;
    }

    public String getTipoTerapia()
    {
        return tipoTerapia;
    }

    public int getDuracionPromedioSesion()
    {
        return duracionPromedioSesion;
    }
}