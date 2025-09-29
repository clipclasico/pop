// Juego de rol - Ejercicio 4
// Giovanni Orozco 251162
// Fecha: 28/09/2025

// Clase principal para iniciar el juego

public class Principal
{
    public static void main(String[] args)
    {
        ControladorBatalla juego = configurarJuego();
        juego.ejecutarJuego();
    }

    private static ControladorBatalla configurarJuego()
    {
        return new ControladorBatalla();
    }
}