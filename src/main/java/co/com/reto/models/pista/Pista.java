package co.com.reto.models.pista;

import co.com.reto.models.jugador.Carro;
import co.com.reto.models.jugador.Jugador;
import java.util.ArrayList;
import java.util.HashMap;

public class Pista {

    private int distanciaMeta;
    private ArrayList<Carril> carriles;
    private HashMap<Integer, Carro> carrilesAsignados;

    public Pista(int cantidadCarriles) {
        this.distanciaMeta = 10000;
        this.carrilesAsignados = new HashMap<Integer, Carro>();
        this.carriles = new ArrayList<>();

        for (int i = 0; i < cantidadCarriles; i++) {
            carriles.add(new Carril());
        }
    }

    public void asignarCarril(Carro carro) {
        for (int i = 0; i < this.carriles.size(); i++) {
            if (!this.carriles.get(i).isAsignado()) {
                this.carriles.get(i).setAsignado(true);
                carrilesAsignados.put(this.carriles.get(i).getIdCarril(), carro);
                break;
            }
        }
    }

    public Carro getCarroJugador(Jugador jugador) {
        for (Integer carrilId : carrilesAsignados.keySet()) {
            if (this.carrilesAsignados.get(carrilId).getConductor().getId() == jugador.getId()) {
                return this.carrilesAsignados.get(carrilId);
            }
        }
        return null;
    }

    public int getDistanciaMeta() {
        return this.distanciaMeta;
    }

    public void setDistanciaMeta(int distanciaMeta) {
        this.distanciaMeta = distanciaMeta;
    }

}
