import Logic.SimulationManager;

public class Main {
    public static void main(String[] args) {
        SimulationManager gen = new SimulationManager(20, 2, 10, 2, 6, 2, 4);
        Thread t = new Thread(gen);
        t.start();
    }

}