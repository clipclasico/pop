/// --- Controlador ---

import java.util.ArrayList;

public class ControladorDispositivos
{
    private Catalogo catalogo;
    
    public ControladorDispositivos()
    {
        this.catalogo = new Catalogo();
    }
    
    public void inicializarSistema()
    {
        catalogo.init();
    }

    public String listarTodosDispositivos()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("LISTADO COMPLETO DE DISPOSITIVOS\n");
        
        ArrayList<Dispositivo> dispositivos = catalogo.getDispositivos();
        
        if (dispositivos.isEmpty())
        {
            sb.append("No hay dispositivos en el catálogo.\n");
        } else {
            for (int i = 0; i < dispositivos.size(); i++)
            {
                sb.append("[").append(i + 1).append("] ");
                sb.append(dispositivos.get(i).toString());
                sb.append("\n");
            }
            sb.append("Total de dispositivos: ").append(dispositivos.size()).append("\n");
        }
        
        return sb.toString();
    }
    
    public String buscarDispositivoPorId(String id)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("BÚSQUEDA POR ID");
        
        Dispositivo dispositivo = catalogo.buscarPorId(id);
        
        if (dispositivo == null)
        {
            sb.append("No se encontró ningún dispositivo con ID: ").append(id).append("\n");
        } else {
            sb.append("Dispositivo encontrado:\n");
            sb.append(dispositivo.toString());
        }
        
        return sb.toString();
    }
    
    public String buscarDispositivosPorNombre(String nombre)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("BÚSQUEDA POR NOMBRE");
        
        ArrayList<Dispositivo> resultados = catalogo.buscarPorNombre(nombre);
        
        if (resultados.isEmpty())
        {
            sb.append("No se encontraron dispositivos con nombre: ").append(nombre).append("\n");
        } else {
            sb.append("Se encontraron ").append(resultados.size()).append(" dispositivo(s):\n");
            for (int i = 0; i < resultados.size(); i++)
            {
                sb.append("[").append(i + 1).append("] ");
                sb.append(resultados.get(i).toString());
                sb.append("\n");
            }
        }
        
        return sb.toString();
    }
    
    public String ordenarYListarPorConsumo()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("DISPOSITIVOS ORDENADOS POR CONSUMO ELÉCTRICO\n");
        
        catalogo.ordenarPorConsumo();
        ArrayList<Dispositivo> dispositivos = catalogo.getDispositivos();
        
        for (int i = 0; i < dispositivos.size(); i++)
        {
            sb.append("[").append(i + 1).append("] ");
            sb.append(dispositivos.get(i).toString());
            sb.append("\n");
        }
        
        sb.append("Ordenamiento completado. Total: ").append(dispositivos.size()).append(" dispositivos.\n");
        
        return sb.toString();
    }
    
    public int obtenerCantidadDispositivos()
    {
        return catalogo.getDispositivos().size();
    }
}