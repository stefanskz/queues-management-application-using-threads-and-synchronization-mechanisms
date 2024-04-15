package gui;

import Logic.SimulationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface extends JFrame implements ActionListener {
    private JTextField timeLimitText, nrOfServersText, nrOFTasksText, minArrivalText, maxArrivalText, minServiceText, maxServiceText;
    private JButton start;
    String[] options = {"Shortest Time", "Shortest Queue"};
    private JComboBox<String> op;

    public UserInterface() {
        setTitle("Simulation");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(9, 2));

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

        jPanel.add(new JLabel("Strategy: "));
        op = new JComboBox<>(options);
        jPanel.add(op);

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

        boolean flagOp = false;
        if (((String) op.getSelectedItem()).equals("Shortest Time"))
            flagOp = true;
        SimulationManager gen = new SimulationManager(timeLimit, minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime, nrOfServers, nrOfTasks, flagOp);
        Thread t = new Thread(gen);
        t.start();

    }

    public static void main(String[] args) {
        new UserInterface();
    }
}