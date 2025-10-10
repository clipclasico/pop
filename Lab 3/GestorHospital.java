import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Clase GestorHospital para gestionar personal médico y citas

public class GestorHospital
{
    private List<TrabajadorMedico> personalMedico;
    private List<CitaMedica> citas;
    private int contadorCitas;

    public GestorHospital()
    {
        this.personalMedico = new ArrayList<>();
        this.citas = new ArrayList<>();
        this.contadorCitas = 1;
    }

    public void agregarTrabajador(TrabajadorMedico trabajador)
    {
        personalMedico.add(trabajador);
    }

    public TrabajadorMedico obtenerTrabajador(String idEmpleado)
    {
        for (TrabajadorMedico trabajador : personalMedico)
        {
            if (trabajador.getIdEmpleado().equals(idEmpleado))
            {
                return trabajador;
            }
        }
        return null;
    }

    public List<TrabajadorMedico> listarPersonalPorDepartamento(String departamento)
    {
        List<TrabajadorMedico> resultado = new ArrayList<>();
        for (TrabajadorMedico trabajador : personalMedico)
        {
            if (trabajador.getDepartamento().equalsIgnoreCase(departamento))
            {
                resultado.add(trabajador);
            }
        }
        return resultado;
    }

    public CitaMedica programarCita(String nombrePaciente, TrabajadorMedico trabajador, LocalDateTime fechaHora, TipoCita tipoCita)
    {
        String idCita = String.format("CITA-%04d", contadorCitas++);
        CitaMedica cita = new CitaMedica(idCita, nombrePaciente, trabajador, fechaHora, tipoCita);
        citas.add(cita);
        return cita;
    }

    public boolean reagendarCita(String idCita, LocalDateTime nuevaFecha)
    {
        CitaMedica cita = buscarCita(idCita);
        if (cita == null) return false;
        
        if (verificarDisponibilidad(cita.getTrabajadorAsignado(), nuevaFecha))
        {
            cita.reagendar(nuevaFecha, "Reagendamiento solicitado");
            return true;
        }
        return false;
    }

    public boolean cancelarCita(String idCita, String razon)
    {
        CitaMedica cita = buscarCita(idCita);
        if (cita == null) return false;
        
        cita.cambiarEstado(EstadoCita.CANCELADA, razon);
        return true;
    }

    public boolean confirmarCita(String idCita)
    {
        CitaMedica cita = buscarCita(idCita);
        if (cita == null) return false;
        
        if (cita.getEstado() == EstadoCita.PROGRAMADA || cita.getEstado() == EstadoCita.REAGENDADA)
        {
            cita.cambiarEstado(EstadoCita.CONFIRMADA, "Cita confirmada por el paciente");
            return true;
        }
        return false;
    }

    public boolean verificarDisponibilidad(TrabajadorMedico trabajador, LocalDateTime fechaHora)
    {
        for (CitaMedica cita : citas)
        {
            if (cita.getTrabajadorAsignado().equals(trabajador) && cita.getFechaHora().equals(fechaHora) && (cita.getEstado() == EstadoCita.PROGRAMADA || cita.getEstado() == EstadoCita.CONFIRMADA))
            {
                return false;
            }
        }
        return true;
    }

    public List<CitaMedica> obtenerCitasPorTrabajador(String idEmpleado)
    {
        List<CitaMedica> resultado = new ArrayList<>();
        for (CitaMedica cita : citas)
        {
            if (cita.getTrabajadorAsignado().getIdEmpleado().equals(idEmpleado))
            {
                resultado.add(cita);
            }
        }
        return resultado;
    }

    public List<CitaMedica> obtenerCitasPorEstado(EstadoCita estado)
    {
        List<CitaMedica> resultado = new ArrayList<>();
        for (CitaMedica cita : citas)
        {
            if (cita.getEstado() == estado)
            {
                resultado.add(cita);
            }
        }
        return resultado;
    }

    public double calcularNominaDepartamento(String departamento)
    {
        double total = 0;
        for (TrabajadorMedico trabajador : personalMedico)
        {
            if (trabajador.getDepartamento().equalsIgnoreCase(departamento))
            {
                total += trabajador.calcularSalarioTotal();
            }
        }
        return total;
    }

    public double calcularNominaTotal()
    {
        double total = 0;
        for (TrabajadorMedico trabajador : personalMedico)
        {
            total += trabajador.calcularSalarioTotal();
        }
        return total;
    }

    public String generarReportePersonal()
    {
        StringBuilder reporte = new StringBuilder();
        reporte.append("=".repeat(80)).append("\n");
        reporte.append("REPORTE DE PERSONAL MÉDICO\n");
        reporte.append("=".repeat(80)).append("\n\n");
        
        for (TrabajadorMedico trabajador : personalMedico)
        {
            reporte.append(trabajador.mostrarInformacion()).append("\n");
            reporte.append("-".repeat(80)).append("\n");
        }
        
        reporte.append(String.format("\nTotal de trabajadores: %d\n", personalMedico.size()));
        return reporte.toString();
    }

    public String generarReporteCitas()
    {
        StringBuilder reporte = new StringBuilder();
        reporte.append("=".repeat(80)).append("\n");
        reporte.append("REPORTE DE CITAS MÉDICAS\n");
        reporte.append("=".repeat(80)).append("\n\n");
        
        for (CitaMedica cita : citas)
        {
            reporte.append(cita.toString()).append("\n");
            reporte.append("-".repeat(80)).append("\n");
        }
        
        reporte.append(String.format("\nTotal de citas: %d\n", citas.size()));
        return reporte.toString();
    }

    public String generarReporteFinanciero()
    {
        StringBuilder reporte = new StringBuilder();
        reporte.append("=".repeat(80)).append("\n");
        reporte.append("REPORTE FINANCIERO - NÓMINA POR DEPARTAMENTO\n");
        reporte.append("=".repeat(80)).append("\n\n");
        
        Set<String> departamentos = new HashSet<>();
        for (TrabajadorMedico trabajador : personalMedico)
        {
            departamentos.add(trabajador.getDepartamento());
        }
        
        for (String depto : departamentos)
        {
            double nominaDepto = calcularNominaDepartamento(depto);
            reporte.append(String.format("Departamento: %s\n", depto));
            reporte.append(String.format("Nómina total: Q%.2f\n\n", nominaDepto));
        }
        
        reporte.append("-".repeat(80)).append("\n");
        reporte.append(String.format("NÓMINA TOTAL DEL HOSPITAL: Q%.2f\n", calcularNominaTotal()));
        return reporte.toString();
    }

    public List<CitaMedica> obtenerHistorialReagendamientos()
    {
        List<CitaMedica> citasReagendadas = new ArrayList<>();
        for (CitaMedica cita : citas) {
            if (!cita.getHistorialCambios().isEmpty())
            {
                citasReagendadas.add(cita);
            }
        }
        return citasReagendadas;
    }

    public List<TrabajadorMedico> buscarPersonalDisponible(String departamento, LocalDateTime fechaHora)
    {
        List<TrabajadorMedico> disponibles = new ArrayList<>();
        for (TrabajadorMedico trabajador : personalMedico)
        {
            if (trabajador.getDepartamento().equalsIgnoreCase(departamento) && verificarDisponibilidad(trabajador, fechaHora))
            {
                disponibles.add(trabajador);
            }
        }
        return disponibles;
    }

    private CitaMedica buscarCita(String idCita)
    {
        for (CitaMedica cita : citas)
        {
            if (cita.getIdCita().equals(idCita))
            {
                return cita;
            }
        }
        return null;
    }

    public List<TrabajadorMedico> getPersonalMedico()
    {
        return personalMedico;
    }

    public List<CitaMedica> getCitas()
    {
        return citas;
    }
}