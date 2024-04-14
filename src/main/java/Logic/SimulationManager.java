package Logic;

import gui.ResultInterface;
import model.Task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SimulationManager implements Runnable {

    private int timeLimit;
    private int minArrivalTime;
    private int maxArrivalTime;
    private int minServiceTime;
    private int maxServiceTime;
    private int totalSerTime;
    private int totalWaiTime;
    private int numberOfServers;
    private int numberOfClients;
    private static int x;
    private Scheduler scheduler;
    private List<Task> tasks;
    private int peekTime = 0;
    private int maxTask = 0;

    ResultInterface resultInterface;

    public SimulationManager(int timeLimit, int minArrivalTime, int maxArrivalTime, int minServiceTime, int maxServiceTime, int numberOfServers, int numberOfClients) {
        this.timeLimit = timeLimit;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minServiceTime = minServiceTime;
        this.maxServiceTime = maxServiceTime;
        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;
        this.totalWaiTime = 0;
        resultInterface = new ResultInterface(this.numberOfServers);
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
            totalSerTime += randomNr2;
            Task index = new Task(x, randomNr1, randomNr2);
            tasks.add(index);
            x++;
        }
        Collections.sort(tasks);
    }

    @Override
    public String toString() {
        return "SimulationManager{" +
                "tasks=" + tasks +
                '}';
    }

    @Override
    public void run() {
        try {
            int currentTime = 0;
            BufferedWriter sout = new BufferedWriter(new FileWriter("file.txt"));
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
                print(sout, currentTime);
                resultInterface.update(currentTime, tasks, scheduler.getServers());
                currentTime++;
            }
            resultInterface.finalUpdate((double) totalWaiTime / numberOfClients, (double) totalSerTime / numberOfClients, peekTime);
            sout.write("Peak hour: " + peekTime + ", with " + maxTask + " tasks in all queues!");
            sout.newLine();
            sout.write("Avg. Service Time: " + ((double) totalSerTime / numberOfClients) + ".");
            sout.newLine();
            sout.write("Avg. Waiting Time: " + ((double) totalWaiTime / numberOfClients) + ".");
            sout.close();
            System.out.println("Stop!");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void print(BufferedWriter sout, int currentTime) throws IOException {
        sout.write("Time: " + currentTime);
        sout.newLine();
        sout.write("Waiting clients: " + tasks);
        sout.newLine();
        int size = 0;
        for (int index = 0; index < scheduler.getServers().size(); index++) {
            sout.write("Queue " + (index + 1) + ": ");
            if (scheduler.getServers().get(index).getTasks().isEmpty()) {
                sout.write("closed");
                sout.newLine();
            } else {
                sout.write(scheduler.getServers().get(index).getTasks().toString() + ", waitingPeriod: " + scheduler.getServers().get(index).getWaitingPeriod() + ".");
                size += scheduler.getServers().get(index).getTasks().size();
                sout.newLine();
            }
        }
        totalWaiTime += size;
        if (size > maxTask) {
            maxTask = size;
            peekTime = currentTime;
        }
        sout.newLine();
    }
}