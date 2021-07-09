package co.com.reto.dado;

public class Dado {

    public static int lanzarDado() {
        return (int) (Math.random() * 6 + 1);
    }
}
