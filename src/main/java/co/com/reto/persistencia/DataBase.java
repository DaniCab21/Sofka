package co.com.reto.persistencia;

import co.com.reto.models.jugador.Jugador;
import java.sql.*;
import java.util.ArrayList;

public class DataBase {

    private Connection connection;

    public DataBase() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost/car_track?user=root&password=example");
        } catch (SQLException exception) {
            System.out.println("SQLException: " + exception.getMessage());
            System.out.println("SQLState: " + exception.getSQLState());
            System.out.println("VendorError: " + exception.getErrorCode());
        }
    }

    public ArrayList<Jugador> getTodosJugadores() {
        int ID = 1;
        int NOMBRE = 2;
        int PUNTAJE = 3;

        ArrayList<Jugador> jugadores = new ArrayList<>();
        try {
            Statement query = this.connection.createStatement();
            ResultSet result = query.executeQuery("SELECT * from jugadores order by puntaje DESC");

            while (result.next()) {
                Jugador playerFromDB = new Jugador(result.getString(NOMBRE));
                playerFromDB.setIdJugador(result.getInt(ID));

                int puntaje = result.getString(PUNTAJE) == null ? 0 : Integer.parseInt(result.getString(PUNTAJE));
                playerFromDB.setPuntaje(puntaje);

                jugadores.add(playerFromDB);
            }
            query.close();

        } catch (SQLException exception) {
            System.out.println("SQLException: " + exception.getMessage());
            System.out.println("SQLState: " + exception.getSQLState());
            System.out.println("VendorError: " + exception.getErrorCode());
        } finally {
            return jugadores;
        }
    }

    public void guardarJugador(Jugador jugador) {
        try {
            String sql = "INSERT INTO jugadores VALUES (?, ?, ?)";
            PreparedStatement query = this.connection.prepareStatement(sql);
            query.setInt(1, jugador.getId());
            query.setString(2, jugador.getNombre());
            query.setInt(3, jugador.getPuntaje());

            query.execute();
            query.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public void actualizarPuntaje(String idJugador) {
        try {
            String sql = "UPDATE jugadores SET puntaje = puntaje + 1 WHERE id = ?;";
            PreparedStatement query = this.connection.prepareStatement(sql);
            query.setString(1, idJugador);
            query.execute();
            query.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

}
