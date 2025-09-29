public class Ficha
{
    private String simbolo;
    private boolean revelada;
    private boolean emparejada;
    
    public Ficha(String simbolo)
    {
        this.simbolo = simbolo;
        this.revelada = false;
        this.emparejada = false;
    }
    
    public String getSimbolo()
    {
        return revelada || emparejada ? simbolo : "?";
    }
    
    public boolean esRevelada()
    {
        return revelada;
    }

    public void setRevelada(boolean revelada)
    {
        this.revelada = revelada;
    }
    
    public boolean esEmparejada()
    {
        return emparejada;
    }
    
    public void setEmparejada(boolean emparejada)
    {
        this.emparejada = emparejada;
    }
}
