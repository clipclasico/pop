import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class GeneradorEnemigos {
    private Random random;
    private static final double PROBABILIDAD_JEFE = 0.3;
    private static final int CANTIDAD_ENEMIGOS = 3;

    public GeneradorEnemigos() {
        this.random = new Random();
    }

    public List<Enemigo> generarTresOrcos() {
        List<Enemigo> orcos = new ArrayList<>();
        
        for (int i = 0; i < CANTIDAD_ENEMIGOS; i++) {
            Orco orco = crearOrcoAleatorio();
            orcos.add(orco);
        }
        
        return orcos;
    }

    public Orco crearOrcoAleatorio() {
        boolean esJefe = determinarSiEsJefe();
        return new Orco(esJefe);
    }

    public boolean determinarSiEsJefe() {
        return random.nextDouble() < PROBABILIDAD_JEFE;
    }
}