/// Clase radio
/// Implementa las interfaces de los diferentes modos de la radio.
/// Clase principal que maneja el estado y las funcionalidades de la radio.

import java.util.ArrayList;
public abstract class Radio implements IRadioBasico, IModoRadio, IModoReproduccion, IModoTelefono, IModoProductividad
{
    
    protected boolean encendido;
    protected int volumen;
    protected String modoActual;

    protected String bandaActual;
    protected double frecuenciaActual;
    protected double[] emisorasGuardadas;
    
    protected ArrayList<ListaReproduccion> listasReproduccion;
    protected ListaReproduccion listaActual;
    protected int indiceCancionActual;
    
    protected boolean telefonoConectado;
    protected ArrayList<Contacto> contactos;
    protected boolean enLlamada;
    protected Contacto contactoLlamadaActual;

    public Radio()
    {
        this.encendido = false;
        this.volumen = 50;
        this.modoActual = "RADIO";
        this.bandaActual = "FM";
        this.frecuenciaActual = 88.0;
        this.emisorasGuardadas = new double[50];
        this.listasReproduccion = new ArrayList<>();
        this.listaActual = null;
        this.indiceCancionActual = 0;
        this.telefonoConectado = false;
        this.contactos = new ArrayList<>();
        this.enLlamada = false;
        this.contactoLlamadaActual = null;
        inicializarDatos();
    }

    protected void inicializarDatos()
    {
        ListaReproduccion rock = new ListaReproduccion("Rock Classics");
        rock.agregarCancion(new Cancion("Bohemian Rhapsody", "5:55", "Queen", "Rock"));
        rock.agregarCancion(new Cancion("Stairway to Heaven", "8:02", "Led Zeppelin", "Rock"));
        rock.agregarCancion(new Cancion("Hotel California", "6:30", "Eagles", "Rock"));
        listasReproduccion.add(rock);

        ListaReproduccion pop = new ListaReproduccion("Pop Hits");
        pop.agregarCancion(new Cancion("Blinding Lights", "3:20", "The Weeknd", "Pop"));
        pop.agregarCancion(new Cancion("Levitating", "3:23", "Dua Lipa", "Pop"));
        pop.agregarCancion(new Cancion("Anti-Hero", "3:21", "Taylor Swift", "Pop"));
        listasReproduccion.add(pop);

        ListaReproduccion jazz = new ListaReproduccion("Jazz Lounge");
        jazz.agregarCancion(new Cancion("Take Five", "5:24", "Dave Brubeck", "Jazz"));
        jazz.agregarCancion(new Cancion("So What", "9:22", "Miles Davis", "Jazz"));
        jazz.agregarCancion(new Cancion("Autumn Leaves", "5:50", "Bill Evans", "Jazz"));
        listasReproduccion.add(jazz);

        contactos.add(new Contacto("Juan", "+502 5025-1234"));
        contactos.add(new Contacto("María", "+502 5025-5678"));
        contactos.add(new Contacto("Carlos", "+502 5025-1357"));
        contactos.add(new Contacto("Ana", "+502 5025-2468"));
        contactos.add(new Contacto("Pedro", "+502 5025-1928"));
    }


    @Override
    public void encender()
    {
        encendido = true;
        System.out.println("Radio encendido - Mercedes-Benz");
    }

    @Override
    public void apagar()
    {
        if (!encendido)
            {
            System.out.println("El radio ya está apagado.");
            return;
        }
        encendido = false;
        enLlamada = false;
        contactoLlamadaActual = null;
        System.out.println("Radio apagado.");
    }

    @Override
    public void subirVolumen()
    {
        if (!encendido)
            {
            System.out.println("El radio está apagado. Enciéndalo primero.");
            return;
        }
        if (volumen < 100)
            {
            volumen++;
            System.out.println("Volumen: " + volumen);
        } else {
            System.out.println("El volumen ya está al máximo.");
        }
    }

    @Override
    public void bajarVolumen()
    {
        if (!encendido)
            {
            System.out.println("El radio está apagado. Enciéndalo primero.");
            return;
        }
        if (volumen > 0)
            {
            volumen--;
            System.out.println("Volumen: " + volumen);
        } else {
            System.out.println("El volumen ya está al mínimo.");
        }
    }

    @Override
    public String obtenerEstado()
    {
        if (!encendido)
            {
            return "Radio: APAGADO";
        }

        StringBuilder estado = new StringBuilder();
        estado.append("\n╔════════════════════════════════════════╗\n");
        estado.append("║     RADIO MERCEDES-BENZ - ESTADO      ║\n");
        estado.append("╠════════════════════════════════════════╣\n");
        estado.append(String.format("║ Volumen: %-26d ║\n", volumen));
        estado.append(String.format("║ Modo: %-29s ║\n", modoActual));
        
        switch (modoActual)
        {
            case "RADIO":
                estado.append(String.format("║ Banda: %-28s ║\n", bandaActual));
                estado.append(String.format("║ Frecuencia: %-22.1f ║\n", frecuenciaActual));
                String preset = obtenerPresetActual();
                if (preset != null)
                    {
                    estado.append(String.format("║ Preset: %-26s ║\n", preset));
                }
                break;
                
            case "REPRODUCCION":
                if (listaActual != null && listaActual.getTamanio() > 0)
                    {
                    Cancion actual = listaActual.getCancion(indiceCancionActual);
                    estado.append(String.format("║ Lista: %-27s ║\n", listaActual.getNombre()));
                    estado.append(String.format("║ Canción: %-25s ║\n", actual.getNombre()));
                    estado.append(String.format("║ Artista: %-25s ║\n", actual.getAutor()));
                    estado.append(String.format("║ Duración: %-24s ║\n", actual.getDuracion()));
                    estado.append(String.format("║ Género: %-26s ║\n", actual.getGenero()));
                } else {
                    estado.append("║ No hay lista seleccionada         ║\n");
                }
                break;
                
            case "TELEFONO":
                estado.append(String.format("║ Teléfono: %-24s ║\n", 
                    telefonoConectado ? "Conectado" : "Desconectado"));
                if (enLlamada && contactoLlamadaActual != null)
                    {
                    estado.append(String.format("║ En llamada con: %-18s ║\n", 
                        contactoLlamadaActual.getNombre()));
                }
                break;
                
            case "PRODUCTIVIDAD":
                estado.append("║ Modo Productividad activo          ║\n");
                break;
        }
        
        estado.append("╚════════════════════════════════════════╝\n");
        return estado.toString();
    }

    private String obtenerPresetActual()
    {
        return buscarPresetRecursivo(0);
    }

    private String buscarPresetRecursivo(int indice)
    {
        if (indice >= emisorasGuardadas.length)
            {
            return null;
        }
        if (Math.abs(emisorasGuardadas[indice] - frecuenciaActual) < 0.01)
            {
            return String.valueOf(indice + 1);
        }
        return buscarPresetRecursivo(indice + 1);
    }

    @Override
    public void cambiarBanda()
    {
        if (!encendido)
            {
            System.out.println("El radio está apagado. Enciéndalo primero.");
            return;
        }
        if (bandaActual.equals("FM"))
            {
            bandaActual = "AM";
            frecuenciaActual = 530;
            System.out.println("Banda cambiada a AM - Frecuencia: " + frecuenciaActual);
        } else {
            bandaActual = "FM";
            frecuenciaActual = 88.0;
            System.out.println("Banda cambiada a FM - Frecuencia: " + frecuenciaActual);
        }
    }

    @Override
    public void subirEmisora()
    {
        if (!encendido)
            {
            System.out.println("El radio está apagado. Enciéndalo primero.");
            return;
        }
        if (bandaActual.equals("FM"))
            {
            if (frecuenciaActual + 0.5 <= 108.0)
                {
                frecuenciaActual += 0.5;
                System.out.println("FM: " + frecuenciaActual);
            } else {
                System.out.println("Frecuencia máxima de FM alcanzada.");
            }
        } else {
            if (frecuenciaActual + 10 <= 1610)
                {
                frecuenciaActual += 10;
                System.out.println("AM: " + (int)frecuenciaActual);
            } else {
                System.out.println("Frecuencia máxima de AM alcanzada.");
            }
        }
    }

    @Override
    public void bajarEmisora()
    {
        if (!encendido)
            {
            System.out.println("El radio está apagado. Enciéndalo primero.");
            return;
        }
        if (bandaActual.equals("FM"))
            {
            if (frecuenciaActual - 0.5 >= 88.0)
                {
                frecuenciaActual -= 0.5;
                System.out.println("FM: " + frecuenciaActual);
            } else {
                System.out.println("Frecuencia mínima de FM alcanzada.");
            }
        } else {
            if (frecuenciaActual - 10 >= 530)
                {
                frecuenciaActual -= 10;
                System.out.println("AM: " + (int)frecuenciaActual);
            } else {
                System.out.println("Frecuencia mínima de AM alcanzada.");
            }
        }
    }

    @Override
    public void guardarEmisora(int posicion)
    {
        if (!encendido)
            {
            System.out.println("El radio está apagado. Enciéndalo primero.");
            return;
        }
        if (posicion < 1 || posicion > 50)
            {
            System.out.println("Posición inválida. Debe ser entre 1 y 50.");
            return;
        }
        emisorasGuardadas[posicion - 1] = frecuenciaActual;
        System.out.println("Emisora guardada en preset " + posicion + ": " + bandaActual + " " + frecuenciaActual);
    }

    @Override
    public void cargarEmisora(int posicion)
    {
        if (!encendido)
            {
            System.out.println("El radio está apagado. Enciéndalo primero.");
            return;
        }
        if (posicion < 1 || posicion > 50)
            {
            System.out.println("Posición inválida. Debe ser entre 1 y 50.");
            return;
        }
        if (emisorasGuardadas[posicion - 1] == 0)
            {
            System.out.println("No hay emisora guardada en preset " + posicion);
            return;
        }
        frecuenciaActual = emisorasGuardadas[posicion - 1];
        System.out.println("Cargando preset " + posicion + ": " + bandaActual + " " + frecuenciaActual);
    }

    @Override
    public void seleccionarLista(String nombre)
    {
        if (!encendido)
            {
            System.out.println("El radio está apagado. Enciéndalo primero.");
            return;
        }
        ListaReproduccion lista = buscarListaRecursiva(nombre, 0);
        if (lista != null)
            {
            listaActual = lista;
            indiceCancionActual = 0;
            System.out.println("Lista seleccionada: " + lista.getNombre());
        } else {
            System.out.println("No se encontró la lista: " + nombre);
        }
    }

    protected ListaReproduccion buscarListaRecursiva(String nombre, int indice)
    {
        if (indice >= listasReproduccion.size())
            {
            return null;
        }
        if (listasReproduccion.get(indice).getNombre().equalsIgnoreCase(nombre))
            {
            return listasReproduccion.get(indice);
        }
        return buscarListaRecursiva(nombre, indice + 1);
    }

    @Override
    public void siguienteCancion()
    {
        if (!encendido)
            {
            System.out.println("El radio está apagado. Enciéndalo primero.");
            return;
        }
        if (listaActual == null)
            {
            System.out.println("No hay lista seleccionada.");
            return;
        }
        if (listaActual.getTamanio() == 0)
            {
            System.out.println("La lista está vacía.");
            return;
        }
        indiceCancionActual = (indiceCancionActual + 1) % listaActual.getTamanio();
        System.out.println(listaActual.getCancion(indiceCancionActual));
    }

    @Override
    public void cancionAnterior()
    {
        if (!encendido)
            {
            System.out.println("El radio está apagado. Enciéndalo primero.");
            return;
        }
        if (listaActual == null)
            {
            System.out.println("No hay lista seleccionada.");
            return;
        }
        if (listaActual.getTamanio() == 0)
            {
            System.out.println("La lista está vacía.");
            return;
        }
        indiceCancionActual = (indiceCancionActual - 1 + listaActual.getTamanio()) % listaActual.getTamanio();
        System.out.println(listaActual.getCancion(indiceCancionActual));
    }

    @Override
    public Cancion obtenerCancionActual()
    {
        if (listaActual != null && listaActual.getTamanio() > 0)
            {
            return listaActual.getCancion(indiceCancionActual);
        }
        return null;
    }

    @Override
    public void conectarTelefono()
    {
        if (!encendido)
            {
            System.out.println("El radio está apagado. Enciéndalo primero.");
            return;
        }
        if (telefonoConectado)
            {
            System.out.println("Ya hay un teléfono conectado.");
            return;
        }
        telefonoConectado = true;
        System.out.println("Teléfono conectado vía Bluetooth.");
    }

    @Override
    public void desconectarTelefono()
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
        if (enLlamada)
            {
            finalizarLlamada();
        }
        telefonoConectado = false;
        System.out.println("Teléfono desconectado.");
    }

    @Override
    public ArrayList<Contacto> mostrarContactos()
    {
        if (!encendido)
            {
            System.out.println("El radio está apagado. Enciéndalo primero.");
            return null;
        }
        if (!telefonoConectado)
            {
            System.out.println("No hay teléfono conectado.");
            return null;
        }
        System.out.println("\nLISTA DE CONTACTOS:");
        mostrarContactosRecursivo(0);
        return contactos;
    }

    private void mostrarContactosRecursivo(int indice)
    {
        if (indice >= contactos.size())
            {
            return;
        }
        System.out.println((indice + 1) + ". " + contactos.get(indice));
        mostrarContactosRecursivo(indice + 1);
    }

    @Override
    public void llamarContacto(String nombre)
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
        if (enLlamada) {
            System.out.println("Ya hay una llamada activa.");
            return;
        }
        Contacto contacto = buscarContactoRecursivo(nombre, 0);
        if (contacto != null)
            {
            enLlamada = true;
            contactoLlamadaActual = contacto;
            System.out.println("Llamando a " + contacto.getNombre() + " (" + contacto.getNumero() + ")...");
        } else {
            System.out.println("❌ Contacto no encontrado: " + nombre);
        }
    }

    protected Contacto buscarContactoRecursivo(String nombre, int indice)
    {
        if (indice >= contactos.size())
            {
            return null;
        }
        if (contactos.get(indice).getNombre().equalsIgnoreCase(nombre))
            {
            return contactos.get(indice);
        }
        return buscarContactoRecursivo(nombre, indice + 1);
    }

    @Override
    public void finalizarLlamada()
    {
        if (!encendido)
            {
            System.out.println("El radio está apagado. Enciéndalo primero.");
            return;
        }
        if (!enLlamada)
            {
            System.out.println("No hay llamada activa.");
            return;
        }
        System.out.println("Llamada finalizada con " + contactoLlamadaActual.getNombre());
        enLlamada = false;
        contactoLlamadaActual = null;
    }

    public void cambiarModo(String modo)
    {
        if (!encendido)
            {
            System.out.println("El radio está apagado. Enciéndalo primero.");
            return;
        }
        String modoUpper = modo.toUpperCase();
        if (modoUpper.equals("RADIO") || modoUpper.equals("REPRODUCCION") || modoUpper.equals("TELEFONO") || modoUpper.equals("PRODUCTIVIDAD"))
            {
            modoActual = modoUpper;
            System.out.println("Modo cambiado a: " + modoActual);
        } else {
            System.out.println("Modo inválido.");
        }
    }

    @Override
    public abstract String usarFuncionalidadProductividad();
}