/// interfaz IRadioBasico.java
/// Define las operaciones básicas de una radio.
/// Incluye métodos para encender, apagar, ajustar volumen y obtener el estado.

interface IRadioBasico
{
    void encender();
    void apagar();
    void subirVolumen();
    void bajarVolumen();
    String obtenerEstado();
}