/// interfaz IModoReproduccion.java
/// Define las operaciones específicas para el modo de reproducción de música.
/// Incluye métodos para seleccionar listas de reproducción y navegar entre canciones.

interface IModoReproduccion
{
    void seleccionarLista(String nombre);
    void siguienteCancion();
    void cancionAnterior();
    Cancion obtenerCancionActual();
}