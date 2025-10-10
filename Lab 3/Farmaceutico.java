// Clase Farmaceutico que extiende TrabajadorMedico

public class Farmaceutico extends TrabajadorMedico
{
    private int limitePrescripcionesDia;
    private boolean licenciaSustanciasControladas;
    private int prescripcionesEmitidas;
    private double comisionPorPrescripcion;

    public Farmaceutico(String idEmpleado, String nombreCompleto, String departamento, int aniosExperiencia, double salarioBase, int limitePrescripcionesDia, boolean licenciaSustanciasControladas)
    {
        super(idEmpleado, nombreCompleto, departamento, aniosExperiencia, salarioBase);
        this.limitePrescripcionesDia = limitePrescripcionesDia;
        this.licenciaSustanciasControladas = licenciaSustanciasControladas;
        this.prescripcionesEmitidas = 0;
        this.comisionPorPrescripcion = 25.0;
    }

    @Override
    public double calcularSalarioTotal()
    {
        return getSalarioBase() + (prescripcionesEmitidas * comisionPorPrescripcion);
    }

    @Override
    public String mostrarInformacion()
    {
        return String.format("%s\nTipo: Farmacéutico | Límite prescripciones: %d/día\n" + "Licencia sustancias controladas: %s | Prescripciones emitidas: %d\n" +"Comisión/prescripción: Q%.2f | Salario total: Q%.2f",
        toString(), limitePrescripcionesDia, (licenciaSustanciasControladas ? "Sí" : "No"), 
        prescripcionesEmitidas, comisionPorPrescripcion, calcularSalarioTotal());
    }

    public void registrarPrescripcion()
    {
        prescripcionesEmitidas++;
    }

    public boolean tieneLicenciaSustanciasControladas()
    {
        return licenciaSustanciasControladas;
    }

    public int getLimitePrescripcionesDia()
    {
        return limitePrescripcionesDia;
    }
}