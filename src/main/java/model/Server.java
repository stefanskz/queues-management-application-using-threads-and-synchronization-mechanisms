package model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;

    public Server() {
        tasks = new LinkedBlockingQueue<>();
        waitingPeriod = new AtomicInteger(0);
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
        this.setWaitingPeriod(this.getWaitingPeriod() + newTask.getServiceTime());
    }

    public void run() {
        while (true) {
            //take next task from queue
            //stop the thread for a time equal with the task's service time
            //decrement the waitingPeriod
        }
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public int getWaitingPeriod() {
        return this.waitingPeriod.get();
    }

    public void setWaitingPeriod(int waitingPeriod) {
        this.waitingPeriod.set(waitingPeriod);
    }
}