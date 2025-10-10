import java.util.List;

// Clase Cirujano que extiende TrabajadorMedico

public class Cirujano extends TrabajadorMedico
{
    private List<String> tiposOperaciones;
    private int horasCirugiaDisponibles;
    private double bonoPorRiesgo;
    private int horasCirugiaRealizadas;
    private double tarifaPorHora;

    public Cirujano(String idEmpleado, String nombreCompleto, String departamento, int aniosExperiencia, double salarioBase, List<String> tiposOperaciones, int horasCirugiaDisponibles, double bonoPorRiesgo, double tarifaPorHora)
    {
        super(idEmpleado, nombreCompleto, departamento, aniosExperiencia, salarioBase);
        this.tiposOperaciones = tiposOperaciones;
        this.horasCirugiaDisponibles = horasCirugiaDisponibles;
        this.bonoPorRiesgo = bonoPorRiesgo;
        this.tarifaPorHora = tarifaPorHora;
        this.horasCirugiaRealizadas = 0;
    }

    @Override
    public double calcularSalarioTotal()
    {
        return getSalarioBase() + (horasCirugiaRealizadas * tarifaPorHora) + bonoPorRiesgo;
    }

    @Override
    public String mostrarInformacion()
    {
        return String.format("%s\nTipo: Cirujano | Operaciones: %s\n" +"Horas disponibles: %d | Horas realizadas: %d | Bono riesgo: Q%.2f\n" + "Tarifa/hora: Q%.2f | Salario total: Q%.2f",
        toString(), String.join(", ", tiposOperaciones), horasCirugiaDisponibles, 
        horasCirugiaRealizadas, bonoPorRiesgo, tarifaPorHora, calcularSalarioTotal());
    }

    public void registrarCirugia(int horas)
    {
        horasCirugiaRealizadas += horas;
    }

    public boolean puedeRealizarOperacion(String tipo)
    {
        return tiposOperaciones.contains(tipo);
    }

    public List<String> getTiposOperaciones()
    {
        return tiposOperaciones;
    }

    public int getHorasCirugiaDisponibles()
    { 
        return horasCirugiaDisponibles;
    }
}