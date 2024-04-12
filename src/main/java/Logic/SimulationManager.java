package Logic;

import model.Task;

import java.util.*;

public class SimulationManager implements Runnable {

    private int timeLimit;
    private int minArrivalTime;
    private int maxArrivalTime;
    private int minServiceTime;
    private int maxServiceTime;
    private int numberOfServers;
    private int numberOfClients;
    private static int x;
    private Scheduler scheduler;

    private List<Task> tasks;

    public SimulationManager(int timeLimit, int minArrivalTime, int maxArrivalTime, int minServiceTime, int maxServiceTime, int numberOfServers, int numberOfClients) {
        this.timeLimit = timeLimit;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minServiceTime = minServiceTime;
        this.maxServiceTime = maxServiceTime;
        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;
        generateTasks();
        this.scheduler = new Scheduler(numberOfServers);
        this.scheduler.changeStrategy(true);
    }

    public void generateTasks() {
        x = 1;
        tasks = new ArrayList<>(numberOfClients);
        for (int i = 0; i < numberOfClients; i++) {
            Random random1 = new Random();
            int randomNr1 = random1.nextInt(maxArrivalTime - minArrivalTime + 1) + minArrivalTime;
            Random random2 = new Random();
            int randomNr2 = random2.nextInt(maxServiceTime - minServiceTime + 1) + minServiceTime;
            Task index = new Task(x, randomNr1, randomNr2);
            tasks.add(index);
            x++;
        }
        Collections.sort(tasks);
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getMinArrivalTime() {
        return minArrivalTime;
    }

    public void setMinArrivalTime(int minArrivalTime) {
        this.minArrivalTime = minArrivalTime;
    }

    public int getMaxArrivalTime() {
        return maxArrivalTime;
    }

    public void setMaxArrivalTime(int maxArrivalTime) {
        this.maxArrivalTime = maxArrivalTime;
    }

    public int getMinServiceTime() {
        return minServiceTime;
    }

    public void setMinServiceTime(int minServiceTime) {
        this.minServiceTime = minServiceTime;
    }

    public int getMaxServiceTime() {
        return maxServiceTime;
    }

    public void setMaxServiceTime(int maxServiceTime) {
        this.maxServiceTime = maxServiceTime;
    }

    public int getNumberOfServers() {
        return numberOfServers;
    }

    public void setNumberOfServers(int numberOfServers) {
        this.numberOfServers = numberOfServers;
    }

    public int getNumberOfClients() {
        return numberOfClients;
    }

    public void setNumberOfClients(int numberOfClients) {
        this.numberOfClients = numberOfClients;
    }

    @Override
    public String toString() {
        return "SimulationManager{" +
                "tasks=" + tasks +
                '}';
    }

    @Override
    public void run() {
        int currentTime = 0;
        while (currentTime < timeLimit) {
            Iterator<Task> taskIterator = tasks.iterator();
            while (taskIterator.hasNext()) {
                Task taskIndex = taskIterator.next();
                if (taskIndex.getArrivalTime().equals(currentTime)) {
                    scheduler.dispatchTask(taskIndex);
                    taskIterator.remove();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            System.out.println("Time: " + currentTime);
            System.out.println("Waiting clients: " + tasks);
            for (int index = 0; index < scheduler.getServers().size(); index++) {
                System.out.println("Queue " + (index + 1) + ": ");
                if (scheduler.getServers().get(index).getTasks().isEmpty())
                    System.out.println("closed");
                else
                    System.out.println(scheduler.getServers().get(index).getTasks());
            }
            System.out.println("\n");
            currentTime++;
        }
    }
}