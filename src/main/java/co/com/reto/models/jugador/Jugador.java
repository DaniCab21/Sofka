package co.com.reto.models.jugador;


public class Jugador {
    
    private int id;
    public static int contadorJugadores;
    private String nombre;
    private int puntaje;

    public Jugador(String nombre) {
        this.id = ++Jugador.contadorJugadores;
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setIdJugador(int idJugador) {
        this.id = idJugador;
    }

    public int getId() {
        return this.id;
    }

    public int getPuntaje() {
        return this.puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
    
    
    
    
}
