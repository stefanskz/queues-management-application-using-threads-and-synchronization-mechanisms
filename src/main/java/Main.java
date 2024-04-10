import Logic.SimulationManager;

public class Main {
    public static void main(String[] args) {
        SimulationManager manager = new SimulationManager(100, 2, 10, 3, 20, 3, 5);
        System.out.println(manager.toString());
    }
}