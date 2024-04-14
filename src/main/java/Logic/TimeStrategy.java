package Logic;

import model.Server;
import model.Task;

import java.util.List;

public class TimeStrategy implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task task) {
        Server minTimeServ = servers.get(0);
        for (Server index : servers) {
            if (index.getWaitingPeriod() < minTimeServ.getWaitingPeriod()) {
                minTimeServ = index;
            }
        }
        servers.get(servers.indexOf(minTimeServ)).addTask(task);
    }

}