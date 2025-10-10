import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Enumeración para el tipo de cita

public class CitaMedica
{
    private String idCita;
    private String nombrePaciente;
    private TrabajadorMedico trabajadorAsignado;
    private LocalDateTime fechaHora;
    private TipoCita tipoCita;
    private EstadoCita estado;
    private List<HistorialCambio> historialCambios;

    public CitaMedica(String idCita, String nombrePaciente, TrabajadorMedico trabajadorAsignado, LocalDateTime fechaHora, TipoCita tipoCita)
    {
        this.idCita = idCita;
        this.nombrePaciente = nombrePaciente;
        this.trabajadorAsignado = trabajadorAsignado;
        this.fechaHora = fechaHora;
        this.tipoCita = tipoCita;
        this.estado = EstadoCita.PROGRAMADA;
        this.historialCambios = new ArrayList<>();
    }

    public void cambiarEstado(EstadoCita nuevoEstado, String razon)
    {
        historialCambios.add(new HistorialCambio(razon, this.estado, nuevoEstado));
        this.estado = nuevoEstado;
    }

    public void reagendar(LocalDateTime nuevaFecha, String razon)
    {
        historialCambios.add(new HistorialCambio(razon + " (de " + fechaHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + " a " + nuevaFecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ")", 
        this.estado, EstadoCita.REAGENDADA));
        this.fechaHora = nuevaFecha;
        this.estado = EstadoCita.REAGENDADA;
    }

    @Override
    public String toString()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("ID: %s | Paciente: %s | Médico: %s\nFecha: %s | Tipo: %s | Estado: %s",
        idCita, nombrePaciente, trabajadorAsignado.getNombreCompleto(), 
        fechaHora.format(formatter), tipoCita, estado);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CitaMedica that = (CitaMedica) obj;
        return idCita.equals(that.idCita);
    }

    public String getIdCita() 
    {
        return idCita;
    }

    public String getNombrePaciente()
    {
        return nombrePaciente;
    }

    public TrabajadorMedico getTrabajadorAsignado()
    {
        return trabajadorAsignado;
    }

    public LocalDateTime getFechaHora()
    {
        return fechaHora;
    }

    public TipoCita getTipoCita()
    {
        return tipoCita;
    }

    public EstadoCita getEstado()
    {
        return estado;
    }
    
    public List<HistorialCambio> getHistorialCambios()
    {
        return historialCambios;
    }
}