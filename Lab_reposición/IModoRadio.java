/// interfaz IModoRadio.java
/// Define las operaciones específicas para el modo de radio.
/// Incluye métodos para cambiar de banda, ajustar emisoras y gestionar presets.

interface IModoRadio
{
    void cambiarBanda();
    void subirEmisora();
    void bajarEmisora();
    void guardarEmisora(int posicion);
    void cargarEmisora(int posicion);
}