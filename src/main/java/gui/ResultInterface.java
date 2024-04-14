package gui;

import model.Server;
import model.Task;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ResultInterface extends JFrame {
    private JTextField currentTime, waitingTasks, avgWaitingTime, avgServiceTime, peakHour;
    private JTextField[] queues;

    public ResultInterface(int nrServ) {
        setTitle("Result!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel onePanel = new JPanel();
        onePanel.setLayout(new GridLayout(5 + nrServ, 2));

        onePanel.add(new JLabel("Time: "));
        currentTime = new JTextField();
        onePanel.add(currentTime);

        onePanel.add(new JLabel("Waiting clients: "));
        waitingTasks = new JTextField();
        onePanel.add(waitingTasks);

        queues = new JTextField[nrServ];
        for (int i = 0; i < nrServ; i++) {
            onePanel.add(new JLabel("Queue " + (i + 1) + ": "));
            queues[i] = new JTextField();
            onePanel.add(queues[i]);
        }

        onePanel.add(new JLabel("Avg. Waiting Time: "));
        avgWaitingTime = new JTextField();
        onePanel.add(avgWaitingTime);

        onePanel.add(new JLabel("Avg. Service Time: "));
        avgServiceTime = new JTextField();
        onePanel.add(avgServiceTime);

        onePanel.add(new JLabel("Peak Hour: "));
        peakHour = new JTextField();
        onePanel.add(peakHour);
        add(onePanel);
        pack();
        setVisible(true);
    }

    public void update(int time, List<Task> tasks, List<Server> servers) {
        currentTime.setText(String.valueOf(time));
        waitingTasks.setText(tasks.toString());
        for (int index = 0; index < servers.size(); index++) {
            if (servers.get(index).getTasks().isEmpty()) {
                queues[index].setText("closed");
            } else {
                queues[index].setText(servers.get(index).getTasks().toString());
            }
        }
        pack();
    }

    public void finalUpdate(double avgWaiting, double avgService, int peakTime) {
        peakHour.setText(String.valueOf(peakTime));
        avgServiceTime.setText(String.valueOf(avgService));
        avgWaitingTime.setText(String.valueOf(avgWaiting));
        pack();
    }

}