/// Interfaz para el modo teléfono
/// Define las operaciones específicas para el modo teléfono.
/// Incluye métodos para conectar/desconectar el teléfono

import java.util.ArrayList;

interface IModoTelefono
{
    void conectarTelefono();
    void desconectarTelefono();
    ArrayList<Contacto> mostrarContactos();
    void llamarContacto(String nombre);
    void finalizarLlamada();
}