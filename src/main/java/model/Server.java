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
        synchronized (waitingPeriod) {
            try {
                tasks.put(newTask);
                this.setWaitingPeriod(this.getWaitingPeriod() + newTask.getServiceTime());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void run() {
        while (true) {
            try {
                Task client = tasks.peek();
                if (client != null) {
                    Thread.sleep(1000);
                    tasks.peek().setServiceTime(client.getServiceTime() - 1);
                    synchronized (waitingPeriod) {
                        this.setWaitingPeriod(this.getWaitingPeriod() - 1);
                    }
                    if (tasks.peek().getServiceTime() <= 0) {
                        tasks.poll();
                    }
                } else {
                    Thread.sleep(1000);
                }
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
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