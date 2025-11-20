/// Clase RadioClaseB
/// Extiende la clase Radio e implementa la interfaz ITelefonoClaseB.
/// Proporciona funcionalidades específicas para el modo teléfono en Clase B

import java.util.ArrayList;

public class RadioClaseB extends Radio implements ITelefonoClaseB
{
    
    private Contacto ultimoContactoLlamado;
    private ArrayList<String> tarjetasPresentacion;

    public RadioClaseB()
    {
        super();
        this.ultimoContactoLlamado = null;
        this.tarjetasPresentacion = new ArrayList<>();
        inicializarTarjetasEjemplo();
    }

    private void inicializarTarjetasEjemplo()
    {
        tarjetasPresentacion.add("Ing. Roberto Méndez | CEO Tech Solutions | +502 5555-1111");
        tarjetasPresentacion.add("Lic. Sofia Herrera | Dir. Marketing | +502 5555-2222");
        tarjetasPresentacion.add("Dr. Fernando Castro | Consultor | +502 5555-3333");
    }

    @Override
    public void rellamarUltimo()
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
        if (ultimoContactoLlamado == null)
            {
            System.out.println("No hay registro de llamadas anteriores.");
            return;
        }
        if (enLlamada)
            {
            System.out.println("❌ Ya hay una llamada activa.");
            return;
        }
        
        enLlamada = true;
        contactoLlamadaActual = ultimoContactoLlamado;
        System.out.println("🔄 Rellamando a " + ultimoContactoLlamado.getNombre() +  " (" + ultimoContactoLlamado.getNumero() + ")...");
    }

    @Override
    public void llamarContacto(String nombre)
    {
        super.llamarContacto(nombre);
        if (enLlamada && contactoLlamadaActual != null)
            {
            ultimoContactoLlamado = contactoLlamadaActual;
        }
    }

    public Contacto getUltimoContactoLlamado()
    {
        return ultimoContactoLlamado;
    }

    public String verTarjetasPresentacion()
    {
        if (!encendido)
            {
            return "El radio está apagado. Enciéndalo primero.";
        }
        
        StringBuilder resultado = new StringBuilder();
        resultado.append("\n╔════════════════════════════════════════╗\n");
        resultado.append("║     TARJETAS DE PRESENTACIÓN - B      ║\n");
        resultado.append("╠════════════════════════════════════════╣\n");
        
        if (tarjetasPresentacion.isEmpty())
            {
            resultado.append("║ No hay tarjetas guardadas             ║\n");
        } else {
            mostrarTarjetasRecursivo(0, resultado);
        }
        
        resultado.append("╚════════════════════════════════════════╝");
        return resultado.toString();
    }

    private void mostrarTarjetasRecursivo(int indice, StringBuilder sb)
    {
        if (indice >= tarjetasPresentacion.size())
            {
            return;
        }
        String tarjeta = tarjetasPresentacion.get(indice);
        if (tarjeta.length() <= 36)
            {
            sb.append(String.format("║ %d. %-35s ║\n", (indice + 1), tarjeta));
        } else {
            sb.append(String.format("║ %d. %-35s ║\n", (indice + 1), 
                      tarjeta.substring(0, 35)));
            sb.append(String.format("║    %-36s ║\n", 
                      tarjeta.substring(35, Math.min(71, tarjeta.length()))));
        }
        mostrarTarjetasRecursivo(indice + 1, sb);
    }

    @Override
    public String usarFuncionalidadProductividad()
    {
        return verTarjetasPresentacion();
    }

    public void agregarTarjeta(String tarjeta)
    {
        if (!encendido)
            {
            System.out.println("El radio está apagado. Enciéndalo primero.");
            return;
        }
        tarjetasPresentacion.add(tarjeta);
        System.out.println("Tarjeta agregada correctamente.");
    }
}