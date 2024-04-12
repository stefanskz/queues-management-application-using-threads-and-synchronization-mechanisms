import Logic.SimulationManager;

public class Main {
    public static void main(String[] args) {
        SimulationManager gen = new SimulationManager(30, 2, 15, 2, 6, 2, 4);
        Thread t = new Thread(gen);
        t.start();
    }
}