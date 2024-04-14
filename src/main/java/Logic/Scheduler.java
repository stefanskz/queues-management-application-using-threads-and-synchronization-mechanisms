package Logic;
import model.*;

import java.util.*;
public class Scheduler {
    private List<Server> servers;
    private int nrOfServers;
    private Strategy strategy;

    public Scheduler(int nrOfServers) {
        this.nrOfServers = nrOfServers;
        this.servers = new ArrayList<>();
        for (int index = 0; index < nrOfServers; index++) {
            Server emptyServer = new Server();
            servers.add(emptyServer);
            Thread thr = new Thread(emptyServer);
            thr.start();
        }
    }

    public void dispatchTask(Task task) {
        strategy.addTask(servers, task);
    }

    public void changeStrategy(boolean flag) {
        if (!flag) {
            strategy = new ShortestQueueStrategy();
        } else if (flag) {
            strategy = new TimeStrategy();
        }
    }

    public int nrOfTasksInQueues() {
        int nr = 0;
        for (Server index : servers) {
            for (Task indexTask : index.getTasks()) {
                if (indexTask.getServiceTime() != 0)
                    nr++;
            }
        }
        return nr;
    }

    public List<Server> getServers() {
        return servers;
    }
}