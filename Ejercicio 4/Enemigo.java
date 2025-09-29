import java.util.List;
import java.util.Random;

abstract class Enemigo extends Combatiente {
    protected boolean esJefe;
    protected int nivelAmenaza;
    private Random random = new Random();

    public Enemigo(String nombre, int vida, int ataque, boolean esJefe) {
        super(nombre, vida, ataque);
        this.esJefe = esJefe;
        this.nivelAmenaza = esJefe ? 3 : 1;
        
        if (esJefe) {
            double bonus = calcularBonusJefe();
            this.puntosVida = (int)(vida * bonus);
            this.puntosVidaMax = this.puntosVida;
            this.poderAtaque = (int)(ataque * bonus);
            this.nombre = "Jefe " + this.nombre;
        }
        
        mostrarMensajeInicio();
    }

    @Override
    public void tomarTurno() {
        if (!vivo) return;
        
    }

    public abstract void usarHabilidadEspecial(Combatiente objetivo);

    public abstract void usarHabilidadJefe(List<Combatiente> objetivos);

    protected double calcularBonusJefe() {
        return 1.8; // 80% más de vida y ataque para jefes
    }

    public AccionEnemigo decidirAccion() {
        if (!vivo) return AccionEnemigo.PASAR;
        
        int probabilidad = random.nextInt(100);
        
        if (esJefe && probabilidad < 30) {
            return AccionEnemigo.HABILIDAD_JEFE;
        } else if (probabilidad < 40) {
            return AccionEnemigo.HABILIDAD_ESPECIAL;
        } else if (probabilidad < 90) {
            return AccionEnemigo.ATACAR;
        } else {
            return AccionEnemigo.PASAR;
        }
    }

    // Getters
    public boolean getEsJefe() { return esJefe; }
    public int getNivelAmenaza() { return nivelAmenaza; }

    @Override
    public void mostrarMensajeInicio() {
    }

    @Override
    public void mostrarMensajeMuerte() {
 
    }
}