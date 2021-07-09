package co.com.reto.models.jugador;

public class Carro {

    private Jugador conductor;
    private int ubicacion;

    public Carro(Jugador conductor) {
        this.conductor = conductor;
        this.ubicacion = 0;
    }

    public int getUbicacion() {
        return this.ubicacion;
    }

    public void setUbicacion(int ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Jugador getConductor() {
        return this.conductor;
    }

    public void setConductor(Jugador conductor) {
        this.conductor = conductor;
    }

    public void updatePosition(int steps) {
        this.ubicacion += steps;
    }

}
