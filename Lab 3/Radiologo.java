import java.util.List;

// Clase Radiologo que extiende TrabajadorMedico

public class Radiologo extends TrabajadorMedico
{
    private List<String> equiposCertificados;
    private double tarifaPorEstudio;
    private int estudiosRealizados;

    public Radiologo(String idEmpleado, String nombreCompleto, String departamento, int aniosExperiencia, double salarioBase, List<String> equiposCertificados, double tarifaPorEstudio) {
        super(idEmpleado, nombreCompleto, departamento, aniosExperiencia, salarioBase);
        this.equiposCertificados = equiposCertificados;
        this.tarifaPorEstudio = tarifaPorEstudio;
        this.estudiosRealizados = 0;
    }

    @Override
    public double calcularSalarioTotal()
    {
        return getSalarioBase() + (estudiosRealizados * tarifaPorEstudio);
    }

    @Override
    public String mostrarInformacion()
    {
        return String.format("%s\nTipo: Radiólogo | Equipos certificados: %s\n" + "Tarifa/estudio: Q%.2f | Estudios realizados: %d | Salario total: Q%.2f",
        toString(), String.join(", ", equiposCertificados), tarifaPorEstudio, 
        estudiosRealizados, calcularSalarioTotal());
    }

    public void registrarEstudio()
    {
        estudiosRealizados++;
    }

    public boolean puedeManejarEquipo(String equipo)
    {
        return equiposCertificados.contains(equipo);
    }

    public List<String> getEquiposCertificados()
    {
        return equiposCertificados;
    }
}
