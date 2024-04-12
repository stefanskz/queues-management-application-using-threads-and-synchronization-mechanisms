package Logic;
import model.*;
import java.util.List;
public class ShortestQueueStrategy implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task task) {
        int minLen = servers.get(0).getTasks().size();
        int indexOfMin = 0;
        for (Server index : servers) {
            if (index.getTasks().size() < minLen) {
                minLen = index.getTasks().size();
                indexOfMin = servers.indexOf(index);
            }
        }
        servers.get(indexOfMin).addTask(task);
    }
}