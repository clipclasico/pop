import java.util.ArrayList;
import java.util.List;

class Guerrero extends RolJugador
{
    public Guerrero() {
        this.multiplicadorVida = 1.5;
        this.multiplicadorAtaque = 1.3;
        this.capacidadItems = 3;
        this.nombreRol = "Guerrero";
    }

@Override
public void aplicarBonificaciones(Jugador jugador) {
    int nuevaVida = calcularVidaBonus(jugador.getPuntosVidaMax());
    int nuevoAtaque = calcularAtaqueBonus(jugador.getPoderAtaque());

    jugador.setPuntosVidaMax(nuevaVida);
    jugador.setPoderAtaque(nuevoAtaque);
}


    @Override
    public List<Item> obtenerItemsIniciales() {
        List<Item> items = new ArrayList<>();
        items.add(new Pocion(2));
        items.add(new Elixir(1));
        return items;
    }

    @Override
    public String toString() {
        return nombreRol + " - Tanque resistente con alto daño";
    }
}