/// Clase RadioClaseA
/// Extiende la clase Radio e implementa la interfaz ITelefonoClaseA.
/// Proporciona funcionalidades específicas para el modo teléfono en Clase A

import java.util.ArrayList;

public class RadioClaseA extends Radio implements ITelefonoClaseA
{
    
    private String modoAudio;
    private ArrayList<String> viajesPlanificados;

    public RadioClaseA()
    {
        super();
        this.modoAudio = "SPEAKER";
        this.viajesPlanificados = new ArrayList<>();
        inicializarViajesEjemplo();
    }

    private void inicializarViajesEjemplo()
    {
        viajesPlanificados.add("Antigua Guatemala - 25/11/2025 - 09:00 AM");
        viajesPlanificados.add("Lago Atitlán - 02/12/2025 - 07:30 AM");
    }

    @Override
    public void cambiarASpeaker()
    {
        if (!encendido)
            {
            System.out.println("El radio está apagado. Enciéndalo primero.");
            return;
        }
        if (!telefonoConectado)
            {
            System.out.println("No hay teléfono conectado.");
            return;
        }
        if (!enLlamada)
            {
            System.out.println("No hay llamada activa.");
            return;
        }
        modoAudio = "SPEAKER";
        System.out.println("Audio cambiado a SPEAKER (Altavoces del vehículo)");
    }

    @Override
    public void cambiarAAuriculares()
    {
        if (!encendido)
            {
            System.out.println("El radio está apagado. Enciéndalo primero.");
            return;
        }
        if (!telefonoConectado)
            {
            System.out.println("No hay teléfono conectado.");
            return;
        }
        if (!enLlamada)
            {
            System.out.println("No hay llamada activa.");
            return;
        }
        modoAudio = "AURICULARES";
        System.out.println("Audio cambiado a AURICULARES (Bluetooth personal)");
    }

    public String getModoAudio()
    {
        return modoAudio;
    }


    public void planificarViaje(String destino, String fecha)
    {
        if (!encendido)
            {
            System.out.println("El radio está apagado. Enciéndalo primero.");
            return;
        }
        String viaje = destino + " - " + fecha;
        viajesPlanificados.add(viaje);
        System.out.println("Viaje planificado: " + viaje);
    }

    @Override
    public String usarFuncionalidadProductividad()
    {
        if (!encendido)
            {
            return "El radio está apagado. Enciéndalo primero.";
        }
        
        StringBuilder resultado = new StringBuilder();
        resultado.append("\n╔════════════════════════════════════════╗\n");
        resultado.append("║     PLANIFICACIÓN DE VIAJES - A       ║\n");
        resultado.append("╠════════════════════════════════════════╣\n");
        
        if (viajesPlanificados.isEmpty())
            {
            resultado.append("║ No hay viajes planificados            ║\n");
        } else {
            mostrarViajesRecursivo(0, resultado);
        }
        
        resultado.append("╚════════════════════════════════════════╝");
        return resultado.toString();
    }

    private void mostrarViajesRecursivo(int indice, StringBuilder sb)
    {
        if (indice >= viajesPlanificados.size())
            {
            return;
        }
        String viaje = viajesPlanificados.get(indice);
        sb.append(String.format("║ %d. %-36s ║\n", (indice + 1), 
                  viaje.length() > 36 ? viaje.substring(0, 36) : viaje));
        mostrarViajesRecursivo(indice + 1, sb);
    }

    @Override
    public String obtenerEstado()
    {
        String estadoBase = super.obtenerEstado();
        if (encendido && enLlamada && modoActual.equals("TELEFONO"))
            {
            return estadoBase.replace("╚════════════════════════════════════════╝", 
                                    String.format("║Audio: %-28s ║\n╚════════════════════════════════════════╝", 
                                                modoAudio));
        }
        return estadoBase;
    }
}