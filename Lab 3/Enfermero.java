// Clase Enfermero que extiende TrabajadorMedico

class Enfermero extends TrabajadorMedico
{
    private String tipoTurno;
    private String nivelCertificacion;
    private double bonificacionNocturna;

    public Enfermero(String idEmpleado, String nombreCompleto, String departamento, int aniosExperiencia, double salarioBase, String tipoTurno, String nivelCertificacion)
    {
        super(idEmpleado, nombreCompleto, departamento, aniosExperiencia, salarioBase);
        this.tipoTurno = tipoTurno;
        this.nivelCertificacion = nivelCertificacion;
        this.bonificacionNocturna = tipoTurno.equalsIgnoreCase("nocturno") ? 1500.0 : 0.0;
    }

    @Override
    public double calcularSalarioTotal()
    {
        return getSalarioBase() + bonificacionNocturna;
    }

    @Override
    public String mostrarInformacion()
    {
        return String.format("%s\nTipo: Enfermero | Turno: %s | Certificación: %s\n" + "Bonificación nocturna: Q%.2f | Salario total: Q%.2f",
        toString(), tipoTurno, nivelCertificacion, bonificacionNocturna, calcularSalarioTotal());
    }

    public boolean esTurnoNocturno()
    {
        return tipoTurno.equalsIgnoreCase("nocturno");
    }

    public String getTipoTurno()
    {
        return tipoTurno;
    }

    public String getNivelCertificacion()
    {
        return nivelCertificacion;
    }
}