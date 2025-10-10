import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Clase HistorialCambio para registrar cambios en el estado de una cita

public class HistorialCambio
{
    private LocalDateTime fechaCambio;
    private String descripcion;
    private EstadoCita estadoAnterior;
    private EstadoCita estadoNuevo;

    public HistorialCambio(String descripcion, EstadoCita estadoAnterior, EstadoCita estadoNuevo)
    {
        this.fechaCambio = LocalDateTime.now();
        this.descripcion = descripcion;
        this.estadoAnterior = estadoAnterior;
        this.estadoNuevo = estadoNuevo;
    }

    @Override
    public String toString()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("[%s] %s: %s → %s", 
            fechaCambio.format(formatter), descripcion, estadoAnterior, estadoNuevo);
    }

    public LocalDateTime getFechaCambio()
    {
        return fechaCambio;
    }

    public String getDescripcion()
    {
        return descripcion;
    }
}