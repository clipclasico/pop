public class Principal {
    public static void main(String[] args)
    {
        GestorProcesos modelo = new GestorProcesos();
        VistaProcesos vista = new VistaProcesos();
        ControladorProcesos controlador = new ControladorProcesos(modelo, vista);
        
        // Iniciar el sistema
        controlador.iniciar();
    }
}