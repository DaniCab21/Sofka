package co.com.reto.juego;

import co.com.reto.dado.Dado;
import co.com.reto.models.jugador.Carro;
import co.com.reto.models.jugador.Jugador;
import co.com.reto.models.pista.Pista;
import co.com.reto.persistencia.DataBase;
import java.util.ArrayList;
import java.util.Collections;

public class Juego {

    public static int MAX_JUGADORES_GANAR = 3;
    private final DataBase dbc;

    public ArrayList<Jugador> jugadoresRegistrados;
    public Pista pistaActual;
    public ArrayList<Jugador> podioGeneral;

    public Jugador jugadorTurnoActual;
    public int indiceTurno;
    public ArrayList<Jugador> jugadoresEnJuego;
    public boolean juegoTerminado;
    public boolean isGanador;

    public ArrayList<Jugador> podio;

    public Juego() {
        dbc = new DataBase();

        this.jugadoresRegistrados = dbc.getTodosJugadores();
        this.podioGeneral = new ArrayList<>();

        this.jugadoresEnJuego = new ArrayList<>();
        this.podio = new ArrayList<>();
    }

    public void guardarJugador(String nombre) {
        Jugador newPlayer = new Jugador(nombre);
        this.jugadoresRegistrados.add(newPlayer);
        dbc.guardarJugador(newPlayer);
    }

    public void crearPista(int numeroCarriles) {
        this.pistaActual = new Pista(numeroCarriles);
    }

    public void agregarJugadorJuego(Jugador jugadorSeleccionado) {
        this.pistaActual.asignarCarril(new Carro(jugadorSeleccionado));
        this.jugadoresEnJuego.add(jugadorSeleccionado);
    }

    public void reset() {
        this.jugadoresRegistrados = dbc.getTodosJugadores();
        this.jugadoresEnJuego = new ArrayList<>();
        this.podio = new ArrayList<>();
        this.juegoTerminado = false;
    }

    public void iniciarJuego() {
        this.turnoAleatorio();
        this.indiceTurno = 0;
        this.jugadorTurnoActual = this.jugadoresEnJuego.get(indiceTurno);
    }

    public int jugar() {
        if (!this.juegoTerminado) {
            int pasos = Dado.lanzarDado() * 100;
            System.out.println("Jugador actual: " + this.jugadorTurnoActual.getNombre() + " indice de: " + this.indiceTurno);
            System.out.println("Pasos: " + pasos);
            Carro carro = this.pistaActual.getCarroJugador(this.jugadorTurnoActual);
            carro.updatePosition(pasos);
            System.out.println("Total distancia: " + carro.getUbicacion());

            if (carro.getUbicacion() >= this.pistaActual.getDistanciaMeta()) {
                this.podio.add(this.jugadorTurnoActual);
                this.jugadoresEnJuego.remove(this.indiceTurno);
                this.indiceTurno--;

                if (this.podio.size() == MAX_JUGADORES_GANAR || this.jugadoresEnJuego.size() == 0) {
                    this.juegoTerminado = true;
                }
            }
            this.siguienteTurno();
            return pasos;
        } else {
            if (podio.size() == 3) {
                if (!isGanador) {
                    dbc.actualizarPuntaje(Integer.toString(podio.get(0).getId()));
                    isGanador = true;
                }
            }

            return 0;
        }
    }

    public void siguienteTurno() {
        int totalNumberOfPlayers = this.jugadoresEnJuego.size() - 1;

        if (!this.juegoTerminado) {

            if (totalNumberOfPlayers <= this.indiceTurno) {
                this.indiceTurno = 0;
                this.jugadorTurnoActual = this.jugadoresEnJuego.get(this.indiceTurno);
            } else {
                this.indiceTurno++;
                this.jugadorTurnoActual = this.jugadoresEnJuego.get(this.indiceTurno);
            }
        }
    }

    public void turnoAleatorio() {
        Collections.shuffle(this.jugadoresEnJuego);
    }
}
