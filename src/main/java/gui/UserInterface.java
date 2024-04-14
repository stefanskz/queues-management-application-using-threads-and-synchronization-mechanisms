package gui;

import Logic.SimulationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface extends JFrame implements ActionListener {

    private JTextField timeLimitText, nrOfServersText, nrOFTasksText, minArrivalText, maxArrivalText, minServiceText, maxServiceText;
    private JButton start;

    public UserInterface() {
        setTitle("Simulation");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(8, 2));

        jPanel.add(new JLabel("Nr. Of Clients: "));
        nrOFTasksText = new JTextField();
        jPanel.add(nrOFTasksText);

        jPanel.add(new JLabel("Nr. Of Servers: "));
        nrOfServersText = new JTextField();
        jPanel.add(nrOfServersText);

        jPanel.add(new JLabel("Time Limit: "));
        timeLimitText = new JTextField();
        jPanel.add(timeLimitText);

        jPanel.add(new JLabel("Min. Arrival Time: "));
        minArrivalText = new JTextField();
        jPanel.add(minArrivalText);

        jPanel.add(new JLabel("Max. Arrival Time: "));
        maxArrivalText = new JTextField();
        jPanel.add(maxArrivalText);

        jPanel.add(new JLabel("Min. Service Time: "));
        minServiceText = new JTextField();
        jPanel.add(minServiceText);

        jPanel.add(new JLabel("Max. Service Time: "));
        maxServiceText = new JTextField();
        jPanel.add(maxServiceText);

        start = new JButton("Start Simulation!");
        start.addActionListener(this);
        jPanel.add(start);

        add(jPanel);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int timeLimit = Integer.parseInt(timeLimitText.getText());
        int minArrivalTime = Integer.parseInt(minArrivalText.getText());
        int maxArrivalTime = Integer.parseInt(maxArrivalText.getText());
        int minServiceTime = Integer.parseInt(minServiceText.getText());
        int maxServiceTime = Integer.parseInt(maxServiceText.getText());
        int nrOfServers = Integer.parseInt(nrOfServersText.getText());
        int nrOfTasks = Integer.parseInt(nrOFTasksText.getText());

        SimulationManager gen = new SimulationManager(timeLimit, minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime, nrOfServers, nrOfTasks);
        Thread t = new Thread(gen);
        t.start();

    }

    public static void main(String[] args) {
        new UserInterface();
    }

}