import java.time.LocalDateTime;
import java.util.Arrays;

// Clase Main para iniciar el sistema de gestión hospitalaria

public class Main
{
    public static void main(String[] args)
    {
        GestorHospital gestor = new GestorHospital();
        cargarDatosIniciales(gestor);
        
        VistaHospital vista = new VistaHospital(gestor);
        vista.iniciar();
    }

    private static void cargarDatosIniciales(GestorHospital gestor)
    {
        // Doctores
        gestor.agregarTrabajador(new Doctor("D001", "Dr. Carlos Méndez", "Medicina General", 10, 8000, "Medicina Interna", 15, 250));
        gestor.agregarTrabajador(new Doctor("D002", "Dra. Ana López", "Pediatría", 8, 7500, "Pediatría", 12, 200));
        gestor.agregarTrabajador(new Doctor("D003", "Dr. Roberto García", "Cardiología", 15, 10000, "Cardiología", 10, 350));
        
        // Cirujanos
        gestor.agregarTrabajador(new Cirujano("C001", "Dr. Luis Hernández", "Cirugía", 12, 12000, Arrays.asList("Apendicectomía", "Cesárea", "Hernia"), 40, 5000, 500));
        gestor.agregarTrabajador(new Cirujano("C002", "Dra. María Rodríguez", "Cirugía", 10, 11000, Arrays.asList("Bypass", "Laparoscopía"), 35, 4500, 450));
        
        // Enfermeros
        gestor.agregarTrabajador(new Enfermero("E001", "Carmen Flores", "Emergencias", 5, 4500, "nocturno", "Nivel III"));
        gestor.agregarTrabajador(new Enfermero("E002", "José Martínez", "UCI", 7, 5000, "diurno", "Nivel IV"));
        gestor.agregarTrabajador(new Enfermero("E003", "Patricia Ruiz", "Pediatría", 4, 4000, "nocturno", "Nivel II"));
        
        // Radiólogos
        gestor.agregarTrabajador(new Radiologo("R001", "Dr. Fernando Castro", "Radiología", 9, 8500, Arrays.asList("Rayos X", "Tomografía", "Resonancia Magnética"), 300));
        gestor.agregarTrabajador(new Radiologo("R002", "Dra. Sandra Morales", "Radiología", 6, 7000, Arrays.asList("Ultrasonido", "Mamografía"), 250));
        
        // Farmacéuticos
        gestor.agregarTrabajador(new Farmaceutico("F001", "Lic. Pedro Álvarez", "Farmacia", 8, 5500, 150, true));
        gestor.agregarTrabajador(new Farmaceutico("F002", "Lic. Laura Gómez", "Farmacia", 5, 5000, 120, false));
        
        // Terapeutas
        gestor.agregarTrabajador(new Terapeuta("T001", "Lic. Miguel Ortiz", "Rehabilitación", 6, 6000, "Fisioterapia", 60, 180));
        gestor.agregarTrabajador(new Terapeuta("T002", "Lic. Sofía Vega", "Psicología", 7, 6500, "Terapia Cognitivo-Conductual", 45, 200));
        gestor.agregarTrabajador(new Terapeuta("T003", "Lic. Andrea Ramírez", "Rehabilitación", 4, 5500, "Terapia Ocupacional", 50, 170));
        
        // Citas programadas
        TrabajadorMedico doctor1 = gestor.obtenerTrabajador("D001");
        TrabajadorMedico cirujano1 = gestor.obtenerTrabajador("C001");
        TrabajadorMedico terapeuta1 = gestor.obtenerTrabajador("T001");
        
        gestor.programarCita("Juan Pérez", doctor1, LocalDateTime.of(2025, 10, 15, 9, 0), TipoCita.CONSULTA_GENERAL);
        gestor.programarCita("María González", cirujano1, LocalDateTime.of(2025, 10, 16, 14, 0), TipoCita.CIRUGIA);
        gestor.programarCita("Pedro Sánchez", terapeuta1, LocalDateTime.of(2025, 10, 17, 10, 30), TipoCita.TERAPIA);
        
        // Registrar algunas actividades para mostrar cálculo de salarios
        ((Doctor) doctor1).registrarConsulta();
        ((Doctor) doctor1).registrarConsulta();
        ((Doctor) doctor1).registrarConsulta();
        
        ((Cirujano) cirujano1).registrarCirugia(4);
        
        ((Terapeuta) terapeuta1).registrarSesion();
        ((Terapeuta) terapeuta1).registrarSesion();
    }
}