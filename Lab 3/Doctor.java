// Clase Doctor que extiende TrabajadorMedico

public class Doctor extends TrabajadorMedico
{
    private String especializacion;
    private int capacidadPacientesDia;
    private double tarifaConsulta;
    private int numeroConsultasRealizadas;

    public Doctor(String idEmpleado, String nombreCompleto, String departamento, int aniosExperiencia, double salarioBase, String especializacion, int capacidadPacientesDia, double tarifaConsulta)
    {
        super(idEmpleado, nombreCompleto, departamento, aniosExperiencia, salarioBase);
        this.especializacion = especializacion;
        this.capacidadPacientesDia = capacidadPacientesDia;
        this.tarifaConsulta = tarifaConsulta;
        this.numeroConsultasRealizadas = 0;
    }

    @Override
    public double calcularSalarioTotal()
    {
        return getSalarioBase() + (numeroConsultasRealizadas * tarifaConsulta);
    }

    @Override
    public String mostrarInformacion()
    {
        return String.format("%s\nTipo: Doctor | Especialización: %s | Capacidad: %d pacientes/día\n" + "Tarifa consulta: Q%.2f | Consultas realizadas: %d | Salario total: Q%.2f",
        toString(), especializacion, capacidadPacientesDia, tarifaConsulta, 
        numeroConsultasRealizadas, calcularSalarioTotal());
    }

    public void registrarConsulta()
    {
        numeroConsultasRealizadas++;
    }

    public String getEspecializacion()
    {
        return especializacion;
    }

    public int getCapacidadPacientesDia()
    {
        return capacidadPacientesDia;
    }

    public int getNumeroConsultasRealizadas()
    {
        return numeroConsultasRealizadas;
    }
}