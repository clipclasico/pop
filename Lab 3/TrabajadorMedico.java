public abstract class TrabajadorMedico
{
    private String idEmpleado;
    private String nombreCompleto;
    private String departamento;
    private int aniosExperiencia;
    private double salarioBase;

    public TrabajadorMedico(String idEmpleado, String nombreCompleto, String departamento, int aniosExperiencia, double salarioBase)
    {
        this.idEmpleado = idEmpleado;
        this.nombreCompleto = nombreCompleto;
        this.departamento = departamento;
        this.aniosExperiencia = aniosExperiencia;
        this.salarioBase = salarioBase;
    }

    public abstract double calcularSalarioTotal();
    public abstract String mostrarInformacion();

    public String getIdEmpleado() { return idEmpleado; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getDepartamento() { return departamento; }
    public int getAniosExperiencia() { return aniosExperiencia; }
    public double getSalarioBase() { return salarioBase; }

    @Override
    public String toString() {
        return String.format("ID: %s | %s | Depto: %s | Exp: %d años", 
            idEmpleado, nombreCompleto, departamento, aniosExperiencia);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TrabajadorMedico that = (TrabajadorMedico) obj;
        return idEmpleado.equals(that.idEmpleado);
    }
}