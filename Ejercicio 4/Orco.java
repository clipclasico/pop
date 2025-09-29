import java.util.List;

class Orco extends Enemigo {
    private int fuerza;
    private boolean ataqueRagia;

    public Orco(boolean esJefe) {
        super("Orco", 80, 25, esJefe);
        this.fuerza = esJefe ? 18 : 12;
        this.ataqueRagia = false;
    }

    @Override
    public void usarHabilidadEspecial(Combatiente objetivo) {
        ataqueRabia();
        if (objetivo != null && objetivo.estaVivo()) {
            int danio = calcularDanioRabia();
            objetivo.recibirDanio(danio);
        }
    }

    @Override
    public void usarHabilidadJefe(List<Combatiente> objetivos) {
        if (!esJefe) return;
        
        gritoDeGuerra(objetivos);
    }

    public void ataqueRabia() {
        this.ataqueRagia = true;
    }

    public void gritoDeGuerra(List<Combatiente> objetivos) {
        int danioGrito = calcularDanioJefe();
        for (Combatiente objetivo : objetivos) {
            if (objetivo != this && objetivo.estaVivo()) {
                objetivo.recibirDanio(danioGrito);
            }
        }
    }

    public void regeneracionRapida() {
        if (esJefe && vivo) {
            curar(5);
        }
    }

    protected int calcularDanioRabia() {
        return this.poderAtaque + this.fuerza + (ataqueRagia ? 10 : 0);
    }

    protected int calcularDanioJefe() {
        return (int)(this.poderAtaque * 0.7) + this.fuerza;
    }

    @Override
    public void atacar(Combatiente objetivo) {
        if (!this.vivo || !objetivo.vivo) return;
        
        int danio = this.poderAtaque;
        if (ataqueRagia) {
            danio += 8;
            ataqueRagia = false;
        }
        
        objetivo.recibirDanio(danio);
    }

    public int getFuerza() { return fuerza; }

    @Override
    public String toString() {
        return super.toString() + " - Fuerza: " + fuerza;
    }
}