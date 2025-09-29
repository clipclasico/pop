import java.util.ArrayList;
import java.util.List;

class Explorador extends RolJugador {

    public Explorador() {
        this.multiplicadorVida = 1.0;
        this.multiplicadorAtaque = 1.0;
        this.capacidadItems = 8;
        this.nombreRol = "Explorador";
    }

    @Override
    public void aplicarBonificaciones(Jugador jugador) {
    }

    @Override
    public List<Item> obtenerItemsIniciales() {
        List<Item> items = new ArrayList<>();
        items.add(new Pocion(3));
        items.add(new Elixir(2));
        items.add(new BombaHumo(2));
        items.add(new Antidoto(1));
        return items;
    }

    public String detectarDebilidad(Enemigo enemigo) {
        if (enemigo instanceof Orco) {
            return "El Orco es vulnerable después de usar ataque de rabia";
        }
        return "No se detectaron debilidades específicas";
    }

    @Override
    public String toString() {
        return nombreRol + " - Versátil con amplia variedad de items";
    }
}