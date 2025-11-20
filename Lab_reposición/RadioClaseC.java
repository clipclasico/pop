/// Clase RadioClaseC
/// Representa un radio con funcionalidades de Clase C.
/// Incluye modo teléfono con llamadas en espera y funcionalidad de productividad para ver pronóstico del tiempo


public class RadioClaseC extends Radio implements ITelefonoClaseC
{
    
    private Contacto llamadaEnEspera;
    private String pronosticoClima;

    public RadioClaseC()
    {
        super();
        this.llamadaEnEspera = null;
        this.pronosticoClima = "Soleado 28°C | Mín: 18°C Máx: 30°C";
        actualizarPronostico();
    }

    private void actualizarPronostico()
    {
        pronosticoClima = "Guatemala City\n" +
                         "   Temperatura: 28°C\n" +
                         "   Mín: 18°C | Máx: 30°C\n" +
                         "   Humedad: 65%\n" +
                         "   Viento: 12 km/h";
    }

    @Override
    public void cambiarALlamadaEnEspera()
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
        if (llamadaEnEspera == null)
            {
            System.out.println("No hay llamada en espera.");
            return;
        }

        Contacto temporal = contactoLlamadaActual;
        contactoLlamadaActual = llamadaEnEspera;
        llamadaEnEspera = temporal;
        
        System.out.println("Llamada intercambiada:");
        System.out.println("Activa: " + contactoLlamadaActual.getNombre());
        System.out.println("En espera: " + llamadaEnEspera.getNombre());
    }

    public void recibirLlamadaEntrante(Contacto contacto)
    {
        if (!encendido)
            {
            System.out.println("El radio está apagado. Enciéndalo primero.");
            return;
        }
        if (!telefonoConectado) {
            System.out.println("No hay teléfono conectado.");
            return;
        }
        if (!enLlamada)
            {
            System.out.println("No hay llamada activa para poner en espera.");
            return;
        }
        
        llamadaEnEspera = contacto;
        System.out.println("Llamada entrante de " + contacto.getNombre() + " puesta en espera.");
    }

    public Contacto getLlamadaEnEspera()
    {
        return llamadaEnEspera;
    }

    @Override
    public void finalizarLlamada()
    {
        super.finalizarLlamada();
        if (llamadaEnEspera != null)
            {
            System.out.println("Aún hay una llamada en espera de " + llamadaEnEspera.getNombre());
        }
    }

    public String verPronostico()
    {
        if (!encendido)
            {
            return "El radio está apagado. Enciéndalo primero.";
        }
        
        StringBuilder resultado = new StringBuilder();
        resultado.append("\n╔════════════════════════════════════════╗\n");
        resultado.append("║     PRONÓSTICO DEL TIEMPO - C         ║\n");
        resultado.append("╠════════════════════════════════════════╣\n");
        
        String[] lineas = pronosticoClima.split("\n");
        for (String linea : lineas) {
            resultado.append(String.format("║ %-38s ║\n", linea));
        }
        
        resultado.append("╚════════════════════════════════════════╝");
        return resultado.toString();
    }

    @Override
    public String usarFuncionalidadProductividad()
    {
        return verPronostico();
    }

    public void actualizarPronosticoManual(String nuevoPronostico)
    {
        if (!encendido)
            {
            System.out.println("El radio está apagado. Enciéndalo primero.");
            return;
        }
        this.pronosticoClima = nuevoPronostico;
        System.out.println("Pronóstico actualizado.");
    }

    @Override
    public String obtenerEstado()
    {
        String estadoBase = super.obtenerEstado();
        if (encendido && llamadaEnEspera != null && modoActual.equals("TELEFONO"))
            {
            return estadoBase.replace("╚════════════════════════════════════════╝", 
                                    String.format("║En espera: %-24s ║\n╚════════════════════════════════════════╝", llamadaEnEspera.getNombre()));
        }
        return estadoBase;
    }
}