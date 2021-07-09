package co.com.reto.models.pista;


public class Carril {
    private int idCarril;
    private boolean asignado;
    private static int contadorPista;

    public Carril() {
        this.idCarril = ++Carril.contadorPista;
        this.asignado = false;
    }

    public int getIdCarril() {
        return this.idCarril;
    }

    public void setIdCarril(int idCarril) {
        this.idCarril = idCarril;
    }

    public boolean isAsignado() {
        return asignado;
    }

    public void setAsignado(boolean asignado) {
        this.asignado = asignado;
    }
    
    
}
