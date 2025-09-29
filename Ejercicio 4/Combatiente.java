// clase base para todos los combatientes en la batalla

public abstract class Combatiente
{
    protected String nombre;
    protected int puntosVida;
    protected int puntosVidaMax;
    protected int poderAtaque;
    protected boolean vivo;

    // Constructor
    public Combatiente(String nombre, int vida, int ataque)
    {
        this.nombre = nombre;
        this.puntosVida = vida;
        this.puntosVidaMax = vida;
        this.poderAtaque = ataque;
        this.vivo = true;
    }

    // Realiza un ataque a otro combatiente
    public void atacar(Combatiente objetivo)
    {
        if (!this.vivo || !objetivo.vivo) return;
        
        objetivo.recibirDanio(this.poderAtaque);
    }

    // Recibe daño y actualiza estado
    public void recibirDanio(int danio)
    {
        if (!vivo) return;
        
        this.puntosVida -= danio;
        if (this.puntosVida <= 0)
        {
            this.puntosVida = 0;
            this.vivo = false;
            mostrarMensajeMuerte();
        }
    }
    
    // Cura puntos de vida
    public void curar(int cantidad)
    {
        if (!vivo) return;
        
        this.puntosVida = Math.min(this.puntosVida + cantidad, this.puntosVidaMax);
    }

    // Verifica si el combatiente está vivo
    public boolean estaVivo()
    {
        return vivo;
    }

    // Getters
    public String getNombre()
    {
        return nombre;
    }

    public int getPuntosVida()
    {
        return puntosVida;
    }

    public int getPoderAtaque()
    {
        return poderAtaque;
    }

    public int getPuntosVidaMax()
    {
        return puntosVidaMax;
    }

    // Método abstracto para que cada combatiente tome su turno
    public abstract void tomarTurno();

    // Muestra mensaje al iniciar
    public void mostrarMensajeInicio()
    {

    }

    // Muestra mensaje al morir
    public void mostrarMensajeMuerte()
    {

    }

    @Override // toString para mostrar estado
    public String toString()
    {
        String estado = vivo ? "Vivo" : "Muerto";
        return nombre + " - Vida: " + puntosVida + "/" + puntosVidaMax + " - Ataque: " + poderAtaque + " - Estado: " + estado;
    }

    // Setters
    public void setPuntosVida(int puntosVida)
    {
        this.puntosVida = puntosVida;
    }

    public void setPuntosVidaMax(int puntosVidaMax)
    {
        this.puntosVidaMax = puntosVidaMax;
    }

    public void setPoderAtaque(int poderAtaque)
    {
        this.poderAtaque = poderAtaque;
    }
}